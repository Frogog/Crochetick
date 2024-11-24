package com.example.crochetick.Screens

//class AddProjectScreenState(){
//    companion object{
//        var rightState = true
//        var nameText =""
//    }
//    fun ConfirmAction(){
//        rightState!= rightState
//    }
//}
//
//class AddProjectScreenViewModel: ViewModel(){
//    private val _nameText = MutableLiveData<String>()
//    val nameText:  LiveData<String> = _nameText
//
//    fun setNameText(nameText:String){
//        _nameText.value = nameText
//    }
//}
//
//@Composable
//fun AddProjectScreen(navController: NavController, innerPadding: PaddingValues, currentScreen: (String) -> Unit){
//    currentScreen("Добавить новый проект")
//    //val viewModel:AddProjectScreenViewModel by viewModels()
//    CrochetickTheme {
//        Box(
//            modifier = Modifier.padding(innerPadding)
//        ){
//            Column(modifier = Modifier.padding(vertical = 13.dp, horizontal = 20.dp)){
//                Spacer(modifier = Modifier.height(12.dp).shadow(8.dp))
////                var nameText by remember {
////                    mutableStateOf("")
////                }
//                OutlinedTextField(
//                    value = AddProjectScreenState.nameText,
//                    onValueChange = {AddProjectScreenState.nameText = it},
//                    label = {
//                        Row{
//                            Text("Название", color = TextSecond)
//                            Text("*", color = Color.Red)
//                        }
//                    },
//                    supportingText = {
//                        if (!AddProjectScreenState.rightState) Text("Название обязательно", color = Color.Red)
//                    },
//                    singleLine = true,
//                    modifier = Modifier.width(210.dp)
//                )
//                var descriptionText by remember {
//                    mutableStateOf("")
//                }
//                OutlinedTextField(
//                    value = descriptionText,
//                    onValueChange = {descriptionText = it},
//                    label = {Text("Описание", color = TextSecond)},
//                    modifier = Modifier.fillMaxWidth().height(112.dp),
//                    minLines = 3,
//                    maxLines = 3,
//                )
//                Spacer(modifier = Modifier.height(24.dp).shadow(8.dp))
//            }
//        }
//    }
//}
