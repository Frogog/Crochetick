package com.example.crochetick.Screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.crochetick.Activities.ProjectWorkSharedViewModel
import com.example.crochetick.DataClasses.DetailData
import com.example.crochetick.R
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.TextBright
import com.example.crochetick.ui.theme.TextSecond
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import coil.compose.AsyncImage

@Composable
fun AddProjectScreen(navController: NavController,onBack:()->Unit,onValidate:()->Unit,viewModel: ProjectWorkSharedViewModel){
    Scaffold (
        topBar = {
            ProjectTopBar(
                onBack,
                onValidate
            )
        },
    ){innerPadding->
        MainContent(innerPadding,{navController.navigate("addDetail")},viewModel)
    }
}

@Composable
fun MainContent(innerPadding: PaddingValues,goToDetail:()->Unit,viewModel: ProjectWorkSharedViewModel = viewModel()){
    val uiState by viewModel.uiStateProject.collectAsState()
    Box(
        modifier = Modifier.padding(innerPadding)
    ){
        Column{
            Column(modifier = Modifier.padding(vertical = 12.dp,horizontal = 20.dp)){
                OutlinedTextField(
                    value = uiState.title,
                    onValueChange = {viewModel.updateTitle(it)},
                    label = {
                        Row{
                            Text("Название", color = TextSecond)
                            Text("*", color = Color.Red)
                        }
                    },
                    supportingText = if (!viewModel.rightTitle.value) {
                        { Text("Название обязательно", color = Color.Red) }
                    }else {
                        { Text("", color = Color.Red) }
                    },
                    singleLine = true,
                    modifier = Modifier.width(210.dp)
                )
                OutlinedTextField(
                    value = uiState.description,
                    onValueChange = {viewModel.updateDescription(it)},
                    label = { Text("Описание", color = TextSecond) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp),
                    minLines = 3,
                    maxLines = 3,
                )
            }
            Column(
                modifier = Modifier.padding(start = 16.dp,end = 16.dp, top = 10.dp)
            ) {
                Text("Обложка проекта:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 10.dp, bottom = 8.dp))
                ImagePickerButton(viewModel)
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Детали", style = MaterialTheme.typography.bodyLarge)
                IconButton(
                    onClick = {
                        goToDetail()
                    },
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                        contentDescription = "AddDetail",
                        tint = TextSecond
                    )
                }
            }
            if (!viewModel.rightDetails.value){
                Text(
                    "Добавьте хотя бы одну деталь",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            AddDetailList()
            Spacer(modifier = Modifier
                .height(24.dp)
                .shadow(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestAddProject(){
    CrochetickTheme {
        Surface(
            modifier = Modifier.padding()
        ){
            Column{
                Column(modifier = Modifier.padding(vertical = 12.dp,horizontal = 20.dp)){
                    OutlinedTextField(
                        value = "AD",
                        onValueChange = {},
                        label = {
                            Row{
                                Text("Название", color = TextSecond)
                                Text("*", color = Color.Red)
                            }
                        },
                        supportingText = { Text("Название обязательно", color = Color.Red) },
                        singleLine = true,
                        modifier = Modifier.width(210.dp)
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Описание", color = TextSecond) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(112.dp),
                        minLines = 3,
                        maxLines = 3,
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 16.dp,end = 16.dp, top = 10.dp)
                ) {
                    Text("Обложка проекта:",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 10.dp, bottom = 8.dp))
                    ImagePickerButton()
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text("Детали", style = MaterialTheme.typography.bodyLarge)
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier.size(24.dp),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                            contentDescription = "AddDetail",
                            tint = TextSecond
                        )
                    }
                }
                Text(
                    "Добавьте хотя бы одну деталь",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp)
                )
                AddDetailList()
                Spacer(modifier = Modifier
                    .height(24.dp)
                    .shadow(8.dp))
            }
        }
    }
}

@Composable
private fun ImagePickerButton(viewModel: ProjectWorkSharedViewModel = viewModel()) {
    val uiState by viewModel.uiStateProject.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.updateImageProject(uri)
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding()
    ){
        if (uiState.image != null) {
            AsyncImage(
                model = uiState.image,
                contentDescription = "Выбранное изображение",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 6.dp)
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } else null
    }

    FilledTonalButton(
        onClick = {
            launcher.launch("image/*")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp,vertical = 10.dp)
    ) {
        Text("Выбрать из галереи")
    }
}

@Composable
fun ProjectTopBar(
    onBackClick: () -> Unit,
    onDoneClick: () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 32.dp, bottom = 8.dp, start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 10.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(16.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                    contentDescription = "Localized description",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "Добавить новый проект",
                style = MaterialTheme.typography.titleLarge
            )
        }
        TextButton(
            onClick = onDoneClick,
            contentPadding = PaddingValues(0.dp),

            modifier = Modifier.padding(end=12.dp) //Чуть меньше чем в дизайне
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
fun AddDetailList(){
    val DetailDataArrays:List<DetailData> = listOf(
        DetailData(0,"Ухо",1,5,0,0,"image","sheme"),
        DetailData(1,"Тело",1,10,0,0,"image","sheme"),
        DetailData(2,"Лапы",1,8,0,0,"image","sheme")
    )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(DetailDataArrays){index, item ->
            AddDetailCard(
                item = item,
            )
        }
    }
}

@Composable
fun AddDetailCard(item: DetailData){
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 10.dp)
                .fillMaxWidth(),
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