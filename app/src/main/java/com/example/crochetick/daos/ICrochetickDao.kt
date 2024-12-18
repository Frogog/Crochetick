package com.example.crochetick.daos

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.dataClass.model.NotificationDBTable
import com.example.crochetick.dataClass.model.ProjectDBTable
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import kotlin.coroutines.coroutineContext


@Dao
interface ICrochetickDao {

    @Query("SELECT * FROM projectDBTable WHERE projectId = :projectId")
    fun getProject(projectId:Long):ProjectDBTable

    @Query("SELECT * FROM projectDBTable")
    fun getAllProjects(): Flow<List<ProjectDBTable>>

    @Query("SELECT * FROM detailDBTable WHERE projectIdFK = :projectId")
    fun getAllDetailsByProject(projectId:Long):Flow<List<DetailDBTable>>

    @Query("SELECT * FROM norificationDBTable")
    fun getAllNotifications():Flow<List<NotificationDBTable>>

    @Query("SELECT COUNT(*) FROM detailDBTable WHERE projectIdFK = :projectId AND doneDetails!=countDetails")
    suspend fun checkDoneDetail(projectId: Long):Long

    @Insert
    suspend fun insertProject(projectDBTable: ProjectDBTable):Long

    @Insert
    suspend fun insertDetails(details: List<DetailDBTable>)

    @Insert
    suspend fun insertNotification(notification: NotificationDBTable)

    @Update
    suspend fun updateProject(project:ProjectDBTable)

    @Update
    suspend fun updateDetailCount(detail:DetailDBTable)

    @Delete
    suspend fun deleteNotification(notification: NotificationDBTable)

    @Transaction
    suspend fun InsertProjectWithDetails(project: ProjectDBTable, details: List<DetailDBTable>) {
        try {
            val projectId = insertProject(project)
            Log.d("Database", "Project inserted with id: $projectId")

            val detailsWithProject = details.map { detail ->
                detail.copy(projectIdFK = projectId).also {
                    Log.d("Database", "Creating detail with projectId: ${it.projectIdFK}")
                }
            }
            insertDetails(detailsWithProject)
            Log.d("Database", "Details inserted successfully")
        } catch (e: Exception) {
            Log.e("Database", "Error inserting project with details", e)
            throw e
        }
    }

    @Transaction
    suspend fun UpdateDoneProject(projectId: Long){
        try {
            if (checkDoneDetail(projectId).toInt() ==0){
                val project = getProject(projectId)
                val updateProject = ProjectDBTable(
                    project.projectId,
                    project.title,
                    project.description,
                    project.imageName,
                    true,
                    project.dateStart,
                    LocalDate.now().toString()
                    )
                updateProject(updateProject)
                Log.d("Database", "Project DONE")
            }
            else {
                val project = getProject(projectId)
                val updateProject = ProjectDBTable(
                    project.projectId,
                    project.title,
                    project.description,
                    project.imageName,
                    false,
                    project.dateStart,
                    null
                )
                updateProject(updateProject)
                Log.d("Database", "Project NOT DONE")
            }
        }catch (e: Exception) {
            Log.e("Database", "Error project DONE", e)
            throw e
        }
    }

}