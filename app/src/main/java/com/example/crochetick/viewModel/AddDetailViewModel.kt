package com.example.crochetick.viewModel

//
//class AddDetailViewModel:ViewModel() {
//    private val _uiState = MutableStateFlow(DetailAddState())
//    val uiState:StateFlow<DetailAddState> = _uiState
//
//
//    private val _rightName = mutableStateOf(true)
//    val rightName: State<Boolean> = _rightName
//
//    private val _rightCount = mutableStateOf(true)
//    val rightCount: State<Boolean> = _rightCount
//
//    private val _rightRowCount = mutableStateOf(true)
//    val rightRowCount: State<Boolean> = _rightRowCount
//
//    private val _rightScheme = mutableStateOf(true)
//    val rightScheme: State<Boolean> = _rightScheme
//
//    fun validateForm(){
//        _rightName.value = _uiState.value.name.isNotBlank()
//        _rightCount.value = (_uiState.value.count != 0)
//        _rightRowCount.value = _uiState.value.rowCount != 0
//        _rightScheme.value = _uiState.value.scheme.isNotBlank()
//    }
//
//    fun updateName(nameText:String){
//        _uiState.value = _uiState.value.copy(name=nameText)
//    }
//
//    fun updateCount(countValue:String){
//        if (countValue.toIntOrNull()!=null) _uiState.value = _uiState.value.copy(count=countValue.toInt())
//        else _rightCount.value = false
//    }
//
//    fun updateRowCount(rowCountValue:String){
//        if (rowCountValue.toIntOrNull()!=null) _uiState.value = _uiState.value.copy(rowCount = rowCountValue.toInt())
//        else _rightRowCount.value =false
//    }
//
//    fun updateImage(imageValue:String){
//        _uiState.value = _uiState.value.copy(image = imageValue)
//    }
//
//    fun updateScheme(schemeText:String){
//        _uiState.value = _uiState.value.copy(scheme = schemeText)
//    }
//}