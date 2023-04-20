package com.appcovizzi.lepeenice

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

class Vibrate {

    //Vibrate.vibratePhone(this, vibrateTime)
    companion object {
        fun vibratePhone(context: Context, vibrateTime: Long) {
            print("vibrate")

            val vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        vibrateTime,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(vibrateTime)
            }
        }
    }

    /*companion object {
        fun vibratePhone(mainActivity: ColumnScope, vibrateTime: Int) {

        }
    }*/
}