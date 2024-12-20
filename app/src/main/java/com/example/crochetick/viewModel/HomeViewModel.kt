package com.example.crochetick.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.dataClass.requestData.CategoriesResponse
import com.example.crochetick.dataClass.requestData.SchemesResponse
import com.example.crochetick.instance.RetrofitInstance
import com.example.crochetick.repositories.CrochetickRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val _projects = MutableStateFlow<List<ProjectDBTable>>(emptyList())
    val projects: StateFlow<List<ProjectDBTable>> = _projects.asStateFlow()

    private val _categories = MutableStateFlow<List<CategoriesResponse>>(emptyList())
    val categories: StateFlow<List<CategoriesResponse>> = _categories.asStateFlow()

    private val _scheme = MutableStateFlow<List<SchemesResponse>>(emptyList())
    val scheme : StateFlow<List<SchemesResponse>> = _scheme.asStateFlow()

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
                    _categories.value = categories // Обновляем состояние с полученными категориями
                }
            } else {
                // Обработка ошибки, если необходимо
                Log.e("HomeViewModel", "Ошибка получения категорий: ${response.errorBody()}")
            }
        }
        viewModelScope.launch {
            val response = RetrofitInstance.api.getShemes()
            if (response.isSuccessful) {
                response.body()?.let { schemes ->
                    _scheme.value = schemes
                }
            } else {
                Log.e("HomeViewModel", "Ошибка получения категорий: ${response.errorBody()}")
            }
        }
    }

//    fun getCategories(){
//        viewModelScope.launch {
//            val response = RetrofitInstance.api.getCategories()
//            if (response.isSuccessful) {
//                response.body()?.let { categories ->
//                    _categories.value = categories // Обновляем состояние с полученными категориями
//                }
//            } else {
//                // Обработка ошибки, если необходимо
//                Log.e("HomeViewModel", "Ошибка получения категорий: ${response.errorBody()}")
//            }
//        }
//    }

}