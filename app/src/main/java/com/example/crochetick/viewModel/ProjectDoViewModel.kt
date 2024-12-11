package com.example.crochetick.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.repositories.CrochetickRepository
import com.example.crochetick.state.ProjectAddState
import com.example.crochetick.state.ProjectDoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectDoViewModel:ViewModel() {

    private val _uiStateProjectDo = MutableStateFlow(ProjectDoState())
    val uiStateProjectDo : StateFlow<ProjectDoState> = _uiStateProjectDo.asStateFlow()

    fun getAllDetailsByProject(){
        viewModelScope.launch {
            CrochetickRepository.instance.getAllDetailsByProject(_uiStateProjectDo.value.projectId)
                .collect { detailsList ->
                    _uiStateProjectDo.value = _uiStateProjectDo.value.copy(details = detailsList)
                }
        }
    }

    fun updateProjectId(projectId:Long){
        _uiStateProjectDo.value = _uiStateProjectDo.value.copy(projectId = projectId)
    }

    fun updateCurrentDetail(detailId:Long){
        _uiStateProjectDo.value = _uiStateProjectDo.value.copy(detailId = detailId)
        _uiStateProjectDo.value = _uiStateProjectDo.value.copy(currentDetail = _uiStateProjectDo.value.details[_uiStateProjectDo.value.details.indexOfFirst { it.projectIdFK == _uiStateProjectDo.value.projectId }])
    }

}