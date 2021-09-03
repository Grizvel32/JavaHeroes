package com.company.entities;

public abstract class Unit {
    private int id;

    private Point point;
    private char skin;

    private int hp;
    private int stepDistance;
    private int attackDistance;

    private int minDamage, maxDamage;

    public Unit(Point point, char skin, int hp, int stepDistance, int attackDistance, int minDamage, int maxDamage) {
        this.id = 0;
        this.point = point;
        this.skin = skin;
        this.hp = hp;
        this.stepDistance = stepDistance;
        this.attackDistance = attackDistance;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    //region public methods
    public int getId() {
        return id;
    }

    public Point getPoint() {
        return point;
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

    public boolean canAttack(Unit unit) {
        double distance = Math.sqrt(Math.pow(unit.getPoint().i - point.i, 2) + Math.pow(unit.getPoint().j - point.j, 2));

        return distance <= (double) attackDistance;
    }

    //endregion

    //region protected methods

    protected void decreaseHp(int damage) {
        hp -= damage;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setAttackDistanceForHandsAttack() {
        attackDistance = 1;
    }

    //endregion

    //region public abstract methods

    public abstract void attack(Unit unit);

    public abstract String getInfo();

    //endregion
}
