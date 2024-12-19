package com.example.crochetick.viewModel

import android.app.NotificationManager
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.crochetick.dataClass.model.NotificationDBTable
import com.example.crochetick.dataStore.SettingsDataStore
import com.example.crochetick.notifications.NotificationWorker
import com.example.crochetick.repositories.CrochetickRepository
import com.example.crochetick.screen.NotificationSwitch
import com.example.crochetick.state.ProjectAddState
import com.example.crochetick.state.SettingsState
import com.example.crochetick.ui.theme.CrochetickTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit

class SettingsViewModel(
    private val settingsDataStore: SettingsDataStore
):ViewModel() {
    private val _uiStateNotification = MutableStateFlow(SettingsState())
    val uiStateNotification : StateFlow<SettingsState> = _uiStateNotification.asStateFlow()
    val switchState = settingsDataStore.getSwitchState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    init {
        viewModelScope.launch {
            CrochetickRepository.instance.getAllNotifications()
                .collect { notifications ->
                    _uiStateNotification.value = _uiStateNotification.value.copy(notificationList = notifications)
                }
        }
    }

    fun insertNotification(notification: NotificationDBTable){
        viewModelScope.launch {
            CrochetickRepository.instance.insertNotification(notification)
            if (switchState.value){
                scheduleNotification(notification.noticationId.toInt(),notification.hour,notification.minute)
            }
        }
    }

    fun deleteNotification(notification: NotificationDBTable){
        viewModelScope.launch {
            CrochetickRepository.instance.deleteNotification(notification)
        }
    }

    fun setSwitchState(checked: Boolean) {
        viewModelScope.launch {
            settingsDataStore.saveSwitchState(checked)
        }
    }

    fun scheduleNotification(notificationId:Int, hour: Int, minute: Int) {
        val workManager = WorkManager.getInstance()

        // Создаем календарь с нужным временем
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            // Если время уже прошло, добавляем день
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        // Создаем данные для Worker
        val data = workDataOf(
            "title" to "Напоминание",
            "message" to "Пора вязать!",
            "notificationId" to notificationId.toLong()
        )

        // Создаем запрос на выполнение
        val notificationWork = PeriodicWorkRequestBuilder<NotificationWorker>(24, TimeUnit.HOURS)
            .setInputData(data)
            .setInitialDelay(
                calendar.timeInMillis - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS
            )
            .addTag("notification_$notificationId")
            .build()

        // Запускаем Worker
        workManager.enqueueUniquePeriodicWork(
            "notification_$notificationId",
            ExistingPeriodicWorkPolicy.UPDATE,
            notificationWork
        )
    }

    fun toggleNotifications(enable:Boolean){
        viewModelScope.launch {
            if (enable){
                // Используем first() вместо collect
                val notifications = CrochetickRepository.instance.getAllNotifications().first()
                notifications.forEach { item ->
                    scheduleNotification(
                        item.noticationId.toInt(),
                        item.hour,
                        item.minute
                    )
                }
            }
            else{
                cancelAllNotifications()
            }
        }
    }

    fun cancelNotification(notificationId: Long, context: Context) {
        WorkManager.getInstance(context)
            .cancelAllWorkByTag("notification_$notificationId")

        // Удаляем уже показанное уведомление
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(notificationId.toInt())
    }

    fun cancelAllNotifications() {
        val workManager = WorkManager.getInstance()
        workManager.cancelAllWork()
    }

    fun updateShowDialog(show:Boolean){
        _uiStateNotification.value = _uiStateNotification.value.copy(showDialog = show)
    }
}

class SettingsViewModelFactory(
    private val settingsDataStore: SettingsDataStore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(settingsDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}