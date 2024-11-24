package com.example.crochetick.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crochetick.CustomProjectTopBar
import com.example.crochetick.DataClasses.ProjectData
import com.example.crochetick.MainActivity.Companion.projectDataArrays
import com.example.crochetick.MainActivity.Companion.tabDataArrays
import com.example.crochetick.R
import com.example.crochetick.SimpleTopBar
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.TextSecond

@Composable
fun HomeScreen(navController: NavController,innerPadding:PaddingValues,currentScreen: (String) -> Unit){
    currentScreen("Проекты")
    CrochetickTheme {
        Column(modifier = Modifier.padding(innerPadding)){
            ProjectTabRow()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectTabRow(){
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.shadow(4.dp),
        containerColor =MaterialTheme.colorScheme.background
    ) {
        tabDataArrays.forEachIndexed{ index, item ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = {
                    selectedTabIndex = index
                },
                text = {
                    Text(text = item.title)
                },

                )
        }
    }
    when(selectedTabIndex){
        0-> ProjectList(projectDataArrays.filter { !it.ended })
        1-> ProjectList(projectDataArrays.filter { it.ended })
    }
}

@Composable
fun ProjectCard(item: ProjectData, modifier: Modifier = Modifier) {
    CrochetickTheme {
        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp).then(modifier),
            elevation = CardDefaults.elevatedCardElevation(3.dp)
        )
        {
            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)){
                if (item.hasImage){
                    Column(modifier= Modifier.padding(end = 8.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.cow),
                            contentDescription = "Изображение",
                            modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Column{
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),Arrangement.SpaceBetween){
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = item.startDate,
                            color = TextSecond,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = item.description,
                            color = TextSecond,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectList(projectDataArray:List<ProjectData>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(projectDataArray){index, item ->
            if (index==0) Spacer(modifier = Modifier.height(8.dp))
            ProjectCard(
                item = item,
            )
            if (index== projectDataArray.size-1) Spacer(modifier = Modifier.height(8.dp).shadow(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    CrochetickTheme {
        Scaffold (
            topBar = { CustomProjectTopBar{ SimpleTopBar("Проекты") } },
            //bottomBar = { ProjectBottomBar()}
        ){innerPadding->
            Column(modifier = Modifier.padding(innerPadding)){
                ProjectTabRow()
            }
        }
    }
}