package com.example.crochetick.States

import android.net.Uri
import com.example.crochetick.DataClasses.DetailData

data class ProjectAddState(
    val title:String="",
    val description:String="",
    val image:Uri? = null,
    val details:List<DetailData> = listOf()
)
