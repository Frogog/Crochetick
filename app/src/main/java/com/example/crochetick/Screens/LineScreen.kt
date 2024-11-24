package com.example.crochetick.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.crochetick.CustomProjectTopBar
import com.example.crochetick.ProjectBottomBar
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