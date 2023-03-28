package com.example.lepeenice.MemoryClassPackage

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
public class GameManager private constructor() {

    val swords: MutableList<Sword> = mutableListOf()
    val monsters: MutableList<Monster> = mutableListOf()

    var currentMonster: Monster = Monster("NONE",0,0,0,-1, 0)

    companion object {
        private var instance: GameManager? = null

        fun getInstance(): GameManager {
            if (instance == null) {
                instance = GameManager()
            }
            return instance as GameManager
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
     * @param imageUri Int, Adresse de l'image du Monstre (R.drawable."ImageName").
     */
    fun addMonster(name:String, hp:Int, attack:Int, defense:Int, imageUri:Int, scoreGiven:Int){
        monsters.add(Monster(name,hp,attack,defense,imageUri,scoreGiven))
    }

    /**
     * Création de toutes les épées du jeu.
     */
    fun createSwords() {
        //Création des Swords
            swords.add(Sword("Épée d'entrainement",1))
            swords.add(Sword("Lame du dragon hurlant",18))
            swords.add(Sword("Sabre de la chimère",15))
            swords.add(Sword("Dague de la vipère noire",12))
            swords.add(Sword("Épée des brumes éternelles",20))
            swords.add(Sword("Lame de la destinée",25))
            swords.add(Sword("Épée des cieux étoilés",30))
            swords.add(Sword("Mjöllnir, le marteau divin",35))
            swords.add(Sword("Lame de l'ombre nocturne",40))
            swords.add(Sword("Épée de la foudre céleste",18))
            swords.add(Sword("Lame de l'aigle royal",22))
            swords.add(Sword("La faucheuse de l'enfer",14))
            swords.add(Sword("Épée de la licorne dorée",45))
            swords.add(Sword("Lame de l'esprit du vent",28))
            swords.add(Sword("Épée de la colère divine",16))
            swords.add(Sword("Épée de la guérison éternelle",38))
            swords.add(Sword("Lame de l'ombre de la lune",10))
            swords.add(Sword("Épée de la vallée des âmes",24))
            swords.add(Sword("La folie de la justice",32))
            swords.add(Sword("Lame du feu ardent",50))
        // Fin création des Swords
    }

    fun finishCurrentFight(){
        Player.getInstance().addScore(currentMonster.scoreGiven)
        var randomNB = 0;
        if(monsters.size > 1){
            randomNB = (0..(monsters.size-1)).random()
        }else{
            currentMonster = monsters.get(randomNB);
        }
    }
}