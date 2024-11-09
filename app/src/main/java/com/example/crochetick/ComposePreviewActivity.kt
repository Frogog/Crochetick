package com.example.crochetick
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.crochetick.ui.theme.CrochetickTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewProjectTopBar(){
    CrochetickTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            CustomProjectTopBar(
                title = "Проекты"
            )
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun ProjectTabRowPreview(){
    CrochetickTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ){
            Column {
            }
        }
    }
}

