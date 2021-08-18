package com.company.entities;

public abstract class Unit {
    private int i, j;
    private char skin;

    private int hp;
    private int stepDistance;
    private int attackDistance;

    private int minDamage, maxDamage;

    public Unit(int i, int j, char skin, int hp, int stepDistance, int attackDistance, int minDamage, int maxDamage) {
        this.i = i;
        this.j = j;
        this.skin = skin;
        this.hp = hp;
        this.stepDistance = stepDistance;
        this.attackDistance = attackDistance;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public char getSkin() {
        return skin;
    }

    public int getHp() {
        return hp;
    }

    public int getStepDistance() {
        return stepDistance;
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    protected void decreaseHp(int damage) {
        hp -= damage;
    }

    public abstract void attack(Unit unit);

    public boolean canAttack(Unit unit) {
        double distance = Math.sqrt(Math.pow(unit.i - i, 2) + Math.pow(unit.j - j, 2));

        return distance <= (double) attackDistance;
    }
}
