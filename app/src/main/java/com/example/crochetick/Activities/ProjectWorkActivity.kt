package com.example.crochetick.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crochetick.Screens.HomeScreen
import com.example.crochetick.Screens.LineScreen
import com.example.crochetick.Screens.SearchScreen
import com.example.crochetick.Screens.SettingsScreen
import com.example.crochetick.ui.theme.CrochetickTheme

class ProjectWorkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var currentScreen by remember { mutableStateOf("Проекты") }
            CrochetickTheme {
                NavHost(navController = navController, startDestination = "addProject"){
                    composable(route = "addProject"){

                    }
                    composable(route = "addDetail"){

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