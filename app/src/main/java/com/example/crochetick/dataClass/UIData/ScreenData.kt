package com.example.crochetick.dataClass.UIData

import com.example.crochetick.R

sealed class ScreenData(
    var title: String,
    var icon: Int, //Возможно сделать nullable чтобы убрать из массива ненужное присвоение
    var route:String,
){
    object Projects : ScreenData("Проекты", R.drawable.home,"projects")
    object Categories : ScreenData("Схемы",R.drawable.search,"category")
    object Line : ScreenData("Лента",R.drawable.line,"line")
    object Settings : ScreenData("Настройки",R.drawable.settings,"settings")
}
