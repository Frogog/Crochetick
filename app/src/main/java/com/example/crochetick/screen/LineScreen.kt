package com.example.crochetick.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme

@Composable
fun LineScreen(navController: NavController,innerPadding: PaddingValues,currentScreen: (String) -> Unit){
    currentScreen("Лента")
    CrochetickTheme {
        Column {
            LineTopBar("Лента")
        }
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

@Composable
fun LineTopBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(
                shadowElevation = 5f, // высота тени
                shape = RectangleShape,
                clip = false
            )
            .background(Background)
            .padding(top = 36.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
    }
}