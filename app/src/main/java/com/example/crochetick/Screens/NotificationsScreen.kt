package com.example.crochetick.Screens

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crochetick.DataClasses.ProjectData
import com.example.crochetick.DataClasses.UIData.ScreenData
import com.example.crochetick.ProjectBottomBar
import com.example.crochetick.R
import com.example.crochetick.SimpleTopBar
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.TextBright
import com.example.crochetick.ui.theme.TextSecond
import java.util.Calendar

@Composable
fun NotificationsScreen(navController: NavController,innerPadding:PaddingValues,currentScreen: (String) -> Unit){
    currentScreen("Уведомления")
    CrochetickTheme {
        Scaffold(
            topBar = { NotificationTopBar({navController.popBackStack()},{navController.navigate("")}) },
        ) {innerPadding->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NotificationSwitch()
                Spacer(Modifier.height(8.dp))
                val notificationArray:List<String> = listOf("20:10","11:59")
                NotificationList(notificationArray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestNotifications(title: String = "Уведомления",navController:NavController = rememberNavController()){
    CrochetickTheme {
        Scaffold(
            topBar = { NotificationTopBar({navController.popBackStack()},{navController.navigate("")}) },
            bottomBar = { ProjectBottomBar(navController) },
            modifier = Modifier.fillMaxSize()
        ) {innerPadding->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NotificationSwitch()
                Spacer(Modifier.height(8.dp))
                val notificationArray:List<String> = listOf("20:10","11:59")
                NotificationList(notificationArray)
            }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExample(
//    onConfirm: () -> Unit,
//    onDismiss: () -> Unit,
) {
    CrochetickTheme {
        val currentTime = Calendar.getInstance()

        val timePickerState = rememberTimePickerState(
            initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
            initialMinute = currentTime.get(Calendar.MINUTE),
            is24Hour = true,
        )
        Box{
            Column(
                modifier = Modifier.padding()
            ) {
                Text("Введите время")
                TimeInput(
                    state = timePickerState,
                )
                Row{
                    Button(onClick = {  }) {
                        Text("Отмена")
                    }
                    Button(onClick = {  }) {
                        Text("Ок")
                    }
                }
            }
        }
    }

}

@Composable
fun NotificationList(notificationArray:List<String>){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(notificationArray){index, item ->
            NotificationCard(item)
        }
    }
}


@Composable
fun NotificationCard(title: String){
    Card(
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 8.dp, bottom = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                title,
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(
                onClick = {},
                modifier = Modifier.size(48.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.minus_icon),
                    contentDescription = "AddNotification",
                    tint = TextSecond
                )
            }
        }
    }
}

@Composable
fun NotificationSwitch(){
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            "Включить уведомления",
        )
        Switch(
            checked = true,
            onCheckedChange = {}
        )
    }
}

@Composable
fun NotificationTopBar(
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 40.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 12.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(16.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                    contentDescription = "Localized description",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "Уведомления",
                style = MaterialTheme.typography.titleLarge
            )
        }
        IconButton(
            onClick = {onAddClick()},
            modifier = Modifier.size(30.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                contentDescription = "AddNotification",
                tint = com.example.crochetick.ui.theme.Text
            )
        }
    }
}