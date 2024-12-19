package com.example.crochetick.dataClass

data class LineData(
    val id:Int = 0,
    val name:String="",
    val hour:Int=0,
    val minute:Int=0,
    val date:String,
    val image:String?,
    val title:String,
    val text:String
)
