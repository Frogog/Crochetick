package com.example.crochetick.DataClasses

data class ProjectData(
    var id:Int,
    var name:String,
    var description:String,
    var ended:Boolean,
    var startDate:String,
    var endDate:String,
    var hasImage:Boolean = false, //Временная переменная
)