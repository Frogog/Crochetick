package com.example.crochetick.viewModel

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crochetick.dataClass.DetailData
import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.repositories.CrochetickRepository
import com.example.crochetick.state.DetailAddState
import com.example.crochetick.state.ProjectAddState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectWorkSharedViewModel:ViewModel() {
    private val _uiStateProject = MutableStateFlow(ProjectAddState())
    val uiStateProject : StateFlow<ProjectAddState> = _uiStateProject.asStateFlow()

    private val _rightTitle = mutableStateOf(true)
    val rightTitle: State<Boolean> = _rightTitle

    private val _rightDetails = mutableStateOf(true)
    val rightDetails: State<Boolean> = _rightDetails

    fun validateFormProject():Boolean{
        _rightTitle.value = _uiStateProject.value.title.isNotBlank()
        _rightDetails.value = _uiStateProject.value.details.isNotEmpty()
        return (_rightTitle.value&&_rightDetails.value)
    }
    fun updateTitle(titleText: String) {
        _uiStateProject.value =_uiStateProject.value.copy(title = titleText)
    }

    fun updateDescription(descriptionText: String) {
        _uiStateProject.value =_uiStateProject.value.copy(description = descriptionText)
    }

    fun updateImageProject(imageText: Uri){
        _uiStateProject.value = _uiStateProject.value.copy(image = imageText)
    }

    fun updateDetailsList(){
            val detail = DetailData(
            id = 0,
            title = _uiStateDetail.value.name,
            count = _uiStateDetail.value.count,
            rowCount =  _uiStateDetail.value.rowCount,
            detailsReady = 0,
            rowsReady = 0,
            image = _uiStateDetail.value.image,
            scheme = _uiStateDetail.value.scheme
        )
        _uiStateProject.value = _uiStateProject.value.copy(
            details = _uiStateProject.value.details + detail
        )
    }

    fun deleteDetail(index:Int){
        _uiStateProject.value = _uiStateProject.value.copy(
            details = _uiStateProject.value.details.filterIndexed{i,_->i!=index}
        )
    }

    fun createFullProject(project: ProjectDBTable,details: List<DetailDBTable>){
        viewModelScope.launch {
            CrochetickRepository.instance.insertProjectWithDetails(project,details)
        }
    }
//    fun createProject(project: ProjectDBTable){
//        viewModelScope.launch {
//             CrochetickRepository.instance.insertProject(project)
//        }
//    }
//
//    fun createDetail(detail: DetailDBTable){
//        viewModelScope.launch {
//        }
//    }

    private val _uiStateDetail = MutableStateFlow(DetailAddState())
    val uiStateDetail : StateFlow<DetailAddState> = _uiStateDetail.asStateFlow()

    private val _rightName = mutableStateOf(true)
    val rightName: State<Boolean> = _rightName

    private val _rightCount = mutableStateOf(true)
    val rightCount: State<Boolean> = _rightCount

    private val _rightRowCount = mutableStateOf(true)
    val rightRowCount: State<Boolean> = _rightRowCount

    private val _rightImage = mutableStateOf(true)
    val rightImage: State<Boolean> = _rightImage

    private val _rightScheme = mutableStateOf(true)
    val rightScheme: State<Boolean> = _rightScheme



    fun validateFormDetail():Boolean{
        _rightName.value = _uiStateDetail.value.name.isNotBlank()
        _rightCount.value = (_uiStateDetail.value.count != 0)
        _rightRowCount.value = _uiStateDetail.value.rowCount != 0
        _rightImage.value = _uiStateDetail.value.image!=null
        _rightScheme.value = _uiStateDetail.value.scheme.isNotBlank()
        return (_rightName.value&&_rightCount.value&&_rightRowCount.value&&(_rightScheme.value||_rightImage.value))
    }

    fun resetFormDetail(){
        _uiStateDetail.value = DetailAddState()
        _rightName.value = true
        _rightCount.value = true
        _rightRowCount.value = true
        _rightImage.value = true
        _rightScheme.value = true
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

    fun updateImageDetail(imageValue:Uri){
        _uiStateDetail.value = _uiStateDetail.value.copy(image = imageValue)
    }

    fun updateScheme(schemeText:String){
        _uiStateDetail.value = _uiStateDetail.value.copy(scheme = schemeText)
    }


}