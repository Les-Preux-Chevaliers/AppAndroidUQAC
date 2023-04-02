package com.example.lepeenice.MemoryClassPackage

import kotlinx.serialization.Serializable
import java.util.logging.Level

@Serializable
public class Player private constructor() {

    var pseudo: String = "PlayerName"
    var sword: Sword = Sword("Épée d'entrainement",1,0,true,com.example.lepeenice.R.drawable.epeedentrainement)
    var hp: Int = 100
    private var level: Int = 1
    private var curentXp: Int = 0
    private var money: Int = 0

    companion object {
        private var instance: Player? = null

        fun getInstance(): Player {
            if (instance == null) {
                instance = Player()
            }
            return instance as Player
        }
    }

    fun EquipeEpee(s: Sword){
        sword = s
    }

    fun addMoney(value: Int) {
        money += value;
    }

    fun getMoney(): Int {
        return money
    }

    fun addXp(value: Int) {
        curentXp += value;
        if (curentXp > 100 * level){
            curentXp -= 100 * level
            level++
        }
    }

    fun getXp(): Int {
        return curentXp
    }

    fun getLevel(): Int {
        return level
    }

    fun loadSave(p: Player){
        this.pseudo = p.pseudo
        this.money = p.money
        this.hp = p.hp
        this.level = p.level
        this.curentXp = p.curentXp
        this.sword = p.sword
    }
}