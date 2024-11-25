package com.example.crochetick.States

import com.example.crochetick.DataClasses.DetailData

data class ProjectAddState(
    val title:String="",
    val description:String="",
    val details:List<DetailData> = listOf()
)
