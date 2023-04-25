package com.appcovizzi.lepeenice.MemoryClassPackage

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

public class SaveManager private constructor() {

    private val PlayerKeySaved: String = "LepeeniceSave_Player"
    private val GameManagerKeySaved: String = "LepeeniceSave_GameManager"

    companion object {
        private var instance: SaveManager? = null

        fun getInstance(): SaveManager {
            if (instance == null) {
                instance = SaveManager()
            }
            return instance as SaveManager
        }
    }

    private val REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 1

    fun saveDataToSharedPreferences_Player(context: Context, data: String) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        if (hasPermission) {
            val sharedPreferences =
                context.getSharedPreferences(PlayerKeySaved, Context.MODE_PRIVATE)
            sharedPreferences.edit().putString(PlayerKeySaved, data).apply()
//            println("saved player")
        } else {
            // L'autorisation d'écriture externe n'est pas encore accordée, donc on demande la permission à l'utilisateur
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION
            )
            // Afficher un message indiquant le problème
            Toast.makeText(
                context,
                "Autorisation d'écriture externe requise pour sauvegarder les données",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun saveDataToSharedPreferences_GameManager(context: Context, data: String) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        if (hasPermission) {
            val sharedPreferences =
                context.getSharedPreferences(GameManagerKeySaved, Context.MODE_PRIVATE)
            sharedPreferences.edit().putString(GameManagerKeySaved, data).apply()
//            println("saved GM")
        }
    }

    /**
     * LOAD LES DATA PAS TOUCHEEEEEEEEEEE !!!
     */
    fun loadDataFromSharedPreferences(context: Context) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        if (hasPermission) {
            val sharedPreferences_Player =
                context.getSharedPreferences(PlayerKeySaved, Context.MODE_PRIVATE)
            val sharedPreferences_GameManager =
                context.getSharedPreferences(GameManagerKeySaved, Context.MODE_PRIVATE)
            val resString_Player = sharedPreferences_Player.getString(PlayerKeySaved, "") ?: ""
            val resString_GameManager =
                sharedPreferences_GameManager.getString(GameManagerKeySaved, "") ?: ""

            if (resString_Player != "") {
                val DecodedJson_Player = Json.decodeFromString<Player>(resString_Player)
                Player.getInstance().loadSave(DecodedJson_Player)
            }

            if (resString_GameManager != "") {
                val DecodedJson_GameManager =
                    Json.decodeFromString<GameManager>(resString_GameManager)
                GameManager.getInstance().loadSave(DecodedJson_GameManager)
            } else {
                GameManager.getInstance().createSwords()
            }
            //println("loaded")
        }else{
            GameManager.getInstance().createSwords()
        }
    }


}