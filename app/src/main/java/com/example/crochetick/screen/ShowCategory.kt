package com.example.crochetick.screen

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crochetick.R
import com.example.crochetick.TrashCan.Usual
import com.example.crochetick.activitiy.ProjectDoActivity
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.dataClass.requestData.SchemesResponse
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.TextBright
import com.example.crochetick.ui.theme.TextSecond
import com.example.crochetick.viewModel.SchemesViewModel
import java.io.File

@Composable
fun ShowCategory(navController: NavController, innerPadding: PaddingValues , currentScreen: (String) -> Unit, viewModel: SchemesViewModel){
    CrochetickTheme {
        val uiState = viewModel.uiState.collectAsState()
        Column(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            ShowCategoryTopBar(
                uiState.value.categoryTitle,
            ) { navController.popBackStack() }
            ShowCategoryList(uiState.value.schemes,navController,viewModel)
        }
    }
}

@Composable
fun ShowCategoryList(schemesDataArray:List<SchemesResponse>,navController: NavController,viewModel: SchemesViewModel){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(schemesDataArray){index, item ->
            if (index==0) Spacer(modifier = Modifier.height(8.dp))
            ShowCategoryCard(
                item = item,
                navController,
                viewModel
            )
            if (index== schemesDataArray.size-1) Spacer(modifier = Modifier.height(8.dp).shadow(8.dp))
        }
    }
}

@Composable
fun ShowCategoryCard(item: SchemesResponse, navController: NavController,viewModel: SchemesViewModel){
    CrochetickTheme {
        val context: Context = LocalContext.current
        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
            elevation = CardDefaults.elevatedCardElevation(3.dp),
            onClick = {
                viewModel.updateSchemeId(item.id)
                navController.navigate("showScheme")
            }
        )
        {
            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)){
                if (item.image!=""){
                    Column(modifier= Modifier.padding(end = 8.dp)) {
                        if (item.image!=null){
                            AsyncImage(
                                model = ImageRequest
                                    .Builder(context)
                                    .data(Usual.getBitmapFromBase64(item.image))
                                    .build(),
                                contentDescription = "Изображение",
                                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
                Column{
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),Arrangement.SpaceBetween){
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()){
                        item.description?.let {
                            Text(
                                text = it,
                                color = TextSecond,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowCategoryTopBar(
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