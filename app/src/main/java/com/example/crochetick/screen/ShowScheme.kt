package com.example.crochetick.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crochetick.R
import com.example.crochetick.TrashCan.Usual
import com.example.crochetick.activitiy.ProjectDoActivity
import com.example.crochetick.dataClass.model.DetailDBTable
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.dataClass.requestData.SchemesResponse
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.viewModel.SchemesViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDate

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShowScheme(navController: NavController, innerPadding: PaddingValues, currentScreen: (String) -> Unit, viewModel: SchemesViewModel){
    Column(
        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
    ) {
        ShowSchemeTopBar(
            viewModel.uiState.value.schemeTitle,
        ) { navController.popBackStack() }
        ShowSchemeContent(navController,viewModel=viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSchemeContent(navController: NavController = rememberNavController(), viewModel: SchemesViewModel = viewModel(), id:Long=1, title: String="Flower"){
    Column(
    ) {
        val uiState = viewModel.uiState.collectAsState()
        val schemes = uiState.value.schemes
        val scheme:SchemesResponse? = schemes.firstOrNull{ it.id == uiState.value.schemeId }
        val bitmap by remember { mutableStateOf(scheme?.image?.let { Usual.getBitmapFromBase64(it) }) }
        if(scheme!=null){
            var imageDp = 331.dp
            var textDp = 219.dp
            if (scheme.description =="") imageDp = 550.dp
            if (scheme.image==null) textDp = 550.dp
            val context = LocalContext.current
            val activity = context as ComponentActivity
            LaunchedEffect(viewModel) {
                viewModel.navigationEvent.collect { (projectId, projectTitle) ->
                    val intent = Intent(context, ProjectDoActivity::class.java).apply {
                        putExtra("projectId", projectId)
                        putExtra("projectTitle", projectTitle)
                    }
                    activity.finish()
                    context.startActivity(intent)
                }
            }
            if (scheme.image!=null){
                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data(bitmap)
                        .build(),
                    contentDescription = "Изображение",
                    modifier = Modifier.fillMaxWidth().height(imageDp).padding(top = 8.dp),
                    contentScale = ContentScale.FillHeight,
                )
            }
            if (scheme.description!=null){
                ScrollableText(scheme.description.toString(),
                    paddingValues = PaddingValues(top = 10.dp, start = 16.dp, end = 16.dp),
                    modifier = Modifier.height(textDp)
                )
            }
            else{
                ScrollableText("",
                    paddingValues = PaddingValues(top = 10.dp, start = 16.dp, end = 16.dp),
                    modifier = Modifier.height(textDp)
                )
            }
            FilledTonalButton(
                onClick = {
                    var imageNameProject:String?= null
                    if (scheme.image!=null){
                        imageNameProject = Usual.getBitmapFromBase64(scheme.image)
                            ?.let { saveImageFromBitmap(it, context,"ProjectImages") }
                    }
                    val projectDBTable = ProjectDBTable(
                        title = scheme.name,
                        description = scheme.description,
                        dateStart = LocalDate.now().toString(),
                        dateEnd = null,
                        imageName =imageNameProject
                    )
                    val details = mutableListOf<DetailDBTable>()
                    scheme.details.forEach{item->
                        var imageNameDetail:String?= null
                        var textDetail = ""
                        if (item.schema_image!=null){
                            imageNameDetail = Usual.getBitmapFromBase64(item.schema_image)
                                ?.let { saveImageFromBitmap(it, context,"DetailImages") }
                        }
                        if (item.schema_text!=null) textDetail = item.schema_text.toString()
                        details += DetailDBTable(
                            projectIdFK = 0,
                            titleDetail = item.name,
                            countDetails = item.details_number,
                            countRow = item.rows_number,
                            doneDetails = 0,
                            doneRows = 0,
                            schemaImage = imageNameDetail,
                            schemaText = textDetail
                        )
                    }
                    viewModel.importSchemeToProject(projectDBTable, details)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text("Начать проект")
            }
        }

    }

}

fun saveImageFromBitmap(bitmap: Bitmap, context: Context, folderName: String): String {
    val dir = File(context.filesDir, folderName).apply {
        if (!exists()) mkdirs()
    }

    val timestamp = System.currentTimeMillis()
    val imageName = "IMG_$timestamp" // Добавляем расширение

    val imageFile = File(dir, "$imageName.jpg")

    try {
        FileOutputStream(imageFile).use { output ->
            if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, output)) {
                throw IOException("Ошибка при сжатии изображения")
            }
        }

        return imageName

    } catch (e: Exception) {
        Log.e("SaveImage", "Ошибка при сохранении изображения", e)
        throw e
    }
}

@Composable
fun ShowSchemeTopBar(
    title:String,
    onBackClick: () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(
                shadowElevation = 5f,
                shape = RectangleShape,
                clip = false
            )
            .background(Background)
            .padding(top = 46.dp, bottom = 16.dp, start = 16.dp),
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