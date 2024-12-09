package com.example.crochetick.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.crochetick.ui.theme.CrochetickTheme

@Composable
fun LineScreen(navController: NavController,innerPadding: PaddingValues,currentScreen: (String) -> Unit){
    currentScreen("Лента")
    CrochetickTheme {
        Text("LineScreen")
//        Scaffold (
//            topBar = { CustomProjectTopBar("Схемы") },
//            bottomBar = { ProjectBottomBar(navController) }
//        ){innerPadding->
//            Column(modifier = Modifier.padding(innerPadding)){
//                Text("LineScreen")
//            }
//        }
    }
}