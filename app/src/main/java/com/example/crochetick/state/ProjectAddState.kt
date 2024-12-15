package com.example.crochetick.state

import android.net.Uri
import com.example.crochetick.dataClass.DetailData

data class ProjectAddState(
    val title:String="",
    val description:String="",
    val image:Uri? = null,
    val details: List<DetailData> = listOf()
)
