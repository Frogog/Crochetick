package com.example.crochetick.screen

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crochetick.ProjectBottomBar
import com.example.crochetick.R
import com.example.crochetick.dataClass.model.NotificationDBTable
import com.example.crochetick.state.SettingsState
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.BrightContrast
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.OnBackground
import com.example.crochetick.ui.theme.TextBright
import com.example.crochetick.ui.theme.TextSecond
import com.example.crochetick.viewModel.SettingsViewModel
import java.util.Calendar
import kotlin.time.TimeSource

@Composable
fun NotificationsScreen(navController: NavController,innerPadding:PaddingValues,currentScreen: (String) -> Unit, viewModel: SettingsViewModel){
    currentScreen("Уведомления")
    CrochetickTheme {
        Scaffold(
            topBar = { NotificationTopBar({navController.popBackStack()},{viewModel.updateShowDialog(true)}) },
        ) {innerPadding->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val uiState = viewModel.uiStateNotification.collectAsState()
                NotificationSwitch(viewModel)
                Spacer(Modifier.height(8.dp))
                //Text(uiState.value.selectedTime.get(Calendar.HOUR_OF_DAY).toString())
                NotificationList(uiState.value.notificationList,viewModel)
                InputExample(viewModel,uiState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestNotifications(title: String = "Уведомления",navController:NavController = rememberNavController(),viewModel: SettingsViewModel = viewModel()){
    CrochetickTheme {
        Scaffold(
            topBar = { NotificationTopBar(
                {navController.popBackStack()}
                ,{navController.navigate("")}
            )},
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
                val notificationArray:List<NotificationDBTable> = listOf(
                    NotificationDBTable(0,1,20,false),
                    NotificationDBTable(0,10,15,false),
                    NotificationDBTable(0,18,0,false)
                )
                NotificationList(notificationArray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExample(
//    onConfirm: () -> Unit,
//    onDismiss: () -> Unit,
    viewModel: SettingsViewModel= viewModel(),
    uiState: State<SettingsState>
) {
    CrochetickTheme {
        val uiState = viewModel.uiStateNotification.collectAsState()
        var selectedTime by remember { mutableStateOf(Calendar.getInstance()) }

        Column {
            if (uiState.value.showDialog) {
                BasicAlertDialog(
                    onDismissRequest = { viewModel.updateShowDialog(false) },
                    content = {
                        InputExampleNewest(
                            onConfirm = { hour, minute ->
                                uiState.value.selectedTime.set(Calendar.HOUR_OF_DAY, hour)
                                uiState.value.selectedTime.set(Calendar.MINUTE, minute)
                                val notification = NotificationDBTable(
                                    hour = uiState.value.selectedTime.get(Calendar.HOUR_OF_DAY),
                                    minute = uiState.value.selectedTime.get(Calendar.MINUTE)
                                )
                                viewModel.insertNotification(notification)
                                viewModel.updateShowDialog(false)
                            },
                            onDismiss = {
                                viewModel.updateShowDialog(false)
                            }
                        )
                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExampleNewest(
    onConfirm: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Surface(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(28.dp),
        color = Background
    ) {
        Box{
            Column(
                modifier = Modifier
                    .padding(24.dp)  // Увеличиваем внутренний отступ
                    .fillMaxWidth()  // Опционально: заполняем всю доступную ширину
            ) {
                Text(
                    "Введите время",
                    modifier = Modifier.padding(start = 12.dp ,bottom = 16.dp),  // Добавляем отступ после заголовка
                    style = MaterialTheme.typography.labelMedium
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TimeInput(
                        state = timePickerState,
                        modifier = Modifier.padding(vertical = 8.dp),  // Добавляем отступы вокруг TimeInput
                        colors = TimePickerDefaults.colors(
                            timeSelectorSelectedContentColor = TextBright,
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp),
                        horizontalArrangement = Arrangement.End // Изменено на SpaceBetween
                    ) {

                        TextButton(
                            onClick = {
                                onDismiss()
                            },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.padding() //Чуть меньше чем в дизайне
                        ) {
                            Text(
                                text = "Отмена",
                                style = MaterialTheme.typography.labelLarge,
                                color = TextSecond
                            )
                        }
                        TextButton(
                            onClick = {
                                onConfirm(timePickerState.hour, timePickerState.minute)
                            },
                            contentPadding = PaddingValues(0.dp),

                            modifier = Modifier.padding() //Чуть меньше чем в дизайне
                        ) {
                            Text(
                                text = "ОК",
                                style = MaterialTheme.typography.labelLarge,
                                color = TextSecond
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 140.dp)
                .background(
                    color = Background,
                    shape = RoundedCornerShape(4.dp)
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Часы",
                modifier = Modifier.offset(x = (-64).dp),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecond
            )
            Text(
                "Минуты",
                modifier = Modifier.offset(x = (24).dp),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecond
            )
        }
    }
}

@Composable
fun NotificationList(notificationArray:List<NotificationDBTable>, viewModel: SettingsViewModel= viewModel()){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(notificationArray){index, item ->
            NotificationCard(item, viewModel)
        }
    }
}


@Composable
fun NotificationCard(item:NotificationDBTable,viewModel: SettingsViewModel){
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
                item.hour.toString()+":"+item.minute.toString(),
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(
                onClick = {
                    viewModel.deleteNotification(
                        NotificationDBTable(
                            item.noticationId,
                            item.hour,
                            item.minute
                        )
                    )
                },
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
fun NotificationSwitch(viewModel: SettingsViewModel= viewModel()){
    val switchState by viewModel.switchState.collectAsState()
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            "Включить уведомления"
        )
        Switch(
            checked = switchState,
            onCheckedChange = {
                viewModel.setSwitchState(it)
                viewModel.toggleNotifications(it)
            }
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