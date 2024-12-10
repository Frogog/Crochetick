package com.example.crochetick.TrashCan

import android.app.AlertDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Usual {
    companion object {
        val formatterEn = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatterRu = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        //Работа с датами
        fun EnToRu(enDateText:String):String{
            return formatterRu.format(formatterEn.parse(enDateText)?.time).toString()
        }
        fun RuToEn(ruDateText:String):String{
            return formatterEn.format(formatterRu.parse(ruDateText)?.time)
        }
        fun DateToRu(date: Date):String{
            return formatterRu.format(date.time).toString()
        }
        //Общие методы
        fun Notification(message:String,context:Context){
            val alert1 = AlertDialog.Builder(context).setPositiveButton("Понял", { d, id->d.cancel() } )
            alert1.setMessage(message).create().show()
        }

    }
}