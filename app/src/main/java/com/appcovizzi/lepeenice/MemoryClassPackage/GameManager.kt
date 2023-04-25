package com.appcovizzi.lepeenice.MemoryClassPackage

import kotlinx.serialization.Serializable

import android.content.Context
import androidx.compose.runtime.*
import com.appcovizzi.lepeenice.PlaySound
import com.appcovizzi.lepeenice.R
import com.appcovizzi.lepeenice.UIDisplay.MainScreen
import com.appcovizzi.lepeenice.Vibrate
import kotlinx.coroutines.*
import kotlinx.serialization.Transient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*


@Serializable
class GameManager private constructor() {


    var swords: MutableList<Sword> = mutableListOf()

    @Transient
    val monsters: MutableList<Monster> = mutableListOf(
        Monster(
            "Corrosicœur",
            50,
            0,
            0,
            R.drawable.mob1,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        ),
        Monster(
            "Tourmentum",
            100,
            0,
            0,
            R.drawable.mob2,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        ),
        Monster(
            "Assombros",
            200,
            0,
            0,
            R.drawable.mob3,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        ),
        Monster(
            "Grouillombre",
            275,
            0,
            0,
            R.drawable.mob4,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        ),
        Monster(
            "Rouillefureur",
            375,
            0,
            0,
            R.drawable.mob5,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        ),
        Monster(
            "Épinesang",
            550,
            0,
            0,
            R.drawable.mob6,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        ),
        Monster(
            "Crocsombre",
            650,
            0,
            0,
            R.drawable.mob7,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        ),
        Monster(
            "Glacécaille",
            850,
            0,
            0,
            R.drawable.mob8,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        ),
        Monster(
            "Crâneroc",
            1000,
            0,
            0,
            R.drawable.mob9,
            R.drawable.mob1_evolved,
            0,
            R.raw.mob1_hurt
        )
    )

    @Transient
    var currentMonster: Monster = monsters[0]

    var currentMonsterLife: Int by mutableStateOf(currentMonster.hp)

    var currentMoney: Int by mutableStateOf(Player.getInstance().getMoney())

    var currentXp: Int by mutableStateOf(Player.getInstance().getXp())

    var currentShildNumber: Int by mutableStateOf(3)

    var stateMouvement: Boolean by mutableStateOf(true)

    var sensibility: Float by mutableStateOf(Player.getInstance().sensibility)

    var needFirstTutoriel: Boolean by mutableStateOf(Player.getInstance().needfirsttuto)

    var needSecondTutoriel: Boolean by mutableStateOf(false)

    var creaditTutorial: Boolean by mutableStateOf(false)

    @Transient
    var currentBoolAttack: Boolean = false

    var boolFirstAttack: Boolean = false

    var boolSecondeAttack: Boolean = false

    var boolThirdAttack: Boolean = false

    companion object {
        private var instance: GameManager? = null


        fun getInstance(): GameManager {
            if (instance == null) {
                instance = GameManager()
            }
            return instance as GameManager
        }
    }

    fun loadSave(g: GameManager) {
        this.swords = g.swords
        //println(currentMonster)
        //NewMonster()
    }


    fun dealDamages(currentContext: Context) {
        var currentSword = Player.getInstance().sword.damage

        currentMonsterLife -= currentSword

        currentBoolAttack = !currentBoolAttack

        MainScreen.Life.value -= currentSword

        tryMonsterAttack(currentContext)

        //Player.getInstance().addXp(10)

        Player.getInstance().addMoney(10 * Player.getInstance().getLevel())

        if (currentMonsterLife <= 0) {
            Player.getInstance().addXp(currentMonster.hp)

            Player.getInstance().addMoney(currentMonster.hp)

            NewMonster()
        }
    }

