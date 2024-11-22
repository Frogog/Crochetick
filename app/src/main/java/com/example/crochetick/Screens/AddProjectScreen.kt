package com.example.crochetick.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.crochetick.ui.theme.CrochetickTheme

@Composable
fun AddProjectScreen(navController: NavController, innerPadding: PaddingValues, currentScreen: (String) -> Unit){
    currentScreen("Добавить новый проект")
    CrochetickTheme {
        Column(modifier = Modifier.padding(innerPadding)){
            Text("asdasdadadasd")
        }
    }
}