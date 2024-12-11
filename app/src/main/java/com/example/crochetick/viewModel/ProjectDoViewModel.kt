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

    fun updateProject(projectId:Long,title:String){
        _uiStateProjectDo.value = _uiStateProjectDo.value.copy(
            projectId = projectId,
            projectTitle = title
        )
    }

    fun updateCurrentDetail(detailId:Long){
        _uiStateProjectDo.value = _uiStateProjectDo.value.copy(detailId = detailId)
        _uiStateProjectDo.value = _uiStateProjectDo.value.copy(currentDetail = _uiStateProjectDo.value.details[_uiStateProjectDo.value.details.indexOfFirst { it.projectIdFK == _uiStateProjectDo.value.projectId }])
        if (_uiStateProjectDo.value.currentDetail!=null){
            _uiStateProjectDo.value = _uiStateProjectDo.value.copy(
                countDetails = _uiStateProjectDo.value.currentDetail!!.countDetails,
                countRow = _uiStateProjectDo.value.currentDetail!!.countRow,
                doneDetails = _uiStateProjectDo.value.currentDetail!!.doneDetails,
                doneRows = _uiStateProjectDo.value.currentDetail!!.doneRows
            )
        }
    }

    fun increaseRowCount(){
        if (_uiStateProjectDo.value.doneDetails<_uiStateProjectDo.value.countDetails){
            _uiStateProjectDo.value = _uiStateProjectDo.value.copy(doneRows = _uiStateProjectDo.value.doneRows + 1)
            updateDoneDetails()
        }
    }

    fun decreaseRowCount(){
        if (_uiStateProjectDo.value.doneRows>0) _uiStateProjectDo.value = _uiStateProjectDo.value.copy(doneRows = _uiStateProjectDo.value.doneRows - 1)
        updateDoneDetails()
    }
    fun updateDoneDetails(){
        _uiStateProjectDo.value = _uiStateProjectDo.value.copy(doneDetails = _uiStateProjectDo.value.doneRows/_uiStateProjectDo.value.countRow)
    }

    fun skipDetail(){
        _uiStateProjectDo.value = _uiStateProjectDo.value.copy(
            doneDetails = _uiStateProjectDo.value.doneDetails+1,
            doneRows = (_uiStateProjectDo.value.doneDetails+1)*_uiStateProjectDo.value.countRow
        )
    }

}