    fun tryMonsterAttack(currentContext: Context) {
        val lifePercentage =
            currentMonsterLife.toFloat() / (currentMonster.hp) //* Player.getInstance().getLevel()).toFloat()
        //println(lifePercentage)
        if (!boolFirstAttack) {
            if (lifePercentage <= 0.75) {
                if (Player.getInstance().needsecondetuto == true){needSecondTutoriel=true}
                MonsterAttack(currentContext)
                boolFirstAttack = true
                return
            } else {
                return
            }
        } else if (!boolSecondeAttack) {
            if (lifePercentage <= 0.50) {
                MonsterAttack(currentContext)
                boolSecondeAttack = true
                return
            } else {
                return
            }
        } else if (!boolThirdAttack) {
            if (lifePercentage <= 0.25) {
                MonsterAttack(currentContext)
                boolThirdAttack = true
                return
            } else {
                return
            }
        }
    }


    fun MonsterAttack(currentContext : Context) {
        PlaySound.playSound(currentContext, R.raw.monsterattack, false)
        Vibrate.vibratePhone(currentContext, 3000)
        CoroutineScope(Dispatchers.Default).launch {
            delay(3000) // Attendre 3 secondes
            if (stateMouvement) {
                currentShildNumber += -1
                PlaySound.playSound(currentContext, R.raw.damaged, false)
                Vibrate.vibratePhone(currentContext, 500)
                if (currentShildNumber == 0) {
                    currentShildNumber = 3
                    NewMonster()
                }
            }
            PlaySound.playSound(currentContext, R.raw.shields, false)
            Vibrate.vibratePhone(currentContext, 500)
        }
    }


    fun NewMonster() {
        if (monsters.size < Player.getInstance().getLevel()){
            currentMonster = monsters.random()
        }else{
            val random = Random()
            val index = random.nextInt(Player.getInstance().getLevel())
            currentMonster = monsters[index]
        }
        currentMonsterLife = currentMonster.hp// * Player.getInstance().getLevel()
        boolFirstAttack = false
        boolSecondeAttack = false
        boolThirdAttack = false
    }

    /**
     * Création de toutes les épées du jeu.
     */
    fun createSwords() {
        if (swords.size >0){
            return
        }
        //Création des Swords
        swords.add(
            Sword(
                "Épée d'entrainement",
                1,
                0,
                true,
                R.drawable.epeedentrainement
            )
        )
        swords.add(
            Sword(
                "Lame de l'ombre nocturne",
                10,
                100,
                false,
                R.drawable.lamedelombrenocturne
            )
        )
        swords.add(
            Sword(
                "Épée de la licorne dorée",
                25,
                1500,
                false,
                R.drawable.epeedelalicornedoree
            )
        )
        swords.add(
            Sword(
                "Épée de la colère divine",
                50,
                5000,
                false,
                R.drawable.epeedelacoleredivine
            )
        )
        swords.add(
            Sword(
                "Épée de la vallée des âmes",
                75,
                10000,
                false,
                R.drawable.epeedelavalleedesames
            )
        )
        swords.add(
            Sword(
                "Lame du feu ardent",
                100,
                15000,
                false,
                R.drawable.lamedufeuardent
            )
        )
        // Fin création des Swords
    }

    fun finishCurrentFight() {
        Player.getInstance().addMoney(currentMonster.scoreGiven)
        currentMonster = monsters.random()
    }

    fun AcheterEpee(s: Sword) {
        swords.forEach { item ->
            if (s == item) {
                item.isPurchased = true
            }
        }
    }


    fun onSwordClick(sword: Sword, currentContext: Context) {
        if (!sword.isPurchased) {
            if (Player.getInstance().getMoney() >= sword.price) {
                Player.getInstance().addMoney(-sword.price)
                currentMoney = Player.getInstance().getMoney()
                AcheterEpee(sword)
                sword.isPurchased = true
                Player.getInstance().EquipeEpee(sword)
                val EncodedPlayer = Json.encodeToString(Player.getInstance())
                SaveManager.getInstance().saveDataToSharedPreferences_Player(currentContext,EncodedPlayer)
                val EncodedGameManager = Json.encodeToString(getInstance())
                SaveManager.getInstance().saveDataToSharedPreferences_GameManager(currentContext,EncodedGameManager)
            }
        } else {
            Player.getInstance().EquipeEpee(sword)
        }
    }
}