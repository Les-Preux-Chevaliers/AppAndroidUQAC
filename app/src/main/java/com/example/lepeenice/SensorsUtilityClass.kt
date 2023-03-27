package com.example.lepeenice
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.content.Context
import android.os.Bundle

class SensorsUtilityClass : SensorEventListener {
    var sensorManager: SensorManager? = null
    var accelerometer: Sensor? = null

    private var xPosition = 0f
    private var yPosition = 0f

    // Fonction pour utiliser l'accéléromètre
    fun useAccelerometer(context: Context, accelerometerListener: SensorEventListener) {
        // Récupérer le gestionnaire de capteurs
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Récupérer l'accéléromètre
        accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Enregistrer l'écouteur de capteur pour l'accéléromètre
        sensorManager!!.registerListener(accelerometerListener,
            accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    // Fonction pour délier l'accéléromètre
    fun unuseAccelerometer(context: Context, accelerometerListener: SensorEventListener) {
        sensorManager!!.unregisterListener(accelerometerListener)
    }

    override fun onSensorChanged(event: SensorEvent) {
        // Récupération des données de l'événement
        val xAcceleration = event.values[0]
        val yAcceleration = event.values[1]

        // Calcul de la nouvelle position du téléphone
        val deltaTime = 0.5f // demi-seconde
        xPosition += xAcceleration * deltaTime * deltaTime
        yPosition += yAcceleration * deltaTime * deltaTime
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Ne rien faire ici
    }
}