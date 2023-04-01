package com.example.lepeenice
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.content.Context
import android.os.Bundle
import kotlin.math.sqrt

class SensorsUtilityClass : SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var accelerometer: Sensor

    private var chrono = 0.0f
    private var deltaTime = 0.08f

    private var accDelta = 10f

    private var velocity = floatArrayOf(0.0f, 0.0f)

    private var position = floatArrayOf(0.0f, 0.0f)
    private var hit = false

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

        //println("DT : " + deltaTime)
    }

    // Fonction pour délier l'accéléromètre
    fun unuseAccelerometer() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            // Si la longueur du vecteur est supérieur à 40 la position est reset et un coup est porté
            var vectorLength = sqrt(position[0] * position[0] + position[1] * position[1])
            if (vectorLength > 20.0f && hit == false) {
                hit = true
                position[0] = 0.0f
                position[1] = 0.0f
                println("====================HIT====================")
            }

            if (hit == false) {
                // Récuperer la valeur de l'accélération sur l'axe X et Y obtenue à l'appel de la fonction
                val acceleration = floatArrayOf(event.values[0], event.values[1])

                // Si l'accélération n'est pas assez significative ne rien faire
                if (acceleration[0] <= -accDelta || acceleration[0] >= accDelta) {
                    // Calculer la vitesse en X
                    velocity[0] = velocity[0] + acceleration[0] * deltaTime
                    // Calculer la position en X
                    position[0] = position[0] + velocity[0] * deltaTime
                }
                if (acceleration[1] <= -accDelta || acceleration[1] >= accDelta) {
                    // Calculer la vitesse en Y
                    velocity[1] = velocity[1] + acceleration[1] * deltaTime
                    // Calculer la position en Y
                    position[1] = position[1] + velocity[1] * deltaTime
                }
            }
            else {
                // Si un coup a été porté, le chrono est lancé
                chrono += deltaTime
                // Au bout de 1 seconde le chrono est reset et le joueur peut à nouveau frapper
                if (chrono >= 1.5f) {
                    hit = false
                    chrono = 0.0f
                }
            }

            println(hit)
            //println("Ax = " + event.values[0] + "   Ay = " + event.values[1])
            //println("Vx = " + velocity[0] + "   Vy = " + velocity[1])
            //printThePos()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

    fun printThePos() {
        println("X : " + position[0] + "   Y : " + position[1])
    }
}