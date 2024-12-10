package com.example.crochetick.repositories

import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.provider.DBProvider
import kotlinx.coroutines.flow.Flow

class CrochetickRepository {
    private val crochetickDao = DBProvider.instance.crochetickDao()

    companion object{
        val instance = CrochetickRepository()
    }

    fun getAllProjects(): Flow<List<ProjectDBTable>> = crochetickDao.getAllProjects()

    suspend fun insertProject(projectDBTable: ProjectDBTable):Long {
        return crochetickDao.insertProject(projectDBTable)
    }

    suspend fun insertProjectWithDetails(project:ProjectDBTable, details:List<DetailDBTable>) = crochetickDao.InsertProjectWithDetails(project,details)
}