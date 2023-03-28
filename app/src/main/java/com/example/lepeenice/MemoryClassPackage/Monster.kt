package com.example.lepeenice.MemoryClassPackage

@kotlinx.serialization.Serializable
open class Monster(var name: String, var hp: Float, var attack: Float, var defense: Int, var imageUri: Int, var scoreGiven: Int) {

    override fun toString(): String {
        return "Monster(name=$name, hp=$hp, attack=$attack, defense=$defense, imageUri=$imageUri, , scoreGiven=$scoreGiven)"
    }
}