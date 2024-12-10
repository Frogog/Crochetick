package com.example.crochetick.dataClass

import android.net.Uri

class DetailData(
    var id :Int,
    var title :String,
    var count:Int,
    var rowCount:Int,
    var detailsReady:Int,
    var rowsReady:Int,
    val image: Uri?,
    val scheme:String
)