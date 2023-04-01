package com.example.lepeenice.MemoryClassPackage

import androidx.annotation.DrawableRes

@kotlinx.serialization.Serializable
open class Sword(val name: String, val damage: Int, val price: Int, var isPurchased: Boolean, @DrawableRes val imageId: Int) {

    override fun toString(): String {
        return "Sword(name=$name, damage=$damage)"
    }
}