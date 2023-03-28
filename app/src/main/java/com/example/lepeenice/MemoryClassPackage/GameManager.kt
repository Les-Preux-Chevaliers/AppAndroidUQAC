package com.example.lepeenice.MemoryClassPackage

import kotlinx.serialization.Serializable

@Serializable
public class GameManager private constructor() {

    val swords: MutableList<Sword> = mutableListOf()
    val monsters: MutableList<Monster> = mutableListOf()

    var currentMonster: Monster = Monster("NONE",0.0f,0.0f,0,-1, 0)

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
    fun AddMonster(name:String, hp:Float, attack:Float, defense:Int, imageUri:Int, scoreGiven:Int){
        monsters.add(Monster(name,hp,attack,defense,imageUri,scoreGiven))
    }

    /**
     * Création de toutes les épées du jeu.
     */
    fun CreateSwords() {

        //Création des Swords
            swords.add(Sword("Épée d'entrainement",1.0f))
            swords.add(Sword("Lame du dragon hurlant",18.0f))
            swords.add(Sword("Sabre de la chimère",15.0f))
            swords.add(Sword("Dague de la vipère noire",12.0f))
            swords.add(Sword("Épée des brumes éternelles",20.0f))
            swords.add(Sword("Lame de la destinée",25.0f))
            swords.add(Sword("Épée des cieux étoilés",30.0f))
            swords.add(Sword("Mjöllnir, le marteau divin",35.0f))
            swords.add(Sword("Lame de l'ombre nocturne",40.0f))
            swords.add(Sword("Épée de la foudre céleste",18.0f))
            swords.add(Sword("Lame de l'aigle royal",22.0f))
            swords.add(Sword("La faucheuse de l'enfer",14.0f))
            swords.add(Sword("Épée de la licorne dorée",45.0f))
            swords.add(Sword("Lame de l'esprit du vent",28.0f))
            swords.add(Sword("Épée de la colère divine",16.0f))
            swords.add(Sword("Épée de la guérison éternelle",38.0f))
            swords.add(Sword("Lame de l'ombre de la lune",10.0f))
            swords.add(Sword("Épée de la vallée des âmes",24.0f))
            swords.add(Sword("La folie de la justice",32.0f))
            swords.add(Sword("Lame du feu ardent",50.0f))
        // Fin création des Swords
    }
}