package com.example.crochetick.dataClass.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "detailDBTable", foreignKeys = [
    ForeignKey(entity = ProjectDBTable::class, parentColumns = ["projectId"], childColumns = ["projectIdFK"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE),
])
data class DetailDBTable(
    @PrimaryKey(autoGenerate = true) val detailId:Long = 0,
    @ColumnInfo(name="projectIdFK")val projectIdFK:Long,
    @ColumnInfo(name = "titleDetail") val titleDetail:String,
    @ColumnInfo(name = "countDetails") val countDetails:Int,
    @ColumnInfo(name = "countRow") val countRow:Int,
    @ColumnInfo(name = "schemaText") val schemaText:String?,
    @ColumnInfo(name = "schemaImage") val schemaImage:String?,
    @ColumnInfo(name = "doneDetails") val doneDetails:Int,
    @ColumnInfo(name = "doneRows") val doneRows:Int,
):Serializable
