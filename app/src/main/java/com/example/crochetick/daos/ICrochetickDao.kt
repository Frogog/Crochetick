package com.example.crochetick.daos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.dataClass.model.ProjectDBTable
import kotlinx.coroutines.flow.Flow


@Dao
interface ICrochetickDao {
    @Query("SELECT * FROM projectDBTable")
    fun getAllProjects(): Flow<List<ProjectDBTable>>

    @Query("SELECT * FROM detailDBTable WHERE projectIdFK = :projectId")
    fun getAllDetailsByProject(projectId:Long):Flow<List<DetailDBTable>>

    @Insert
    suspend fun insertProject(projectDBTable: ProjectDBTable):Long

    @Insert
    suspend fun insertDetails(details: List<DetailDBTable>)

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
}