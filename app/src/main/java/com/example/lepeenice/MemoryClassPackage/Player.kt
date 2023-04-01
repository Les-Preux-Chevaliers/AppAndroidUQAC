package com.example.lepeenice.MemoryClassPackage

import kotlinx.serialization.Serializable

@Serializable
public class Player private constructor() {

    var pseudo: String = "PlayerName"
    var sword: Sword = Sword("Épée d'entrainement",1,0,true,com.example.lepeenice.R.drawable.epeedentrainement)
    var hp: Int = 100
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

    fun loadSave(p: Player){
        this.pseudo = p.pseudo
        this.money = p.money
        this.hp = p.hp
        this.sword = p.sword
    }
}