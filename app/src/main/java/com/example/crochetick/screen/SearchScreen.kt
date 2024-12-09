package com.example.crochetick.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.crochetick.ui.theme.CrochetickTheme

@Composable
fun SearchScreen(navController: NavController,innerPadding: PaddingValues,currentScreen: (String) -> Unit){
    currentScreen("Схемы")
    CrochetickTheme {
//        Scaffold (
//            topBar = { CustomProjectTopBar("Схемы") },
//            bottomBar = { ProjectBottomBar(navController) }
//        ){innerPadding->
//            Column(modifier = Modifier.padding(innerPadding)){
//                Text("SearchScreen")
//            }
//        }
        Text("LineScreen")
    }
}