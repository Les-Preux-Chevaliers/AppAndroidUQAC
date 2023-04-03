package com.example.lepeenice.MemoryClassPackage

import kotlinx.serialization.Serializable

import android.content.Context
import androidx.compose.runtime.*
import com.example.lepeenice.PlaySound
import com.example.lepeenice.R
import com.example.lepeenice.UIDisplay.MainScreen
import com.example.lepeenice.Vibrate
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*


@Serializable
public class GameManager private constructor() {


    var swords: MutableList<Sword> = mutableListOf()

    @Transient
    val monsters: MutableList<Monster> = mutableListOf(
        Monster(
            "Gigachiax",
            100,
            0,
            0,
            com.example.lepeenice.R.drawable.mob1,
            com.example.lepeenice.R.drawable.mob1_evolved,
            0,
            com.example.lepeenice.R.raw.mob1_hurt
        ),
        Monster(
            "Tractopulpeux",
            200,
            0,
            0,
            com.example.lepeenice.R.drawable.mob1,
            com.example.lepeenice.R.drawable.mob1_evolved,
            0,
            com.example.lepeenice.R.raw.mob1_hurt
        ),
        Monster(
            "Terminotron",
            50,
            0,
            0,
            com.example.lepeenice.R.drawable.mob1,
            com.example.lepeenice.R.drawable.mob1_evolved,
            0,
            com.example.lepeenice.R.raw.mob1_hurt
        )
    )

    @Transient
    var currentMonster: Monster = monsters.random()

    var currentMonsterLife: Int by mutableStateOf(currentMonster.hp)

    var currentMoney: Int by mutableStateOf(Player.getInstance().getMoney())

    var currentXp: Int by mutableStateOf(Player.getInstance().getXp())

    var currentShildNumber: Int by mutableStateOf(3)

    var stateMouvement: Boolean by mutableStateOf(true)

    @Transient
    var currentBoolAttack: Boolean = false

    @Transient
    var boolFirstAttack: Boolean = false

    @Transient
    var boolSecondeAttack: Boolean = false

    @Transient
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
    }


    fun dealDamages(currentContext: Context) {
        var currentSword = Player.getInstance().sword.damage

        var monsterSound = currentMonster.getHitSound

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

    fun dealDamagestest() {
        var currentSword = Player.getInstance().sword.damage

        currentMonsterLife -= currentSword

        currentBoolAttack = !currentBoolAttack

        MainScreen.Life.value -= currentSword

        //tryMonsterAttack(currentContext)

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
            currentMonsterLife.toFloat() / (currentMonster.hp * Player.getInstance()
                .getLevel()).toFloat()
        //println(lifePercentage)
        if (!boolFirstAttack) {
            if (lifePercentage <= 0.75) {
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
            if (lifePercentage <= 0.50) {
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
        }
    }


    fun NewMonster() {
        currentMonster = monsters.random()
        currentMonsterLife = currentMonster.hp * Player.getInstance().getLevel()
        boolFirstAttack = false
        boolSecondeAttack = false
        boolThirdAttack = false
    }

    /**
     * Cette fonction donne un string de toutes les épées du GameManager.
     * @return String Concatenation des toString() de chaque épée.
     */
    fun printSwords(): String {
        var res: String = ""
        for (sword in swords) {
            res += sword.toString() + ", "
        }
        return res
    }

    /**
     * Cette fonction donne un string de toutes les monstres du GameManager.
     * @return String Concatenation des toString() de chaque monstre.
     */
    fun printMonsters(): String {
        var res: String = ""
        for (monster in monsters) {
            res += monster.toString() + ", "
        }
        return res
    }

    /**
     * Ajoute un Monstre la liste des monstres du GameManager.
     * @param name String, Nom du monstre.
     * @param hp Float, Point de vie du monstre.
     * @param attack Float, Nombre de dégâts du monstre.
     * @param defense Int, Nombre de point de défense du monstre.
     * @param imageUri Int, Adresse de l'image du Monstre (com.example.lepeenice.R.drawable."ImageName").
     * @param imageUri2 Int, Adresse de l'image du Monstre élovué (com.example.lepeenice.R.drawable."ImageName").
     * @param HitSound Int, Adresse du sound.
     */
    fun addMonster(
        name: String,
        hp: Int,
        attack: Int,
        defense: Int,
        imageUri: Int,
        imageUri2: Int,
        scoreGiven: Int,
        getHitSound: Int
    ) {
        monsters.add(
            Monster(
                name,
                hp,
                attack,
                defense,
                imageUri,
                imageUri2,
                scoreGiven,
                getHitSound
            )
        )
    }

    /**
     * Création de toutes les épées du jeu.
     */
    fun createSwords() {
        //Création des Swords
        swords.add(
            Sword(
                "Épée d'entrainement",
                1,
                0,
                true,
                com.example.lepeenice.R.drawable.epeedentrainement
            )
        )
        swords.add(
            Sword(
                "Lame de l'ombre nocturne",
                10,
                100,
                false,
                com.example.lepeenice.R.drawable.lamedufeuardent
            )
        )
        swords.add(
            Sword(
                "Épée de la licorne dorée",
                25,
                1500,
                false,
                com.example.lepeenice.R.drawable.lamedufeuardent
            )
        )
        swords.add(
            Sword(
                "Épée de la colère divine",
                50,
                5000,
                false,
                com.example.lepeenice.R.drawable.lamedufeuardent
            )
        )
        swords.add(
            Sword(
                "Épée de la vallée des âmes",
                75,
                10000,
                false,
                com.example.lepeenice.R.drawable.lamedufeuardent
            )
        )
        swords.add(
            Sword(
                "Lame du feu ardent",
                100,
                15000,
                false,
                com.example.lepeenice.R.drawable.lamedufeuardent
            )
        )
        // Fin création des Swords
    }

    fun finishCurrentFight() {
        Player.getInstance().addMoney(currentMonster.scoreGiven)
        currentMonster = monsters.random()
    }

    fun AcheterEpee(s: Sword, currentContext : Context) {
        swords.forEach { item ->
            if (s == swords) {
                item.isPurchased = true
            }
        }
    }


    fun onSwordClick(sword: Sword, currentContext: Context) {
        if (!sword.isPurchased) {
            if (Player.getInstance().getMoney() >= sword.price) {
                Player.getInstance().addMoney(-sword.price)
                currentMoney = Player.getInstance().getMoney()
                AcheterEpee(sword,currentContext)
                sword.isPurchased = true
                Player.getInstance().EquipeEpee(sword)
                val EncodedPlayer = Json.encodeToString(Player.getInstance())
                SaveManager.getInstance().saveDataToSharedPreferences_Player(currentContext,EncodedPlayer)
                val EncodedGameManager = Json.encodeToString(GameManager.getInstance())
                SaveManager.getInstance().saveDataToSharedPreferences_GameManager(currentContext,EncodedGameManager)
            }
        } else {
            Player.getInstance().EquipeEpee(sword)
        }
    }
}