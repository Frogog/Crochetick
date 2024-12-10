package com.example.crochetick.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.crochetick.dataClass.model.ProjectDBTable


@Dao
interface ICrochetickDao {
    @Query("SELECT * FROM projectDBTable")
    fun getAllProjects(): LiveData<List<ProjectDBTable>>
}