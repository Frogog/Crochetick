package com.example.crochetick

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
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
import com.example.crochetick.screen.HomeScreen
import com.example.crochetick.screen.LineScreen
import com.example.crochetick.screen.NotificationsScreen
import com.example.crochetick.screen.SearchScreen
import com.example.crochetick.screen.SettingsScreen
import com.example.crochetick.ui.theme.LowerNavig
import com.example.crochetick.ui.theme.NavSelect
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.viewModel.SettingsViewModel


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
            ScreenData.Schemes,
            ScreenData.Line,
            ScreenData.Settings
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var currentScreen by remember { mutableStateOf("Проекты") }
            val settingsViewModel:SettingsViewModel = viewModel()
            CrochetickTheme {
                Scaffold (
                    topBar = {
                        when (currentScreen) {
                            "Проекты", "Схемы", "Лента", "Настройки" -> SimpleTopBar(currentScreen)
                            else -> null
                        }
                    },
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
                    NavHost(navController = navController, startDestination = "projects"){
                        composable(route = "projects"){
                            HomeScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                        }
                        composable(route = "schemes"){
                            SearchScreen(navController,innerPadding,currentScreen = { currentScreen = it })
                        }
                        composable(route = "line"){
                            LineScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                        }
                        composable(route = "settings"){
                            SettingsScreen(navController,innerPadding, currentScreen = { currentScreen = it })
                        }
                        composable(
                            route = "notifications",
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(150)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(150)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(150)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(150)
                                )
                            }
                        ){
                            NotificationsScreen(navController,innerPadding, currentScreen = { currentScreen = it },settingsViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomProjectTopBar(topBarCreator: @Composable () -> Unit){
    topBarCreator()
}

@Composable
fun SimpleTopBar(title: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 42.dp,bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
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
    //val backStackEntry = navController.currentBackStackEntryAsState()  Нужен может быть для (выбор между реализацией в bottomNav или во внешней) selected = backStackEntry.value?.destination?.route == screen.route,
    var currentScreen by remember { mutableStateOf("Проекты") }
    val settingsViewModel:SettingsViewModel = viewModel()
    CrochetickTheme {
        Scaffold (
            topBar = { CustomProjectTopBar{ SimpleTopBar(currentScreen) } },
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
                    SearchScreen(navController,innerPadding, currentScreen = { currentScreen = it })
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