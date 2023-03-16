package com.example.lepeenice
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.app.Activity
import android.os.Bundle

class CombatActivity : Activity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor

    private var xPosition = 0f
    private var yPosition = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.Activity)

        // Récupération du SensorManager et de l'accéléromètre
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Initialisation de la position du téléphone à (0,0)
        xPosition = 0f
        yPosition = 0f
    }

    override fun onResume() {
        super.onResume()

        // Enregistrement de l'écouteur d'événement pour l'accéléromètre
        sensorManager.registerListener(
            this,
            accelerometer,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()

        // Désenregistrement de l'écouteur d'événement pour l'accéléromètre
        sensorManager.unregisterListener(this)
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