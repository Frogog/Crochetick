package com.example.crochetick.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crochetick.MainActivity
import com.example.crochetick.R
import com.example.crochetick.dataClass.CategoryData
import com.example.crochetick.dataClass.requestData.CategoriesResponse
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.TextSecond
import com.example.crochetick.viewModel.SchemesViewModel

@Composable
fun CategoryScreen(navController: NavController, innerPadding: PaddingValues, currentScreen: (String) -> Unit, viewModel: SchemesViewModel = viewModel()){
    currentScreen("Схемы")
    CrochetickTheme {
        Column(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            CategoryTopBar("Схемы",innerPadding)
            SchemesMainContent(viewModel ,navController)
        }
    }
}

@Composable
fun SchemesMainContent(viewModel: SchemesViewModel = viewModel(),navController: NavController = rememberNavController()){
    val uiState = viewModel.uiState.collectAsState()
    CategoryList(uiState.value.categories,viewModel,navController)
}

@Composable
fun CategoryList(schemesList:List<CategoriesResponse>, viewModel: SchemesViewModel= viewModel(), navController: NavController){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(schemesList){index, item ->
            CategoryCard(item, viewModel, navController)
        }
    }
}

@Composable
fun CategoryCard(item: CategoriesResponse, viewModel: SchemesViewModel, navController: NavController){
    ElevatedCard(
        onClick = {
            viewModel.updateCategory(item.id,item.name)
            viewModel.updateSchemesByCategory()
            navController.navigate("showCategory")
        },
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(3.dp),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 8.dp, bottom = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                item.name,
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(
                onClick = {},
                modifier = Modifier.size(48.dp).padding(start = 8.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.inside_icon),
                    contentDescription = "AddNotification",
                    tint = TextSecond
                )
            }
        }
    }
}

@Composable
fun CategoryTopBar(title: String,innerPadding: PaddingValues) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(
                shadowElevation = 5f, // высота тени
                shape = RectangleShape,
                clip = false
            )
            .background(Background)
            .padding(top = innerPadding.calculateTopPadding()+12.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSchemes(){
    CrochetickTheme {
        Column {
            val viewModel:SchemesViewModel = viewModel()
            CategoryTopBar("Схемы", innerPadding = PaddingValues(0.dp))
            SchemesMainContent(viewModel)
        }
    }
}