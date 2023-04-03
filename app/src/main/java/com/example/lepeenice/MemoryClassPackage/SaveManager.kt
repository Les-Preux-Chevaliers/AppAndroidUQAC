package com.example.lepeenice.MemoryClassPackage

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

public class SaveManager private constructor(){

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

    fun saveDataToSharedPreferences_Player(context: Context, data: String) {
        val sharedPreferences = context.getSharedPreferences(PlayerKeySaved, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(PlayerKeySaved, data).apply()
        println("saved")
    }

    fun saveDataToSharedPreferences_GameManager(context: Context, data: String) {
        val sharedPreferences = context.getSharedPreferences(GameManagerKeySaved, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(GameManagerKeySaved, data).apply()
    }

    /**
     * LOAD LES DATA PAS TOUCHEEEEEEEEEEE !!!
     */
    fun loadDataFromSharedPreferences(context: Context) {
        val sharedPreferences_Player = context.getSharedPreferences(PlayerKeySaved, Context.MODE_PRIVATE)
        val sharedPreferences_GameManager = context.getSharedPreferences(GameManagerKeySaved, Context.MODE_PRIVATE)
        val resString_Player = sharedPreferences_Player.getString(PlayerKeySaved, "") ?: ""
        val resString_GameManager = sharedPreferences_GameManager.getString(GameManagerKeySaved, "") ?: ""

        if(resString_Player != ""){
            val DecodedJson_Player = Json.decodeFromString<Player>(resString_Player)
            Player.getInstance().loadSave(DecodedJson_Player)
        }

        if(resString_GameManager != ""){
            val DecodedJson_GameManager = Json.decodeFromString<GameManager>(resString_GameManager)
            GameManager.getInstance().loadSave(DecodedJson_GameManager)
        }else{
            GameManager.getInstance().createSwords()
        }
        println("loaded")
    }


}