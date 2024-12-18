package com.example.crochetick.state

import com.example.crochetick.dataClass.model.NotificationDBTable
import java.util.Calendar

data class SettingsState(
    val switchMode:Boolean = false,
    val notificationList:List<NotificationDBTable> = listOf(),
    val showDialog:Boolean=false,
    val selectedTime:Calendar = Calendar.getInstance()
)