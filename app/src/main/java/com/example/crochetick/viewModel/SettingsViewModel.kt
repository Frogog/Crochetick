package com.example.crochetick.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crochetick.repositories.CrochetickRepository
import com.example.crochetick.state.ProjectAddState
import com.example.crochetick.state.SettingsState
import com.example.crochetick.ui.theme.CrochetickTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel:ViewModel() {
    private val _uiStateNotification = MutableStateFlow(SettingsState())
    val uiStateNotification : StateFlow<SettingsState> = _uiStateNotification.asStateFlow()

    fun getAllNotifications(){
        viewModelScope.launch {
            CrochetickRepository.instance.getAllNotifications()
                .collect { notifications ->
                    _uiStateNotification.value =  _uiStateNotification.value.copy(notificationList = notifications)
                }
        }
    }

}