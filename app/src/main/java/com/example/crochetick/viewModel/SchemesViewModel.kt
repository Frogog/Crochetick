package com.example.crochetick.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.repositories.CrochetickRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SchemesViewModel:ViewModel() {
    private val _projects = MutableStateFlow<List<ProjectDBTable>>(emptyList())
    val projects: StateFlow<List<ProjectDBTable>> = _projects.asStateFlow()

    init {
        viewModelScope.launch {
            CrochetickRepository.instance.getAllProjects()
                .collect { projects ->
                    _projects.value = projects
                }
        }
    }
}