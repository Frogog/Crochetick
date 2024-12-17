package com.example.crochetick.state

import com.example.crochetick.dataClass.model.NotificationDBTable

data class SettingsState(
    val switchMode:Boolean = false,
    val notificationList:List<NotificationDBTable> = listOf()
)