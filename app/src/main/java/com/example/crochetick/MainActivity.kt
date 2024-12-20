package com.example.crochetick

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crochetick.dataClass.UIData.ScreenData
import com.example.crochetick.dataClass.ProjectData
import com.example.crochetick.dataClass.UIData.TabData
import com.example.crochetick.ui.theme.CrochetickTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crochetick.activitiy.ProjectWorkActivity
import com.example.crochetick.MainActivity.Companion.navDataArrays
import com.example.crochetick.dataClass.CategoryData
import com.example.crochetick.dataClass.LineData
import com.example.crochetick.dataStore.SettingsDataStore
import com.example.crochetick.screen.HomeScreen
import com.example.crochetick.screen.LineScreen
import com.example.crochetick.screen.NotificationsScreen
import com.example.crochetick.screen.CategoryScreen
import com.example.crochetick.screen.SettingsScreen
import com.example.crochetick.screen.ShowCategory
import com.example.crochetick.screen.ShowScheme
import com.example.crochetick.ui.theme.LowerNavig
import com.example.crochetick.ui.theme.NavSelect
import com.example.crochetick.viewModel.SchemesViewModel
import com.example.crochetick.viewModel.SettingsViewModel
import com.example.crochetick.viewModel.SettingsViewModelFactory


class MainActivity : ComponentActivity() {
    private val settingsDataStore by lazy { SettingsDataStore(applicationContext) }
    private val SettingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(settingsDataStore)
    }
    private val SchemesViewModel:SchemesViewModel by viewModels()
    companion object {
        val projectDataArrays:List<ProjectData> = listOf(
            ProjectData(0,"Первый","Описание первого проекта",true,"01.12.2024","02.12.2024",),
            ProjectData(1,"Заяц","Для подруги",true,"02.12.2024","03.12.2024",),
            ProjectData(2,"Осьминог","На полку с ракушками с моря",false,"03.12.2024","04.12.2024",),
            ProjectData(3,"Корова","Розово-белая игрушка коровы станет отличным подарком для детей постарше - используются глаза, которые малыши могут оторвать и проглотить спровоцировав удушье. ",
                false,"04.12.2024","05.12.2024", true),
            ProjectData(3,"Корова","Розово-белая игрушка коровы станет отличным подарком для детей постарше - используются глаза, которые малыши могут оторвать и проглотить спровоцировав удушье. ",
                false,"04.12.2024","05.12.2024", true),
            ProjectData(3,"Корова","Розово-белая игрушка коровы станет отличным подарком для детей постарше - используются глаза, которые малыши могут оторвать и проглотить спровоцировав удушье. ",
                false,"04.12.2024","05.12.2024", true),
            ProjectData(3,"Корова","Розово-белая игрушка коровы станет отличным подарком для детей постарше - используются глаза, которые малыши могут оторвать и проглотить спровоцировав удушье. ",
                false,"04.12.2024","05.12.2024", true),
            ProjectData(3,"Корова","Розово-белая игрушка коровы станет отличным подарком для детей постарше - используются глаза, которые малыши могут оторвать и проглотить спровоцировав удушье. ",
                false,"04.12.2024","05.12.2024", true),
            ProjectData(3,"Корова","Розово-белая игрушка коровы станет отличным подарком для детей постарше - используются глаза, которые малыши могут оторвать и проглотить спровоцировав удушье. ",
                false,"04.12.2024","05.12.2024", true),
        )
        val tabDataArrays:List<TabData> = listOf(
            TabData(0,"Начатые"),
            TabData(1,"Завершенные")
        )
        val navDataArrays = listOf(
            ScreenData.Projects,
            ScreenData.Categories,
            ScreenData.Line,
            ScreenData.Settings
        )
        val categoryArrays:List<CategoryData> = listOf(
            CategoryData(0,"Название категории"),
            CategoryData(1,"Одежда"),
            CategoryData(2,"Игрушки"),
        )
        val lineArray:List<LineData> = listOf(
            LineData(0,"Артур",2,10,"10.09.2024",R.drawable.lane_image,"К праздникам готовы!","Смогла успеть приготовить всем подарки."),
            LineData(1,"Влад",3,5,"11.09.2024",null,"Начинаю открывать мир вязания!",null)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var currentScreen by remember { mutableStateOf("Проекты") }
            CrochetickTheme {
                Scaffold (
                    bottomBar = { ProjectBottomBar(navController) },
                    floatingActionButton = {
                        if (currentScreen=="Проекты"){
                            FloatingActionButton(
                                onClick = {
                                    intent = Intent(this, ProjectWorkActivity::class.java)
                                    startActivity(intent)
                                },
                                shape = CircleShape,
                                modifier = Modifier.size(66.dp),

                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                                    modifier = Modifier.size(42.dp),
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = "Icon"
                                )
                            }
                        }
                    }
                ){innerPadding->
                    NavHost(
                        navController = navController,
                        startDestination = "projects",
                        enterTransition = {
                            fadeIn(animationSpec = tween(0))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(0))
                        },
                        popEnterTransition = {
                            fadeIn(animationSpec = tween(0))
                        },
                        popExitTransition = {
                            fadeOut(animationSpec = tween(0))
                        }
                    ){
                        composable(route = "projects"){
                            HomeScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                        }
                        composable(route = "category"){
                            CategoryScreen(navController,innerPadding, currentScreen = { currentScreen = it }, SchemesViewModel)
                        }
                        composable(route = "line"){
                            LineScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                        }
                        composable(route = "settings"){
                            SettingsScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                        }
                        composable(route = "notifications",){
                            NotificationsScreen(navController,innerPadding, currentScreen = { currentScreen = it },SettingsViewModel)
                        }
                        composable(route="showCategory") {
                            ShowCategory(navController, innerPadding, currentScreen = { currentScreen = it }, SchemesViewModel)
                        }
                        composable(route="showScheme") {
                            ShowScheme(navController, innerPadding, currentScreen = { currentScreen = it }, SchemesViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectBottomBar(navController: NavController){
    var selectedNavIndex by rememberSaveable() {
        mutableIntStateOf(0)
    }
    NavigationBar(
        containerColor = LowerNavig
    ) {
        navDataArrays.forEachIndexed{index, item ->
            NavigationBarItem(
                selected = selectedNavIndex==index,
                onClick = {
                    selectedNavIndex =index
                    navController.navigate(item.route)
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
                    selectedIconColor = com.example.crochetick.ui.theme.Text,
                    selectedTextColor = com.example.crochetick.ui.theme.Text,
                    unselectedIconColor = NavSelect,
                    unselectedTextColor = NavSelect
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf("Проекты") }
    val settingsViewModel:SettingsViewModel = viewModel()
    CrochetickTheme {
        Scaffold (
            bottomBar = { ProjectBottomBar(navController) },
            floatingActionButton = {
                if (currentScreen=="Проекты"){
                    FloatingActionButton(
                        onClick = {

                        },
                        shape = CircleShape,
                        modifier = Modifier.size(66.dp)
                        ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                            modifier = Modifier.size(42.dp),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Icon"
                        )
                    }
                }
            }
        ){innerPadding->
            NavHost(navController = navController, startDestination = "home"){
                composable(route = "home"){
                    HomeScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                }
                composable(route = "search"){
                    CategoryScreen(navController, innerPadding,currentScreen = { currentScreen = it })
                }
                composable(route = "line"){
                    LineScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                }
                composable(route = "settings"){
                    SettingsScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                }
                composable(route = "notifications") {
                    NotificationsScreen(navController,innerPadding,currentScreen = {currentScreen=it},settingsViewModel)
                }
            }
        }
    }
}