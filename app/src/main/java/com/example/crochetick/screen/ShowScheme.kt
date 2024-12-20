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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.crochetick.R
import com.example.crochetick.activitiy.ProjectDoActivity
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
        ShowSchemeContent(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSchemeContent(navController: NavController = rememberNavController(),id:Long=1, title: String="Flower"){
    Column {
        var dp = 331.dp
        if (true) dp = 645.dp
        val context = LocalContext.current
        val activity = context as ComponentActivity
        val intent = remember {
            Intent(context, ProjectDoActivity::class.java).apply {
                putExtra("projectId", id)
                putExtra("projectTitle", title)
            }
        }
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
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj"+
                "djndnakdnkadnjadnkandjdkadajsbdkadjbadkabdjhbsdkadnbjakndkjadandkjasndkandkaj",
            paddingValues = PaddingValues(top = 10.dp, start = 16.dp, end = 16.dp),
            modifier = Modifier.weight(1f)
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