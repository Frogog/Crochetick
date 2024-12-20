package com.example.crochetick.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crochetick.TrashCan.Usual
import com.example.crochetick.activitiy.ProjectDoActivity
import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.dataClass.requestData.CategoriesResponse
import com.example.crochetick.dataClass.requestData.SchemesResponse
import com.example.crochetick.instance.RetrofitInstance
import com.example.crochetick.repositories.CrochetickRepository
import com.example.crochetick.state.ProjectAddState
import com.example.crochetick.state.SchemesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class SchemesViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(SchemesState())
    val uiState :StateFlow<SchemesState> = _uiState.asStateFlow()

    private val _projects = MutableStateFlow<List<ProjectDBTable>>(emptyList())
    val projects: StateFlow<List<ProjectDBTable>> = _projects.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<Pair<Long, String>>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            CrochetickRepository.instance.getAllProjects()
                .collect { projects ->
                    _projects.value = projects
                }
        }
        viewModelScope.launch {
            val response = RetrofitInstance.api.getCategories()
            if (response.isSuccessful) {
                response.body()?.let { categories ->
                    _uiState.value = _uiState.value.copy(categories =  categories)
                }
            } else {
                Log.e("HomeViewModel", "Ошибка получения категорий: ${response.errorBody()}")
            }
        }
    }

    fun updateCategory(categoryId:Int,categoryTitle:String){
        _uiState.value = _uiState.value.copy(categoryId = categoryId,categoryTitle=categoryTitle)
    }

    fun updateSchemeId(schemeId:Int){
        _uiState.value = _uiState.value.copy(schemeId = schemeId)
    }

    fun importSchemeToProject(project:ProjectDBTable,details:List<DetailDBTable>){
        viewModelScope.launch {
            val projectCreated = withContext(Dispatchers.IO) {
                CrochetickRepository.instance.insertProjectWithDetails(project, details)
                CrochetickRepository.instance.getLastProject()
            }

            _uiState.value = _uiState.value.copy(
                createdId = projectCreated.projectId,
                createdTitle = projectCreated.title
            )

            // Отправляем событие навигации
            _navigationEvent.emit(
                Pair(projectCreated.projectId, projectCreated.title)
            )
        }
    }
    fun updateLastProject(context: Context){
        viewModelScope.launch {
            val projectCreated = CrochetickRepository.instance.getLastProject()
            _uiState.value = _uiState.value.copy(createdId = projectCreated.projectId, createdTitle = projectCreated.title)
            Usual.Notification(_uiState.value.createdId.toString()+" "+_uiState.value.createdTitle,context)
            _uiState.value = _uiState.value.copy(updated = true)
            Log.d("Деталь обновлена","Да да да да ")
        }
    }

    fun updateSchemesByCategory(){
        viewModelScope.launch {
            val response = RetrofitInstance.api.getShemes()
            if (response.isSuccessful) {
                response.body()?.let { schemes ->
                    _uiState.value = _uiState.value.copy(schemes = schemes.filter { it.category.id==_uiState.value.categoryId })
                }
            } else {
                Log.e("HomeViewModel", "Ошибка получения категорий: ${response.errorBody()}")
            }
        }
    }

}