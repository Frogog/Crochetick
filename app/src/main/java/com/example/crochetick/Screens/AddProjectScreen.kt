package com.example.crochetick.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.crochetick.DataClasses.DetailData
import com.example.crochetick.DataClasses.ProjectData
import com.example.crochetick.R
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.TextBright
import com.example.crochetick.ui.theme.TextSecond
import kotlin.math.sin

class AddProjectScreenState(){
    companion object{
        var rightState = true
        var nameText =""
    }
    fun ConfirmAction(){
        rightState!= rightState
    }
}

class AddProjectScreenViewModel: ViewModel(){
    private val _nameText = MutableLiveData<String>()
    val nameText:  LiveData<String> = _nameText

    fun setNameText(nameText:String){
        _nameText.value = nameText
    }
}

@Composable
fun AddProjectScreen(navController: NavController, innerPadding: PaddingValues, currentScreen: (String) -> Unit){
    currentScreen("Добавить новый проект")
    //val viewModel:AddProjectScreenViewModel by viewModels()
    CrochetickTheme {
        Box(
            modifier = Modifier.padding(innerPadding)
        ){
            Column(modifier = Modifier.padding(vertical = 13.dp, horizontal = 20.dp)){
                Spacer(modifier = Modifier.height(12.dp).shadow(8.dp))
//                var nameText by remember {
//                    mutableStateOf("")
//                }
                OutlinedTextField(
                    value = AddProjectScreenState.nameText,
                    onValueChange = {AddProjectScreenState.nameText = it},
                    label = {
                        Row{
                            Text("Название", color = TextSecond)
                            Text("*", color = Color.Red)
                        }
                    },
                    supportingText = {
                        if (!AddProjectScreenState.rightState) Text("Название обязательно", color = Color.Red)
                    },
                    singleLine = true,
                    modifier = Modifier.width(210.dp)
                )
                var descriptionText by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = {descriptionText = it},
                    label = {Text("Описание", color = TextSecond)},
                    modifier = Modifier.fillMaxWidth().height(112.dp),
                    minLines = 3,
                    maxLines = 3,
                )
                Spacer(modifier = Modifier.height(24.dp).shadow(8.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAddScreen(innerPadding: PaddingValues = PaddingValues(20.dp,20.dp)){
    CrochetickTheme {
        Scaffold (
            topBar = { HardTopBarTest("Добавить новый проект")},
            //bottomBar = { ProjectBottomBar()}
        ){innerPadding->
            Box(
                modifier = Modifier.padding(innerPadding)
            ){
                Column{
                    Column(modifier = Modifier.padding(vertical = 13.dp, horizontal = 20.dp)){
                        Spacer(modifier = Modifier.height(12.dp).shadow(8.dp))
                        var nameText by remember {
                            mutableStateOf("")
                        }
                        OutlinedTextField(
                            value = nameText,
                            onValueChange = {nameText = it},
                            label = {
                                Row{
                                    Text("Название", color = TextSecond)
                                    Text("*", color = Color.Red)
                                }
                            },
                            supportingText = {
                                Text("Название обязательно", color = Color.Red)
                            },
                            singleLine = true,
                            modifier = Modifier.width(210.dp)
                        )
                        var descriptionText by remember {
                            mutableStateOf("")
                        }
                        OutlinedTextField(
                            value = "descriptionText",
                            onValueChange = {descriptionText = it},
                            label = {Text("Описание", color = TextSecond)},
                            modifier = Modifier.fillMaxWidth().height(112.dp),
                            minLines = 3,
                            maxLines = 3,
                        )
                    }
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text("Детали", style = MaterialTheme.typography.bodyLarge)
                        IconButton(
                            onClick = {},
                            modifier = Modifier.size(24.dp),
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                                contentDescription = "AddDetail",
                                tint = TextSecond
                            )
                        }
                    }
                    DetailList()
                    Spacer(modifier = Modifier.height(24.dp).shadow(8.dp))
                }
            }
        }
    }
}

@Composable
fun DetailList(){
    val DetailDataArrays:List<DetailData> = listOf(
        DetailData(0,"Ухо"),
        DetailData(1,"Тело"),
        DetailData(2,"Лапы")
    )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(DetailDataArrays){index, item ->
            DetailCard(
                item = item,
            )
        }
    }
}

@Composable
fun DetailCard(item:DetailData){
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ){
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(item.title, style = MaterialTheme.typography.bodyLarge)
            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.minus_icon),
                    contentDescription = "AddDetail",
                    tint = TextSecond
                )
            }
        }
    }
}

@Composable
fun HardTopBar(title:String, navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 32.dp,bottom = 8.dp, start = 16.dp,end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 10.dp)
        ) {
            IconButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier.size(24.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                    contentDescription = "Localized description"
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }
        TextButton(
            onClick = {

                navController.popBackStack()
            },
        ) {
            Text(
                text = "Готово",
                style = MaterialTheme.typography.titleMedium,
                color = TextBright,
            )
        }
    }
}

@Composable
fun HardTopBarTest(title:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 32.dp,bottom = 8.dp, start = 16.dp,end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 10.dp)
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                    contentDescription = "Localized description"
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }
        TextButton(
            onClick = {
                if (AddProjectScreenState.rightState){

                }
            },
        ) {
            Text(
                text = "Готово",
                style = MaterialTheme.typography.titleMedium,
                color = TextBright,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar(){
    HardTopBarTest("Добавить новый проект")
}