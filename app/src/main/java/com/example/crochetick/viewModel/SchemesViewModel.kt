package com.example.crochetick.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.dataClass.requestData.CategoriesResponse
import com.example.crochetick.instance.RetrofitInstance
import com.example.crochetick.repositories.CrochetickRepository
import com.example.crochetick.state.ProjectAddState
import com.example.crochetick.state.SchemesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SchemesViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(SchemesState())
    val uiState :StateFlow<SchemesState> = _uiState.asStateFlow()

    private val _projects = MutableStateFlow<List<ProjectDBTable>>(emptyList())
    val projects: StateFlow<List<ProjectDBTable>> = _projects.asStateFlow()

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

    fun updateCategoryId(categoryId:Int){
        _uiState.value = _uiState.value.copy(categoryId = categoryId)
    }

    fun updateSchemeId(schemeId:Int){
        _uiState.value = _uiState.value.copy(schemeId = schemeId)
    }

    fun importSchemeToProject(){

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