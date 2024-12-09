package com.example.crochetick.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crochetick.ProjectBottomBar
import com.example.crochetick.R
import com.example.crochetick.SimpleTopBar
import com.example.crochetick.ui.theme.CrochetickTheme

@Composable
fun SettingsScreen(navController: NavController,innerPadding: PaddingValues,currentScreen: (String) -> Unit){
    currentScreen("Настройки")
    CrochetickTheme {
        SettingsMainContent(navController,innerPadding)
    }
}

@Composable
fun SettingsMainContent(navController: NavController = rememberNavController(),innerPadding: PaddingValues){
    Column(
        modifier = Modifier.padding(innerPadding).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {},
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape,
            modifier = Modifier.padding(bottom = 4.dp, top = 16.dp).size(100.dp)
        ) {
        }
        Text(
            "SiName",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        CardSettingsAll(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun TestSettings(navController: NavController= rememberNavController()){
    CrochetickTheme {
        Scaffold(
            topBar = { SimpleTopBar("Настройки") },
            bottomBar = { ProjectBottomBar(navController)},
            modifier = Modifier.fillMaxSize()
        ) {innerPadding->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {},
                    contentPadding = PaddingValues(0.dp),
                    shape = CircleShape,
                    modifier = Modifier.padding(bottom = 4.dp, top = 16.dp).size(100.dp)
                ) {
                }
                Text(
                    "SiName",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                CardSettingsAll(navController)
            }
        }
    }
}

@Composable
fun CardSettingsAll(navController: NavController){
    CrochetickTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            CardSettings("Аккаунт",{})
            CardSettings("Уведомления",{navController.navigate("notifications")})
            CardSettings("Статистика",{})
        }

    }
}

@Composable
fun CardSettings(title:String="Аккаунт", openSetting:()->Unit){
    Card(
        modifier = Modifier.fillMaxWidth().height(64.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(top = 8.dp, bottom = 8.dp, end = 8.dp, start = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                title,
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(
                onClick = {openSetting()},
                modifier = Modifier.size(48.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.inside_icon),
                    contentDescription = "Localized description",
                    modifier = Modifier
                )
            }
        }
    }
}