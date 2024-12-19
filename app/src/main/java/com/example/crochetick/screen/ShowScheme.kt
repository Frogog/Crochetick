package com.example.crochetick.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crochetick.R
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
            {navController.popBackStack()},
        )
        ShowSchemeContent()
    }
}

@Composable
fun ShowSchemeContent(){
    val context = LocalContext.current
    val imageFile = File(context.filesDir, "ProjectImages/IMG_1733890380767.jpg")
    AsyncImage(
        model = ImageRequest
            .Builder(context)
            .data(imageFile)
            .build(),
        contentDescription = "Изображение",
        modifier = Modifier.fillMaxWidth().height(331.dp).padding(top = 8.dp),
        contentScale = ContentScale.FillHeight,
    )
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