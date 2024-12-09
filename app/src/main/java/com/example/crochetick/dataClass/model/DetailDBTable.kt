package com.example.crochetick.dataClass.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "detailDBTable", foreignKeys = [
    ForeignKey(entity = ProjectDBTable::class, parentColumns = ["projectId"], childColumns = ["detailId"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE),
])
data class DetailDBTable(
    @PrimaryKey(autoGenerate = true) val detailId:Int = 0,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "description") val description:String,
    @ColumnInfo(name = "imageName") val imageName:String,
    @ColumnInfo(name = "dateStart") val dateStart:String,
    @ColumnInfo(name = "dateEnd") val dateEnd:String,
)
