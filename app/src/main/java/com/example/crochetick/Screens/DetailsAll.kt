package com.example.crochetick.Screens

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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import androidx.navigation.NavController
import com.example.crochetick.Activities.ProjectDoViewModel
import com.example.crochetick.DataClasses.DetailData
import com.example.crochetick.R
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.DoingYellow
import com.example.crochetick.ui.theme.NoGray
import com.example.crochetick.ui.theme.ReadyGreen
import com.example.crochetick.ui.theme.TextBright

@Composable
fun DetailsAll(
    navController: NavController,
    onBack:()->Unit,
    onClick:()->Unit,
    viewModel: ProjectDoViewModel
){
    Scaffold(
        topBar = { DetailsAllTopBar(
            onBack
        )}
    ) {innerPadding->
        MainContent(innerPadding,onClick)
    }
}

@Composable
fun MainContent(innerPadding:PaddingValues,onClick:()->Unit){
    Column(modifier = Modifier.padding(innerPadding)) {
        val detailDataArrays:List<DetailData> = listOf(
            DetailData(0,"Ухо",5,1,4,5,"image","sheme"),
            DetailData(1,"Тело",1,10,1,0,"image","sheme"),
            DetailData(2,"Лапы",1,8,0,0,"image","sheme")
        )
        DetailList(detailDataArrays,onClick)
    }
}

@Composable
fun DetailList(detailList:List<DetailData>,onClick:()->Unit){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(detailList){index, item ->
            if (index==0) Spacer(modifier = Modifier.height(24.dp))
            DetailCard(item,onClick)
            if (index==detailList.size-1) Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DetailCard(item:DetailData,onClick:()->Unit){
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 4.dp,),
        onClick = {

        }
    ){
        var colorBackground:Modifier = Modifier.background(ReadyGreen)
        if (item.detailsReady>0&&item.detailsReady<item.count) colorBackground= Modifier.background(DoingYellow) else if (item.detailsReady==0) colorBackground= Modifier.background(
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
                    Text(item.title, style = MaterialTheme.typography.titleSmall)
                    Text(item.detailsReady.toString()+"/"+item.count.toString(), style = MaterialTheme.typography.bodySmall)
                }
                IconButton(
                    onClick = onClick,
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
                .padding(top = 44.dp, bottom = 8.dp, start = 16.dp),
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
                    "Корова",
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