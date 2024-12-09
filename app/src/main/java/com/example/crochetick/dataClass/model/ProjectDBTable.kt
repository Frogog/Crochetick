package com.example.crochetick.dataClass.model

import androidx.navigation.Navigator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "projectDBTable")
data class ProjectDBTable(
    @PrimaryKey(autoGenerate = true) val projectId:Int = 0,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "description") val description:String,
    @ColumnInfo(name = "imageName") val imageName:String,
    @ColumnInfo(name = "dateStart") val dateStart:String,
    @ColumnInfo(name = "dateEnd") val dateEnd:String,
)
