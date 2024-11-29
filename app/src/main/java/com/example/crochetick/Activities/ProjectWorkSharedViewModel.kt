package com.example.crochetick.Activities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.crochetick.States.DetailAddState
import com.example.crochetick.States.ProjectAddState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProjectWorkSharedViewModel:ViewModel() {
    private val _uiStateProject = MutableStateFlow(ProjectAddState())
    val uiStateProject : StateFlow<ProjectAddState> = _uiStateProject.asStateFlow()

    private val _rightTitle = mutableStateOf(true)
    val rightTitle: State<Boolean> = _rightTitle

    private val _rightDetails = mutableStateOf(true)
    val rightDetails: State<Boolean> = _rightDetails

    fun validateFormProject(){
        _rightTitle.value = _uiStateProject.value.title.isNotBlank()
        _rightDetails.value = _uiStateProject.value.details.isNotEmpty()
    }
    fun updateTitle(titleText: String) {
        _uiStateProject.value =_uiStateProject.value.copy(title = titleText)
    }

    fun updateDescription(descriptionText: String) {
        _uiStateProject.value =_uiStateProject.value.copy(description = descriptionText)
    }

    private val _uiStateDetail = MutableStateFlow(DetailAddState())
    val uiStateDetail : StateFlow<DetailAddState> = _uiStateDetail.asStateFlow()

    private val _rightName = mutableStateOf(true)
    val rightName: State<Boolean> = _rightName

    private val _rightCount = mutableStateOf(true)
    val rightCount: State<Boolean> = _rightCount

    private val _rightRowCount = mutableStateOf(true)
    val rightRowCount: State<Boolean> = _rightRowCount

    private val _rightScheme = mutableStateOf(true)
    val rightScheme: State<Boolean> = _rightScheme

    fun validateFormDetail(){
        _rightName.value = _uiStateDetail.value.name.isNotBlank()
        _rightCount.value = (_uiStateDetail.value.count != 0)
        _rightRowCount.value = _uiStateDetail.value.rowCount != 0
        _rightScheme.value = _uiStateDetail.value.scheme.isNotBlank()
    }

    fun updateName(nameText:String){
        _uiStateDetail.value = _uiStateDetail.value.copy(name=nameText)
    }

    fun updateCount(countValue:String){
        if (countValue.toIntOrNull()!=null) _uiStateDetail.value = _uiStateDetail.value.copy(count=countValue.toInt())
        else _rightCount.value = false
    }

    fun updateRowCount(rowCountValue:String){
        if (rowCountValue.toIntOrNull()!=null) _uiStateDetail.value = _uiStateDetail.value.copy(rowCount = rowCountValue.toInt())
        else _rightRowCount.value =false
    }

    fun updateImage(imageValue:String){
        _uiStateDetail.value = _uiStateDetail.value.copy(image = imageValue)
    }

    fun updateScheme(schemeText:String){
        _uiStateDetail.value = _uiStateDetail.value.copy(scheme = schemeText)
    }
}