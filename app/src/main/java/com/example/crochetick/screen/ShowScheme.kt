package com.example.crochetick.screen

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crochetick.R
import com.example.crochetick.TrashCan.Usual
import com.example.crochetick.activitiy.ProjectDoActivity
import com.example.crochetick.dataClass.requestData.SchemesResponse
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.TextBright
import com.example.crochetick.viewModel.SchemesViewModel
import java.io.File

@Composable
fun ShowScheme(navController: NavController, innerPadding: PaddingValues, currentScreen: (String) -> Unit, viewModel: SchemesViewModel){
    Column(
        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
    ) {
        ShowSchemeTopBar(
            "Игрушка одна",
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
        if(scheme!=null){
            var imageDp = 331.dp
            var textDp = 330.dp
            if (scheme.description =="") imageDp = 661.dp
            if (scheme.image==null) textDp = 661.dp
            val context = LocalContext.current
            val activity = context as ComponentActivity
            val intent = remember {
                Intent(context, ProjectDoActivity::class.java).apply {
                    putExtra("projectId", scheme.id.toLong())
                    putExtra("projectTitle", scheme.name)
                }
            }
            if (scheme.image!=null){
                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data(Usual.getBitmapFromBase64(scheme.image))
                        .build(),
                    contentDescription = "Изображение",
                    modifier = Modifier.fillMaxWidth().height(imageDp).padding(top = 8.dp),
                    contentScale = ContentScale.FillHeight,
                )
            }
            ScrollableText(scheme.description.toString(),
                paddingValues = PaddingValues(top = 10.dp, start = 16.dp, end = 16.dp),
                modifier = Modifier.height(textDp)
            )
            FilledTonalButton(
                onClick = {
                    activity.finish()
                    context.startActivity(intent)
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
            .padding(top = 36.dp, bottom = 16.dp, start = 16.dp),
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