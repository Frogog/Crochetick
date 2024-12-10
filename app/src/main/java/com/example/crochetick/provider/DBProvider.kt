package com.example.crochetick.provider

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crochetick.daos.ICrochetickDao
import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.dataClass.model.NotificationDBTable
import com.example.crochetick.dataClass.model.ProjectDBTable

@Database(entities = [ProjectDBTable::class,DetailDBTable::class,NotificationDBTable::class], version = 1)
abstract class DBProvider:RoomDatabase() {
    companion object{
        lateinit var instance:DBProvider

        fun create(context: Context){
            instance = Room.databaseBuilder(context,DBProvider::class.java,"CrochetickDB").build()
        }
    }
    abstract fun crochetickDao(): ICrochetickDao
}