package com.example.lepeenice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lepeenice.ui.theme.LEpeeNiceTheme

class MemoryClass {

    companion object {

        //Zone de Julien
        @Preview(name = "MemoryPreview",showBackground = true)
        @Composable
        fun MemoryPreview() {
            LEpeeNiceTheme {
                Surface(modifier = Modifier.fillMaxSize()){
                    Column() {
                        Text(text = "Memory Preview")
                        Text(text = "Version : 0.0.1")
                    }
                }
            }
        }
    }
}