package com.example.crochetick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crochetick.Classes.NavData
import com.example.crochetick.Classes.ProjectData
import com.example.crochetick.Classes.TabData
import com.example.crochetick.MainActivity.Companion.projectDataArrays
import com.example.crochetick.MainActivity.Companion.tabDataArrays
import com.example.crochetick.ui.theme.OnCardSurfaceSecondBrown
import com.example.crochetick.ui.theme.CrochetickTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.crochetick.MainActivity.Companion.navDataArrays
import com.example.crochetick.ui.theme.CardSurfaceBrown
import com.example.crochetick.ui.theme.NavBarSelectedColor


class MainActivity : ComponentActivity() {
    companion object {
        val projectDataArrays:List<ProjectData> = listOf(
            ProjectData(0,"Первый","Описание первого проекта",true,"01.12.2024","02.12.2024",),
            ProjectData(1,"Заяц","Для подруги",true,"02.12.2024","03.12.2024",),
            ProjectData(2,"Осьминог","На полку с ракушками с моря",false,"03.12.2024","04.12.2024",),
            ProjectData(3,"Корова","Розово-белая игрушка коровы станет отличным подарком для детей постарше - используются глаза, которые малыши могут оторвать и проглотить спровоцировав удушье. ",
                false,"04.12.2024","05.12.2024", true),
            ProjectData(3,"Корова","Розово-белая игрушка коровы станет отличным подарком для детей постарше - используются глаза, которые малыши могут оторвать и проглотить спровоцировав удушье. ",
                false,"04.12.2024","05.12.2024", true),

        )
        val tabDataArrays:List<TabData> = listOf(
            TabData(0,"Начатые"),
            TabData(1,"Завершенные")
        )
        val navDataArrays:List<NavData> = listOf(
            NavData("Проекты",R.drawable.home),
            NavData("Схемы",R.drawable.search),
            NavData("Лента",R.drawable.document),
            NavData("Настройки",R.drawable.profile),
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrochetickTheme {
                var selectedTabIndex by remember {
                    mutableIntStateOf(0)
                }
                Scaffold (
                    topBar = { CustomProjectTopBar("Проекты") },
                    bottomBar = { ProjectBottomBar()}
                ){innerPadding->
                    Column(modifier = Modifier.padding(innerPadding)){
                        ProjectTabRow()
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            itemsIndexed(projectDataArrays){index, item ->
                                if (index==0) Spacer(modifier = Modifier.height(8.dp))
                                ProjectCard(
                                    item = item,
                                )
                                if (index== projectDataArrays.size-1) Spacer(modifier = Modifier.height(8.dp).shadow(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomProjectTopBar(title: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardSurfaceBrown)
            .padding(top = 28.dp,bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
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
        modifier = Modifier.shadow(4.dp)
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
        0->ProjectList(projectDataArrays.filter { !it.ended })
        1->ProjectList(projectDataArrays.filter { it.ended })
    }
}

@Composable
fun ProjectCard(item:ProjectData, modifier: Modifier = Modifier) {
    CrochetickTheme {
        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp).then(modifier),
            elevation = CardDefaults.elevatedCardElevation(6.dp)
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
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)){
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = item.description,
                            color = OnCardSurfaceSecondBrown,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectBottomBar(){
    var selectedNavIndex by rememberSaveable() {
        mutableIntStateOf(0)
    }
    NavigationBar(
    ) {
        navDataArrays.forEachIndexed{index, item ->
            NavigationBarItem(
                selected = selectedNavIndex==index,
                onClick = {
                    selectedNavIndex =index
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(item.icon),
                        contentDescription = "Проекты"
                    )
                },
                label = {
                    Text(item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = NavBarSelectedColor,
                    selectedTextColor = NavBarSelectedColor
                )
            )
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
fun ProjectBottomBarPreview(){
    CrochetickTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                ProjectBottomBar()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    CrochetickTheme {
        Scaffold (
            topBar = { CustomProjectTopBar("Проекты") },
            bottomBar = { ProjectBottomBar()}
        ){innerPadding->
            Column(modifier = Modifier.padding(innerPadding)){
                ProjectTabRow()
            }
        }
    }
}