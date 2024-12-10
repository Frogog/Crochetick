package com.example.crochetick.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.crochetick.state.ProjectAddState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddProjectViewModel :ViewModel(){
    private val _uiState = MutableStateFlow(ProjectAddState())
    val uiState :StateFlow<ProjectAddState> = _uiState.asStateFlow()


    private val _rightTitle = mutableStateOf(true)
    val rightTitle:State<Boolean> = _rightTitle

    private val _rightDetails = mutableStateOf(true)
    val rightDetails:State<Boolean> = _rightDetails


    fun validateForm(){
        _rightTitle.value = _uiState.value.title.isNotBlank()
        _rightDetails.value = _uiState.value.details.isNotEmpty()
    }
    fun updateTitle(titleText: String) {
        _uiState.value =_uiState.value.copy(title = titleText)
    }

    fun updateDescription(descriptionText: String) {
        _uiState.value =_uiState.value.copy(description = descriptionText)
    }

}
