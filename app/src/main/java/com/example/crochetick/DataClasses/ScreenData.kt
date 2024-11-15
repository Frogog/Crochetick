package com.example.crochetick.DataClasses

import com.example.crochetick.R

sealed class ScreenData(
    var title: String,
    var icon: Int,
    var route:String,
){
    object Projects : ScreenData("Проекты", R.drawable.home,"projects")
    object Schemes : ScreenData("Схемы",R.drawable.search,"schemes")
    object Line : ScreenData("Лента",R.drawable.line,"line")
    object Settings : ScreenData("Настройки",R.drawable.settings,"settings")
}
