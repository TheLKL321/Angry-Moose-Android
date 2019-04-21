package com.example.thelkl321.angrymooseandroid.fight;

public abstract class Fighter {
    private int health, maxHealth;
    FightActivity myWorld;

    Fighter(int maxHealth, FightActivity myWorld) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.myWorld = myWorld;
    }

    void takeDamage (int damage) {
        health = (health - damage) % maxHealth;
    }

    public void reset () { health = maxHealth; }

    int getHealth () { return health; }
}
