package com.example.crochetick.Activities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddProjectViewModel :ViewModel(){
    private val _nameText = mutableStateOf("")
    val nameText: State<String> = _nameText

    private val _descriptionText = mutableStateOf("")
    val descriptionText: State<String> = _descriptionText

    private val _rightData = mutableStateOf(true)
    val rightData:State<Boolean> = _rightData

    private fun validateName(){
        _rightData.value = _nameText.value.isNotBlank()
    }
    fun validateForm(): Boolean {
        validateName()
        return _rightData.value
    }
    fun setNameText(nameText: String) {
        _nameText.value = nameText
    }

    fun setDescriptionText(descriptionText: String) {
        _descriptionText.value = descriptionText
    }

}
