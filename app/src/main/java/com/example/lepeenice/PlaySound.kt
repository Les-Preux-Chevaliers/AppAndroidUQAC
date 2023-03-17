package com.example.lepeenice

import android.content.Context
import android.media.MediaPlayer

class PlaySound {

    //import com.example.lepeenice.PlaySound
    //PlaySound.playsound(this, R.raw.my_sound_name, toggleFalse/True)
    companion object {
        fun playSound(context: Context, soundID: Int, toggleLoop: Boolean) {
            print("sound")
            var mediaPlayer: MediaPlayer? = null

            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, soundID)
                mediaPlayer.isLooping = toggleLoop
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener { mp ->
                    mp.release()
                }
            } else mediaPlayer.start()
        }
    }
    /*companion object {
        fun playsound(mainActivity: ColumnScope, mySoundName: Any, any: Any) {

        }
    }*/
}