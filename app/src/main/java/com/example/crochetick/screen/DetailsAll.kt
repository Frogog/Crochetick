package com.example.crochetick.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.crochetick.viewModel.ProjectDoViewModel
import com.example.crochetick.dataClass.DetailData
import com.example.crochetick.R
import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.DoingYellow
import com.example.crochetick.ui.theme.NoGray
import com.example.crochetick.ui.theme.ReadyGreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailsAll(
    navController: NavController,
    onBack:()->Unit,
    viewModel: ProjectDoViewModel
){
    Scaffold(
        topBar = { DetailsAllTopBar(
            viewModel.uiStateProjectDo.value.projectTitle,
            onBack
        )}
    ) {innerPadding->
        MainContent(
            innerPadding,
            {detailId->
                viewModel.updateCurrentDetail(detailId)
                navController.navigate("showDetail")
            },
            viewModel)
    }
}

@Composable
fun MainContent(innerPadding:PaddingValues,onClick:(detailId:Long)->Unit,viewModel: ProjectDoViewModel){
    Column(modifier = Modifier.padding(innerPadding)) {

        val uiState = viewModel.uiStateProjectDo.collectAsState()

        LaunchedEffect(Unit) {//uiState.value.projectId можно указать его прям так, чтобы он обновлялся при изменении projectId
            viewModel.getAllDetailsByProject()
        }

        DetailList(uiState.value.details,onClick)
    }
}

@Composable
fun DetailList(detailList:List<DetailDBTable>,onClick:(detailId:Long)->Unit){
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(detailList){index, item ->
            DetailCard(item,onClick)
        }
    }
}

@Composable
fun DetailCard(item:DetailDBTable,onClick:(detailId:Long)->Unit){
    Card( modifier = Modifier
        .fillMaxWidth(),
        onClick = {
            onClick(item.detailId)
        }
    ){
        var colorBackground:Modifier = Modifier.background(ReadyGreen)
        if (item.doneDetails>0&&item.doneDetails<item.countDetails) colorBackground= Modifier.background(DoingYellow) else if (item.doneDetails==0) colorBackground= Modifier.background(
            NoGray)
        Row(
            modifier = Modifier.fillMaxWidth().padding(end = 8.dp).height(IntrinsicSize.Min),
        ){
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .then(colorBackground)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 16.dp)
                ) {
                    Text(item.titleDetail, style = MaterialTheme.typography.titleSmall)
                    Text(item.doneDetails.toString()+"/"+item.countDetails.toString(), style = MaterialTheme.typography.bodySmall)
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.inside_icon),
                        contentDescription = "Localized description",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestDetail(){
    CrochetickTheme {
        Card( modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp,),
            onClick = {
            }
        ){
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 8.dp).height(IntrinsicSize.Min),
            ){
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .fillMaxHeight()
                        .background(ReadyGreen)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 16.dp)
                    ) {
                        Text("Тело", style = MaterialTheme.typography.titleSmall)
                        Text("1/1", style = MaterialTheme.typography.bodySmall)
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(24.dp),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.inside_icon),
                            contentDescription = "Localized description",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun DetailsAllTopBar(
    title:String,
    onBackClick: () -> Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 5.dp,
        tonalElevation = 5.dp,
        color = Background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Background)
                .padding(top = 48.dp, bottom = 16.dp, start = 16.dp),
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
                    title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun test(){
    CrochetickTheme {
        Scaffold(
            topBar = {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 5.dp,
                    tonalElevation = 5.dp,
                    color = Background
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Background)
                            .padding(top = 44.dp, bottom = 8.dp, start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 10.dp)
                        ) {
                            IconButton(
                                onClick = {},
                                modifier = Modifier.size(16.dp),//Уменьшил с 24
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                                    contentDescription = "Localized description",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                "Тело",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        ) {innerPadding->

        }

    }
}