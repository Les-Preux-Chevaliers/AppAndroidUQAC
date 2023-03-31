package com.example.lepeenice.MemoryClassPackage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable
import kotlin.random.Random

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.lepeenice.PlaySound

@Serializable
public class GameManager private constructor() {

    companion object {

    val swords: MutableList<Sword> = mutableListOf()

        val monsters: MutableList<Monster> = mutableListOf(
            Monster("Gigachiax",100,0,0,com.example.lepeenice.R.drawable.mob1,com.example.lepeenice.R.drawable.mob1_evolved, 0, com.example.lepeenice.R.raw.mob1_hurt),
            Monster("Tractopulpeux",200,0,0,com.example.lepeenice.R.drawable.mob1,com.example.lepeenice.R.drawable.mob1_evolved, 0, com.example.lepeenice.R.raw.mob1_hurt),
            Monster("Terminotron",50,0,0,com.example.lepeenice.R.drawable.mob1,com.example.lepeenice.R.drawable.mob1_evolved, 0, com.example.lepeenice.R.raw.mob1_hurt)
        )

        private var instance: GameManager? = null

        var currentMonster: Monster = monsters.random()

        fun getInstance(): GameManager {
            if (instance == null) {
                instance = GameManager()
            }
            return instance as GameManager
        }

        @Composable
        fun dealDamages()
        {
            val currentContext: Context = LocalContext.current

            var currentSword = Player.getInstance().sword.damage

            var monsterSound = currentMonster.getHitSound
            var monsterHp = currentMonster.hp
            

            //joue le son du monstre
            PlaySound.playSound(currentContext, monsterSound, false)

            //memoire tampon de hpMax du monstre pour lui infliger des degats




        }
    }

    /**
     * Cette fonction donne un string de toutes les épées du GameManager.
     * @return String Concatenation des toString() de chaque épée.
     */
    fun printSwords() : String {
        var res:String = ""
        for (sword in swords) {
            res += sword.toString()+", "
        }
        return res
    }

    /**
     * Cette fonction donne un string de toutes les monstres du GameManager.
     * @return String Concatenation des toString() de chaque monstre.
     */
    fun printMonsters() : String {
        var res:String = ""
        for (monster in monsters) {
            res += monster.toString()+", "
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
    fun addMonster(name:String, hp:Int, attack:Int, defense:Int, imageUri:Int,imageUri2:Int, scoreGiven:Int, getHitSound:Int){
        monsters.add(Monster(name,hp,attack,defense,imageUri,imageUri2,scoreGiven, getHitSound))
    }

    /**
     * Création de toutes les épées du jeu.
     */
    fun createSwords() {
        swords.add(Sword("NonusÉpée d'entrainement",1,0,true,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("BonusLame de l'ombre nocturne",10,1000,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("proutÉpée de la licorne dorée",15,2500,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("mamamamÉpée de la colère divine",20,5000,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("PIZZZZAAAAÉpée de la vallée des âmes",30,10000,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("NonusÉpée d'entrainement",1,0,true,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("BonusLame de l'ombre nocturne",10,1000,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("proutÉpée de la licorne dorée",15,2500,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("mamamamÉpée de la colère divine",20,5000,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("PIZZZZAAAAÉpée de la vallée des âmes",30,10000,false,com.example.lepeenice.R.drawable.logoepeenice))

        swords.add(Sword("NonusÉpée d'entrainement",1,0,true,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("BonusLame de l'ombre nocturne",10,1000,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("proutÉpée de la licorne dorée",15,2500,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("mamamamÉpée de la colère divine",20,5000,false,com.example.lepeenice.R.drawable.logoepeenice))
        swords.add(Sword("PIZZZZAAAAÉpée de la vallée des âmes",30,10000,false,com.example.lepeenice.R.drawable.logoepeenice))
        //Création des Swords
            swords.add(Sword("Épée d'entrainement",1,0,true,com.example.lepeenice.R.drawable.logoepeenice))
            swords.add(Sword("Lame de l'ombre nocturne",10,1000,false,com.example.lepeenice.R.drawable.logoepeenice))
            swords.add(Sword("Épée de la licorne dorée",15,2500,false,com.example.lepeenice.R.drawable.logoepeenice))
            swords.add(Sword("Épée de la colère divine",20,5000,false,com.example.lepeenice.R.drawable.logoepeenice))
            swords.add(Sword("Épée de la vallée des âmes",30,10000,false,com.example.lepeenice.R.drawable.logoepeenice))
            swords.add(Sword("Lame du feu ardent",50,15000,false,com.example.lepeenice.R.drawable.logoepeenice))
        // Fin création des Swords
    }

    fun finishCurrentFight(){
        Player.getInstance().addMoney(currentMonster.scoreGiven)
        currentMonster = monsters.random()
    }


}