package com.example.lepeenice
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.content.Context
import android.os.Bundle

class SensorsUtilityClass : SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var accelerometer: Sensor

    private var gravity = floatArrayOf(0.0f, 0.0f)

    private val alpha = 0.8f // coefficient de filtrage passe-bas

    private val deltaTime = SensorManager.SENSOR_DELAY_NORMAL
    private var currentTime = 0L

    private var velocity = floatArrayOf(0.0f, 0.0f)

    private var position = floatArrayOf(0.0f, 0.0f)

    // Fonction pour utiliser l'accéléromètre
    fun useAccelerometer(context: Context) {
        // Récupérer le gestionnaire de capteurs
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Récupérer l'accéléromètre
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also {
            // Enregistrer l'écouteur de capteur pour l'accéléromètre
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    // Fonction pour délier l'accéléromètre
    /*fun unuseAccelerometer() {
        sensorManager.unregisterListener(this)
    }*/

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1]

            currentTime += deltaTime

            // Retirer la gravité pour obtenir l'accélération linéaire
            val linearAcceleration = FloatArray(2)
            linearAcceleration[0] = event.values[0] - gravity[0]
            linearAcceleration[1] = event.values[1] - gravity[1]

            // Calculer la vitesse et la position en utilisant la méthode simple
            for (i in 0 until 2) {
                velocity[i] += linearAcceleration[i] * deltaTime
                position[i] += velocity[i] * deltaTime
            }

            println("Sensor get new Values")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    fun printThePos() {
        println("X : " + position[0])
        println("Y : " + position[1])
    }
}