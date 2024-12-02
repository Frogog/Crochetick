package com.example.crochetick.DataClasses.UIData

import com.example.crochetick.R

sealed class ScreenData(
    var title: String,
    var icon: Int, //Возможно сделать nullable чтобы убрать из массива ненужное присвоение
    var route:String,
){
    object Projects : ScreenData("Проекты", R.drawable.home,"projects")
    object Schemes : ScreenData("Схемы",R.drawable.search,"schemes")
    object Line : ScreenData("Лента",R.drawable.line,"line")
    object Settings : ScreenData("Настройки",R.drawable.settings,"settings")
    object AddProject: ScreenData("Добавить новый проект", R.drawable.line,"addProject")
    object AddDetail: ScreenData("Добавить деталь", R.drawable.line,"addDetail")
    object Notifications: ScreenData("Уведомления",R.drawable.line,"notifications")
}
