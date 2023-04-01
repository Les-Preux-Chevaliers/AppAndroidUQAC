package com.example.lepeenice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lepeenice.MemoryClassPackage.GameManager
import com.example.lepeenice.MemoryClassPackage.Player
import com.example.lepeenice.MemoryClassPackage.SaveManager
import com.example.lepeenice.ui.theme.LEpeeNiceTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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
                        Spacer(modifier = Modifier.height(16.dp))

                        SaveManager.getInstance().loadDataFromSharedPreferences(LocalContext.current)

                        val Player = Player.getInstance()
                        Text(text = "Pseudo : "+Player.pseudo)

                        //SERIALIZATION
                        //val EncodedJson = Json.encodeToString(Player)
                        //SaveManager.getInstance().saveDataToSharedPreferences_Player(LocalContext.current, EncodedJson)

                        Spacer(modifier = Modifier.height(16.dp))


                        // Zone d'entrée de texte
                        val textState = remember { mutableStateOf(TextFieldValue()) }
                        TextField(
                            value = textState.value,
                            onValueChange = { textState.value = it }
                        )
                        Text("The textfield has this text: " + textState.value.text)
                    }
                }
            }
        }
    }
}