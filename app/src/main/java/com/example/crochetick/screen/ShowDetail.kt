package com.example.crochetick.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crochetick.viewModel.ProjectDoViewModel
import com.example.crochetick.R
import com.example.crochetick.state.ProjectDoState
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.BrightContrast
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.NavSelect
import com.example.crochetick.ui.theme.OnBackground
import com.example.crochetick.ui.theme.TextSecond
import java.io.File

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShowDetail(navController: NavController, viewModel: ProjectDoViewModel){
    Scaffold(
        topBar = {
            viewModel.uiStateProjectDo.value.currentDetail?.let {
                SingleDetailTopBar(
                    title = it.titleDetail,
                    onBackClick = {
                        viewModel.doDetail()
                        viewModel.checkDoneDetail()
                        navController.popBackStack()
                    }
                )
            }
        }
    ) {innerPadding->
        val uiState = viewModel.uiStateProjectDo.collectAsState()
        MainContentShowDetail(innerPadding,viewModel,uiState)
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainContentShowDetail(innerPaddingValues: PaddingValues,viewModel: ProjectDoViewModel,uiState: State<ProjectDoState>){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(innerPaddingValues)
    ) {
        var scale by remember {
            mutableStateOf(1f)
        }
        var offset by remember {
            mutableStateOf(Offset.Zero)
        }

        var dp = 240.dp
        if (uiState.value.currentDetail?.schemaText =="") dp = 449.dp
        if (uiState.value.currentDetail?.schemaImage !=null){
            val imageFile = File(LocalContext.current.filesDir, "DetailImages/${uiState.value.currentDetail?.schemaImage}.jpg")
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(imageFile)
                    .build(),
                contentDescription = "Изображение",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

        if (uiState.value.currentDetail?.schemaText !=""){
            ScrollableText(
                uiState.value.currentDetail?.schemaText!!,
                paddingValues = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
            )
        }
        RowIndicator(viewModel,uiState)
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun testDetail(){
    CrochetickTheme {
        Scaffold {innerPadding->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(innerPadding)
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
                        "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj",
                    paddingValues = PaddingValues(vertical = 10.dp, horizontal = 16.dp)
                )
                //RowIndicator()
            }
        }
    }
}

@Composable
fun ScrollableText(
    text: String,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        val scrollState = rememberScrollState()

        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            style = MaterialTheme.typography.titleLarge
        )

        // Опционально: добавление скроллбара
//        VerticalScrollbar(
//            modifier = Modifier.align(Alignment.CenterEnd),
//            adapter = rememberScrollbarAdapter(scrollState)
//        )
    }
}
@Composable
fun RowIndicator(viewModel: ProjectDoViewModel,uiState: State<ProjectDoState>){
    var openAlertDialog = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Количество деталей:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
             uiState.value.doneDetails.toString() +"/"+uiState.value.countDetails.toString(),
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
                    onClick = {
                        viewModel.decreaseRowCount()
                    },
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
                if (uiState.value.doneDetails==uiState.value.countDetails){
                    Text(
                        uiState.value.countRow.toString(),
                        style = MaterialTheme.typography.displayLarge
                    )
                }
                else{
                    Text(
                        (uiState.value.doneRows-(uiState.value.countRow*uiState.value.doneDetails)).toString(),
                        style = MaterialTheme.typography.displayLarge
                    )
                }
                Button(
                    onClick = {
                        viewModel.increaseRowCount()
                    },
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
            if (uiState.value.doneDetails==uiState.value.countDetails){
                Text(
                    uiState.value.countRow.toString()+"/"+uiState.value.countRow.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            else{
                Text(
                    (uiState.value.doneRows-(uiState.value.countRow*uiState.value.doneDetails)).toString()+"/"+uiState.value.countRow.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        if (uiState.value.doneDetails==uiState.value.countDetails) {
            Text(
                "Все детали готовы",
                style = MaterialTheme.typography.labelLarge,
                color = TextSecond
            )
        }
        else{
            Button(
                onClick = { openAlertDialog.value = true },
                colors = ButtonDefaults.buttonColors(
                    contentColor = TextSecond,
                    containerColor = BrightContrast
                )
            ) {
                Text(
                    "Закончить деталь",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextSecond
                )
            }
        }
        if (openAlertDialog.value) {
            AlertDialogExample(
                onDismiss = { openAlertDialog.value = false },
                onConfirm = {
                    viewModel.skipDetail()
                    openAlertDialog.value = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogExample(
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
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
                onDismiss()
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
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
                        onConfirm()
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
    title:String,
    onBackClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 48.dp, bottom = 16.dp, start = 16.dp),
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
                title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}