package com.company;

public class Unite {
    private int min_damage;
    private int max_damage;
    private int attack;
    private int defense;
    private int initiative;
    private int health;
    private int row;

    Unite(int min_damage, int max_damage, int attack, int defense, int initiative, int health) {
        this.min_damage = min_damage;
        this.max_damage = max_damage;
        this.attack = attack;
        this.defense = defense;
        this.initiative = initiative;
        this.health = health;
    }

    Unite() { }

    public int getMin_damage() {
        return min_damage;
    }
    public int getMax_damage() {
        return max_damage;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getInitiative() {
        return initiative;
    }
    public int getHealth() {
        return health;
    }
    public int getRow() {
        return row;
    }

    public void setMin_damage(int min_damage) {
        this.min_damage = min_damage;
    }
    public void setMax_damage(int max_damage) {
        this.max_damage = max_damage;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return min_damage + "," + max_damage + "," + attack + "," + defense + "," + initiative + "," + health;
    }
}
