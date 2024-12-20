package com.example.crochetick.screen

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crochetick.MainActivity
import com.example.crochetick.R
import com.example.crochetick.TrashCan.Usual
import com.example.crochetick.activitiy.ProjectDoActivity
import com.example.crochetick.dataClass.LineData
import com.example.crochetick.dataClass.model.ProjectDBTable
import com.example.crochetick.ui.theme.Background
import com.example.crochetick.ui.theme.CrochetickTheme
import com.example.crochetick.ui.theme.TextSecond
import java.io.File
import kotlin.concurrent.timerTask

@Composable
fun LineScreen(navController: NavController,innerPadding: PaddingValues,currentScreen: (String) -> Unit){
    currentScreen("Лента")
    CrochetickTheme {
        Column(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            LineTopBar("Лента",innerPadding)
            LineList()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LineList(projectDataArray:List<LineData> = MainActivity.lineArray, navController: NavController = rememberNavController()){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(projectDataArray){index, item ->
            if (index==0) Spacer(modifier = Modifier.height(8.dp))
            LineCard(
                item = item,
                navController
            )
            if (index== projectDataArray.size-1) Spacer(modifier = Modifier.height(8.dp).shadow(8.dp))
        }
    }
}

@Composable
fun LineCard(item: LineData, navController: NavController){
    CrochetickTheme {
        val hour = item.hour.toString().padStart(2, '0')
        val minute = item.minute.toString().padStart(2, '0')
        val context = LocalContext.current
        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
            elevation = CardDefaults.elevatedCardElevation(3.dp),
            onClick = {
            }
        )
        {
            Column {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(
                        Modifier
                            .size(40.dp)
                            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(100.dp)),
                    )
                    Column(
                        modifier = Modifier.padding(start = 12.dp)
                    ){
                        Text(
                            item.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            "$hour:$minute",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            item.date,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
                if (item.image!=null){
                    Image(
                        painter = painterResource(id = R.drawable.lane_image),
                        contentDescription = "Изображение",
                        modifier = Modifier.height(250.dp).fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillHeight,
                    )
                }
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            item.title,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        if (item.text!=null){
                            Text(
                                item.text,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

//if (item.imageName!=""){
//    Column(modifier= Modifier.padding(end = 8.dp)) {
//        if (item.imageName!=null){
//            val imageFile = File(context.filesDir, "ProjectImages/${item.imageName}.jpg")
//            AsyncImage(
//                model = ImageRequest
//                    .Builder(context)
//                    .data(imageFile)
//                    .build(),
//                contentDescription = "Изображение",
//                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop,
//                //error = painterResource(id = R.drawable.error_image)
//            )
//        }
//    }
//}
//Column{
//    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp), Arrangement.SpaceBetween){
//        Text(
//            text = item.title,
//            style = MaterialTheme.typography.titleSmall
//        )
//        Text(
//            text = Usual.EnToRu(item.dateStart),
//            color = TextSecond,
//            style = MaterialTheme.typography.bodySmall,
//        )
//    }
//    Row(modifier = Modifier.fillMaxWidth()){
//        item.description?.let {
//            Text(
//                text = it,
//                color = TextSecond,
//                style = MaterialTheme.typography.bodySmall
//            )
//        }
//    }
//}

@Composable
fun LineTopBar(title: String,innerPadding: PaddingValues) {
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