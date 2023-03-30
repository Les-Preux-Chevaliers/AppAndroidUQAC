package com.example.lepeenice

import android.content.Context
import android.media.MediaPlayer
import com.example.lepeenice.MemoryClassPackage.GameManager

class PlaySound {

    //import com.example.lepeenice.PlaySound
    //PlaySound.playSound(this, R.raw.my_sound_name, toggleFalse/True)
    companion object {
        fun playSound(context: Context, soundID: Int, toggleLoop: Boolean) {
            var mediaPlayer: MediaPlayer? = null

            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, soundID)
                mediaPlayer.isLooping = toggleLoop
                mediaPlayer.start()
                mediaPlayer!!.setOnCompletionListener { mp ->
                    mp.release()
                }
            } else mediaPlayer.start()
        }
    }
}