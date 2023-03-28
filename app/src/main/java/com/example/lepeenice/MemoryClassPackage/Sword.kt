package com.example.lepeenice.MemoryClassPackage

@kotlinx.serialization.Serializable
open class Sword(var name: String, var damage: Float) {

    override fun toString(): String {
        return "Sword(name=$name, damage=$damage)"
    }
}