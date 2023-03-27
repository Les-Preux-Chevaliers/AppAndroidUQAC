package com.example.lepeenice.MemoryClassPackage

@kotlinx.serialization.Serializable
open class Monster(var name: String, var hp: Float, var attack: Float, var defense: Int, var imageUri: String?) {
}