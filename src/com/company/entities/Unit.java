package com.company.entities;

import com.company.entities.supportings.Point;
import com.company.entities.supportings.RectangleArea;

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

    public void moveUp(int minI, int maxI) {
        point.i--;

        if (point.i < minI) {
            point.i = maxI;
        }
    }

    public void moveDown(int minI, int maxI) {
        point.i++;

        if (point.i > maxI) {
            point.i = minI;
        }
    }

    public void moveRight(int minJ, int maxJ) {
        point.j++;

        if (point.j > maxJ) {
            point.j = minJ;
        }
    }

    public void moveLeft(int minJ, int maxJ) {
        point.j--;

        if (point.j < minJ) {
            point.j = maxJ;
        }
    }

    //todo refactor this, numbers is bad decision
    public void move(int direction, RectangleArea rectangleArea) {
        switch (direction) {
            case 1:
                moveUp(rectangleArea.minI, rectangleArea.maxI);
                break;
            case 2:
                moveDown(rectangleArea.minI, rectangleArea.maxI);
                break;
            case 3:
                moveLeft(rectangleArea.minJ, rectangleArea.maxJ);
                break;
            case 4:
                moveRight(rectangleArea.minJ, rectangleArea.maxJ);
                break;
        }
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

    protected void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    protected void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    //endregion

    //region public abstract methods

    public abstract void attack(Unit unit);

    public abstract String getInfo();

    //endregion
}
