package com.example.crochetick.activitiy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crochetick.screen.AddDetailScreen
import com.example.crochetick.screen.AddProjectScreen
import com.example.crochetick.ui.theme.CrochetickTheme

class ProjectWorkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            //var currentScreen by remember { mutableStateOf("Проекты") }
            val viewModel:ProjectWorkSharedViewModel by viewModels()
            CrochetickTheme {
                NavHost(
                    navController = navController,
                    startDestination = "addProject",
                    enterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(300)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(300)
                        )
                    }
                ){
                    composable(route = "addProject"){
                        AddProjectScreen(navController,
                            {finish()},
                            {viewModel.validateFormProject()},
                            viewModel)
                    }
                    composable(route = "addDetail"){
                        AddDetailScreen(navController,
                            {
                                navController.popBackStack()
                                viewModel.resetFormDetail()
                            },
                            {
                                if (viewModel.validateFormDetail()){
                                    navController.popBackStack()
                                    viewModel.resetFormDetail()
                                }
                            },
                            viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrochetickTheme {
        Greeting("Android")
    }
}