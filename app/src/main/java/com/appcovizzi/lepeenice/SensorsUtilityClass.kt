package com.appcovizzi.lepeenice

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.content.Context
import kotlin.math.sqrt
import com.appcovizzi.lepeenice.MemoryClassPackage.GameManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SensorsUtilityClass : SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var accelerometer: Sensor
    lateinit var currContext: Context

    var isOnCombatScreen = false

    private var chrono = 0.0f
    private val chronoStop = 1.2f
    private val deltaTime = 0.08f

    private val accDelta = 10f

    private var velocity = floatArrayOf(0.0f, 0.0f)

    private var position = floatArrayOf(0.0f, 0.0f)
    private val lengthToHit = 12.0f
    private var hit = false

    // Fonction pour utiliser l'accéléromètre
    fun useAccelerometer(context: Context) {
        currContext = context

        position[0] = 0.0f
        position[1] = 0.0f

        // Récupérer le gestionnaire de capteurs
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Récupérer l'accéléromètre
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also {
            // Enregistrer l'écouteur de capteur pour l'accéléromètre
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        //println("DT : " + deltaTime)
    }

    // Fonction pour délier l'accéléromètre
    fun unuseAccelerometer() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            // Récuperer la valeur de l'accélération sur l'axe X et Y obtenue à l'appel de la fonction
            val acceleration = floatArrayOf(event.values[0], event.values[1])

            // Si le téléphone est en mouvement, changé l'état du joueur dans le gameManager
            GameManager.getInstance().stateMouvement = (acceleration[0] <= -accDelta || acceleration[0] >= accDelta
                    || acceleration[1] <= -accDelta || acceleration[1] >= accDelta)

            // Si la longueur du vecteur est supérieur à une certaine valeur la position est reset et un coup est porté
            var vectorLength = sqrt(position[0] * position[0] + position[1] * position[1])
//            println(vectorLength)
            if (vectorLength > GameManager.getInstance().sensibility && !hit) {
                hit = true
                position[0] = 0.0f
                position[1] = 0.0f
                println("====================HIT====================")
                PlaySound.playSound(currContext, R.raw.sword_metal_woosh, false)
                Vibrate.vibratePhone(currContext, 200)
                GameManager.getInstance().dealDamages(currContext)
            }

            if (hit == false) {
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
            } else {
                executeHit()
            }

            //println(hit)
            //println(GameManager.getInstance().stateMouvement)
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

    var isExecuting = false
    val lock = Object()

    fun executeHit() {
        synchronized(lock) {
            if (isExecuting) {
                return // la coroutine est déjà en cours d'exécution, on ne fait rien
            }
            isExecuting = true // on marque la coroutine comme étant en cours d'exécution
        }

        CoroutineScope(Dispatchers.Default).launch {
            // exécution de la coroutine

            // Si un coup a été porté, le chrono est lancé
            chrono += deltaTime
            // Au bout de 1 seconde le chrono est reset et le joueur peut à nouveau frapper
            if (chrono >= chronoStop) {
                hit = false
                chrono = 0.0f
            }

            synchronized(lock) {
                isExecuting = false // on marque la coroutine comme étant terminée
            }
        }
    }


}