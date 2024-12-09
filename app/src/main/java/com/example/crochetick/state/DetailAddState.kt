package com.example.crochetick.state

import android.net.Uri

data class DetailAddState(
    val name:String="",
    val count:Int=0,
    val rowCount:Int=0,
    val image: Uri?=null,
    val scheme:String=""
)
