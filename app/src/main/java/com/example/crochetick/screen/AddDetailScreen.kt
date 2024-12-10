package com.example.crochetick.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.crochetick.viewModel.ProjectWorkSharedViewModel
import com.example.crochetick.R
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.TextBright
import com.example.crochetick.ui.theme.TextSecond

@Composable
fun AddDetailScreen(navController: NavController, onBack:()->Unit, onValidate:()->Unit, viewModel: ProjectWorkSharedViewModel){
    Scaffold (
        topBar = {
            DetailTopBar(
                onBack,
                onValidate
            )
        },
    ){innerPadding->
        MainContent(innerPadding,viewModel)
    }
}

@Composable
fun MainContent(innerPadding:PaddingValues, viewModel: ProjectWorkSharedViewModel = viewModel()){
    val uiState by viewModel.uiStateDetail.collectAsState()
    Column(Modifier.padding(innerPadding)) {
        Column(modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp)) {
            OutlinedTextField(
                value = uiState.name,
                onValueChange = {viewModel.updateName(it) },
                label = {
                    Row {
                        Text("Название", color = TextSecond)
                        Text("*", color = Color.Red)
                    }
                },
                supportingText = if (!viewModel.rightName.value){
                    { Text("Название обязательно", color = Color.Red)}
                }else null ,
                singleLine = true,
                modifier = Modifier.width(210.dp)
            )
            OutlinedTextField(
                value = uiState.count.toString(),
                onValueChange = { viewModel.updateCount(it) },
                label = {
                    Row {
                        Text("Количество", color = TextSecond)
                        Text("*", color = Color.Red)
                    }
                },
                supportingText = if (!viewModel.rightCount.value){
                    {Text("Количество обязательно", color = Color.Red)}
                }else null,
                singleLine = true,
                modifier = Modifier.width(210.dp)
            )
            OutlinedTextField(
                value = uiState.rowCount.toString(),
                onValueChange = { viewModel.updateRowCount(it) },
                label = {
                    Row {
                        Text("Количество рядов", color = TextSecond)
                        Text("*", color = Color.Red)
                    }
                },
                supportingText = if (!viewModel.rightRowCount.value){
                    {Text("Количество рядов обязательно", color = Color.Red)}
                }else null,
                singleLine = true,
                modifier = Modifier.width(210.dp)
            )
        }
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Схема", style = MaterialTheme.typography.bodyLarge)
            Text("*", color = Color.Red, style = MaterialTheme.typography.bodyLarge)
            Text(":", style = MaterialTheme.typography.bodyLarge)
        }
        if (!viewModel.rightScheme.value){
            Text(
                "Схема обязательна. Выберите фотографию из галереи или введите текстовое описание.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        ){
            Column{
                ImagePickerButton(viewModel)
                OutlinedTextField(
                    value = uiState.scheme,
                    onValueChange = { viewModel.updateScheme(it) },
                    label = {Text("Схема", color = TextSecond)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp),
                    minLines = 3,
                    maxLines = 3,
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp).shadow(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TestAddDetail(){
    CrochetickTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(Modifier.padding()) {
                Column(modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp)) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Row {
                                Text("Название", color = TextSecond)
                                Text("*", color = Color.Red)
                            }
                        },
                        supportingText = { Text("Название обязательно", color = Color.Red)},
                        singleLine = true,
                        modifier = Modifier.width(210.dp)
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        label = {
                            Row {
                                Text("Количество", color = TextSecond)
                                Text("*", color = Color.Red)
                            }
                        },
                        supportingText = {Text("Количество обязательно", color = Color.Red)},
                        singleLine = true,
                        modifier = Modifier.width(210.dp)
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        label = {
                            Row {
                                Text("Количество рядов", color = TextSecond)
                                Text("*", color = Color.Red)
                            }
                        },
                        supportingText = {Text("Количество рядов обязательно", color = Color.Red)},
                        singleLine = true,
                        modifier = Modifier.width(210.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Схема", style = MaterialTheme.typography.bodyLarge)
                    Text("*", color = Color.Red, style = MaterialTheme.typography.bodyLarge)
                    Text(":", style = MaterialTheme.typography.bodyLarge)
                }
                Text(
                    "Схема обязательна. Выберите фотографию из галереи или введите текстовое описание.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
                ){
                    Column{
                        ImagePickerButton()
                        OutlinedTextField(
                            value = "",
                            onValueChange = { },
                            label = {Text("Схема", color = TextSecond)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(112.dp),
                            minLines = 3,
                            maxLines = 3,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp).shadow(8.dp))
            }
        }
    }
}

@Composable
private fun ImagePickerButton(viewModel: ProjectWorkSharedViewModel = viewModel()) {
    val uiState by viewModel.uiStateDetail.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.updateImageDetail(uri)
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
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
            .padding(top = 10.dp)
    ) {
        Text("Выбрать из галереи")
    }
}

@Composable
fun DetailTopBar(
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

