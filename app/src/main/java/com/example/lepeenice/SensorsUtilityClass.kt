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

    private var lastUpdateTime = 0L
    private var deltaTime = 0.08f

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

        println("DT : " + deltaTime)
    }

    // Fonction pour délier l'accéléromètre
    fun unuseAccelerometer() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            // Récuperer la valeur de l'accélération sur l'axe X et Y obtenue à l'appel de la fonction
            val acceleration = floatArrayOf(event.values[0], event.values[1])

            // Si l'accélération n'est pas assez significative ne rien faire
            if (acceleration[0] <= -3.0f || acceleration[0] >= 3.0f) {
                // Calculer la vitesse en X
                velocity[0] = velocity[0] + acceleration[0] * deltaTime
                // Calculer la position en X
                position[0] = position[0] + velocity[0] * deltaTime
            }
            if (acceleration[1] <= -3.0f || acceleration[1] >= 3.0f) {
                // Calculer la vitesse en Y
                velocity[1] = velocity[1] + acceleration[1] * deltaTime
                // Calculer la position en Y
                position[1] = position[1] + velocity[1] * deltaTime
            }

            /*if (event != null) {
            println("Ax = " + event.values[0] + "Ay = " + event.values[1])
            }*/

            printThePos()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    fun printThePos() {
        println("X : " + position[0] + "   Y : " + position[1])
    }
}