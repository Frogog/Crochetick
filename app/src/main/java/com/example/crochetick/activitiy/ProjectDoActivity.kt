package com.example.crochetick.activitiy

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crochetick.MainActivity
import com.example.crochetick.TrashCan.Usual
import com.example.crochetick.screen.DetailsAll
import com.example.crochetick.screen.ShowDetail
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.viewModel.ProjectDoViewModel

class ProjectDoActivity : ComponentActivity() {
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel: ProjectDoViewModel by viewModels()

            val projectId = intent.getLongExtra("projectId", -1)
            val projectTitle = intent.getStringExtra("projectTitle")?:"Деталь"

            viewModel.updateProject(projectId,projectTitle)
            CrochetickTheme {
                NavHost(
                    navController,
                    startDestination = "detailsAll",
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
                ) {
                    composable(route="detailsAll") {
                        val context = LocalContext.current
                        DetailsAll(
                            navController,
                            {
                                var mainActivityIntent = Intent(context, MainActivity::class.java)
                                val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                                mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                val runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE)
                                val isMainActivityRunning = runningTasks.any {
                                    it.baseActivity?.className == MainActivity::class.java.name
                                }
                                if (isMainActivityRunning) {
                                    finish()
                                    //Usual.Notification("MainActivity уже запущена", context)
                                } else {
                                    mainActivityIntent = Intent(context, MainActivity::class.java).apply {
                                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    }
                                    context.startActivity(mainActivityIntent)
                                }
                            },
                            viewModel)
                    }
                    composable(route="showDetail") {
                        ShowDetail(navController,viewModel)
                    }
                }
            }
        }
    }
}