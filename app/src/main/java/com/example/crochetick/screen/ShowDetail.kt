package com.example.crochetick.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crochetick.viewModel.ProjectDoViewModel
import com.example.crochetick.R
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.BrightContrast
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.NavSelect
import com.example.crochetick.ui.theme.OnBackground
import com.example.crochetick.ui.theme.TextSecond

@Composable
fun ShowDetail(navController: NavController, onBack:()->Unit, viewModel: ProjectDoViewModel){
    Scaffold(
        topBar = {
            SingleDetailTopBar(onBack)
        }
    ) {innerPadding->
        MainContentShowDetail(innerPadding,)
    }
}

@Composable
fun MainContentShowDetail(innerPaddingValues: PaddingValues,){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(innerPaddingValues)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cow),
            contentDescription = "Изображение",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        ScrollableText("asdsadadasdadadd" +
                "sadadad" +
                "sadadas" +
                "dadsadasd" +
                "adsasdas" +
                "dasda" +
                "asdad" +
                "juju j njn n nm nm mn "+
                "adsadadadadasdasd"+
                "aldkalkdmadlkmadandjkadkjahduadhadiadadboadbadbadaodasn"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"
        )
        RowIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun testDetail(){
    CrochetickTheme {
        Scaffold {innerPadding->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cow),
                    contentDescription = "Изображение",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )
                ScrollableText("asdsadadasdadadd" +
                        "sadadad" +
                        "sadadas" +
                        "dadsadasd" +
                        "adsasdas" +
                        "dasda" +
                        "asdad" +
                        "juju j njn n nm nm mn "+
                        "adsadadadadasdasd"+
                        "aldkalkdmadlkmadandjkadkjahduadhadiadadboadbadbadaodasn"+
                        "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"
                )
                RowIndicator()
            }
        }
    }
}

@Composable
fun ScrollableText(
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = 225.dp
) {
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        val scrollState = rememberScrollState()

        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            style = MaterialTheme.typography.titleLarge
        )

//        // Опционально: добавление скроллбара
//        VerticalScrollbar(
//            modifier = Modifier.align(Alignment.CenterEnd),
//            adapter = rememberScrollbarAdapter(scrollState)
//        )
    }
}
@Composable
fun RowIndicator(){
    val openAlertDialog = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Количество деталей:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            "0/1",
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            modifier = Modifier.padding(horizontal = 50.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    onClick = {},
                    contentPadding = PaddingValues(0.dp),
                    shape = CircleShape,
                    modifier = Modifier.size(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = TextSecond,
                        containerColor = BrightContrast
                    )
                ) {
                    Text(
                        "—",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                Text(
                    "0",
                    style = MaterialTheme.typography.displayLarge
                )
                Button(
                    onClick = {},
                    contentPadding = PaddingValues(0.dp),
                    shape = CircleShape,
                    modifier = Modifier.size(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = TextSecond,
                        containerColor = BrightContrast
                    )
                ) {
                    Text(
                        "+",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
            Text(
                "0/25",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Button(
            onClick = {openAlertDialog.value = true },
            colors = ButtonDefaults.buttonColors(
                contentColor = TextSecond,
                containerColor = BrightContrast
            )
        ){
            Text(
                "Закончить деталь",
                style = MaterialTheme.typography.labelSmall
            )
        }
        if (openAlertDialog.value) {
//            AlertDialogExample(
//                onDismissRequest = { openAlertDialog.value = false },
//                onConfirmation = {
//                    openAlertDialog.value = false
//                    println("Confirmation registered")
//                },
//                dialogTitle = "Alert dialog example",
//                dialogText = "This is an example of an alert dialog with buttons.",
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogExample(

) {
    CrochetickTheme {
        AlertDialog(
            title = {
                Text("Слишком мало рядов")
            },
            text = {
                Text("Вы уверены, что хотите закончить деталь?")
            },
            onDismissRequest = {

            },
            dismissButton = {
                TextButton(
                    onClick = {

                    }
                ) {
                    Text(
                        "Отмена",
                        color = TextSecond,
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {

                    }
                ) {
                    Text(
                        "Ок",
                        color = NavSelect
                    )
                }
            },
            containerColor = OnBackground,

        )
    }
}

@Composable
fun SingleDetailTopBar(
    onBackClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 44.dp, bottom = 8.dp, start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 10.dp)
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
                "Тело",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}