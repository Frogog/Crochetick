package com.example.crochetick.repositories

import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.dataClass.model.NotificationDBTable
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.provider.DBProvider
import kotlinx.coroutines.flow.Flow

class CrochetickRepository {
    private val crochetickDao = DBProvider.instance.crochetickDao()

    companion object{
        val instance = CrochetickRepository()
    }

    fun getAllProjects(): Flow<List<ProjectDBTable>> = crochetickDao.getAllProjects()

    fun getAllDetailsByProject(projectId:Long):Flow<List<DetailDBTable>> = crochetickDao.getAllDetailsByProject(projectId)

    fun getAllNotifications():Flow<List<NotificationDBTable>> = crochetickDao.getAllNotifications()

    suspend fun getLastProject():ProjectDBTable = crochetickDao.getLastProject()

    suspend fun insertProjectWithDetails(project:ProjectDBTable, details:List<DetailDBTable>) = crochetickDao.InsertProjectWithDetails(project,details)

    suspend fun insertNotification(notification: NotificationDBTable) = crochetickDao.insertNotification(notification)

    suspend fun updateDoneProject(projectId: Long) = crochetickDao.UpdateDoneProject(projectId)

    suspend fun updateDetailCount(detail:DetailDBTable) = crochetickDao.updateDetailCount(detail)

    suspend fun deleteNotification(notification: NotificationDBTable) = crochetickDao.deleteNotification(notification)
}