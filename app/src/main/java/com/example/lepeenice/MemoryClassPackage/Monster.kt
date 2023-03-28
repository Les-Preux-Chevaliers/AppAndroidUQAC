package com.example.lepeenice.MemoryClassPackage

@kotlinx.serialization.Serializable
open class Monster(var name: String, var hp: Int, var attack: Int, var defense: Int, var imageUri: Int, var scoreGiven: Int) {

    override fun toString(): String {
        return "Monster(name=$name, hp=$hp, attack=$attack, defense=$defense, imageUri=$imageUri, , scoreGiven=$scoreGiven)"
    }

    /**
     * Le monstre reçois des dégâts.
     * @param damage Le nombre de point de dégâts reçu.
     * @return Boolean qui indique si le Monstre est mort.
     */
    fun getDamage(damage: Int): Boolean{
        var res: Boolean = false
        if((defense - damage)>0){
            //Si plus de dégâts que la défense de l'ennemi
            hp -= defense-damage;
            if(hp<=0){
                beforeIsDead()
                res = true
            }
        }
        return res
    }

    private fun beforeIsDead(){
        GameManager.getInstance().finishCurrentFight()
    }
}