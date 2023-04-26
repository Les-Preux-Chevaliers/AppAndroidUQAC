package com.appcovizzi.lepeenice.UIDisplay

import com.appcovizzi.lepeenice.R
import kotlinx.serialization.Serializable

class ImageLibrairy {

    @Serializable
    object ImageMobs {
        val images = mapOf(
            "mob1" to R.drawable.mob1,
            "mob2" to R.drawable.mob2,
            "mob3" to R.drawable.mob3,
            "mob4" to R.drawable.mob4,
            "mob5" to R.drawable.mob5,
            "mob6" to R.drawable.mob6,
            "mob7" to R.drawable.mob7,
            "mob8" to R.drawable.mob8,
            "mob9" to R.drawable.mob9,
        )
    }

    @Serializable
    object ImageSwords {
        val images = mapOf(
            "epeedentrainement" to R.drawable.epeedentrainement,
            "lamedelombrenocturne" to R.drawable.lamedelombrenocturne,
            "epeedelalicornedoree" to R.drawable.epeedelalicornedoree,
            "epeedelacoleredivine" to R.drawable.epeedelacoleredivine,
            "epeedelavalleedesames" to R.drawable.epeedelavalleedesames,
            "lamedufeuardent" to R.drawable.lamedufeuardent,
        )
    }
}