package com.example.crochetick.Classes

data class ProjectData(
    var id:Int,
    var name:String,
    var description:String,
    var ended:Boolean,
    var startDate:String,
    var endDate:String,
    var HasImage:Boolean = false, //Временная переменная
)
