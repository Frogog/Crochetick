package com.example.crochetick.TrashCan

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
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
        fun encodeImageToBase64(
            //imageFile: File,
            context: Context
        ): String {
            val imageFile = File(context.filesDir, "ProjectImages/IMG_1733891051288.jpg")
            val inputStream = FileInputStream(imageFile)
            val bytes = inputStream.readBytes()
            inputStream.close()
            return Base64.encodeToString(bytes, Base64.DEFAULT)
        }

        fun getBitmapFromBase64(base64String: String): Bitmap? {
            val decodedString = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }

        fun getImageBitmapFromBase64(base64String: String): ImageBitmap? {
            val bitmap = getBitmapFromBase64(base64String)
            return bitmap?.asImageBitmap()
        }
        fun encodeImageToBase64AndSaveToFile(
            context: Context
        ): Boolean {
            val imageFile = File(context.filesDir, "ProjectImages/img1.jpg")
            val outputFile = File(context.filesDir, "ProjectImages/base64.txt")
            return try {
                // Чтение изображения и преобразование в Base64
                val inputStream = FileInputStream(imageFile)
                val bytes = inputStream.readBytes()
                inputStream.close()

                val base64String = Base64.encodeToString(bytes, Base64.DEFAULT)

                // Запись строки Base64 в текстовый файл
                val outputStream = FileOutputStream(outputFile)
                outputStream.write(base64String.toByteArray())
                outputStream.close()

                true // Успешно завершено
            } catch (e: IOException) {
                e.printStackTrace()
                false // Произошла ошибка
            }
        }

    }
}