package com.example.crochetick.viewModel

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
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

//    fun getAllNotifications(){
//        viewModelScope.launch {
//            CrochetickRepository.instance.getAllNotifications()
//                .collect { notifications ->
//                    _uiStateNotification.value =  _uiStateNotification.value.copy(notificationList = notifications)
//                }
//        }
//    }

    fun insertNotification(notification: NotificationDBTable){
        viewModelScope.launch {
            CrochetickRepository.instance.insertNotification(notification)
        }
    }

    fun deleteNotification(notification: NotificationDBTable){
        viewModelScope.launch {
            CrochetickRepository.instance.deleteNotification(notification)
        }
    }

    fun updateSwitchMode(){
        _uiStateNotification.value = _uiStateNotification.value.copy(switchMode = !_uiStateNotification.value.switchMode)
    }

    fun setSwitchState(checked: Boolean) {
        viewModelScope.launch {
            settingsDataStore.saveSwitchState(checked)
        }
    }

    fun scheduleNotification(notificationId:Int,title:String, message:String, hour: Int, minute: Int) {
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
            "title" to title,
            "message" to message,
            "notificationId" to notificationId.toLong()
        )

        // Создаем запрос на выполнение
        val notificationWork = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(data)
            .setInitialDelay(
                calendar.timeInMillis - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS
            )
            .addTag("Notifications")
            .build()

        // Запускаем Worker
        workManager.enqueue(notificationWork)
    }

    fun toggleNotifications(enable:Boolean){
        if (enable){
            viewModelScope.launch {
                CrochetickRepository.instance.getAllNotifications().collect { list ->
                    list.forEach{ item->
                        scheduleNotification(
                            item.noticationId.toInt(),
                            "Напоминание",
                            "saads",
                            item.hour,
                            item.minute
                        )
                    }
                }
            }
        }
        else{
            cancelAllNotifications()
        }
    }

    fun TestNotify(){
        scheduleNotification(
            5,
            "asd",
            "asdasd",
            14,
                35
        )
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