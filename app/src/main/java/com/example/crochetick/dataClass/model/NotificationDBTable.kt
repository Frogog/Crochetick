package com.example.crochetick.dataClass.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "norificationDBTable")
data class NotificationDBTable(
    @PrimaryKey(autoGenerate = true)val noticationId:Long=0,
    @ColumnInfo(name = "hour") val hour:Int,
    @ColumnInfo(name="minute") val minute:Int,
    @ColumnInfo(name = "turnOn") val turnOn:Boolean=false
){

}
