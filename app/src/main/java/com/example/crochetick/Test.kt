package com.example.crochetick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crochetick.ui.theme.CrochetickTheme

class TestViewModel: ViewModel(){
    private val _nameText = MutableLiveData<String>()
    val nameText: LiveData<String> = _nameText

    fun setNameText(nameText:String){
        _nameText.value = nameText
    }
}


class Test : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel:TestViewModel by viewModels()
        setContent {
            CrochetickTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    
                }
                Text("asdasd", modifier = Modifier.padding(top=100.dp))
            }
        }
    }
}