package com.example.lepeenice.MemoryClassPackage

import kotlinx.serialization.Serializable

@Serializable
public class Player private constructor() {

    var pseudo: String = "PlayerName"
    var sword: Sword = Sword("BaseSword", 1.0f)
    var hp: Float = 100.0f
    var score: Int = 0

    companion object {
        private var instance: Player? = null

        fun getInstance(): Player {
            if (instance == null) {
                instance = Player()
            }
            return instance as Player
        }
    }

    fun doSomething() {
        // Fait quelque chose ici
    }
}