package com.company.entities;

import com.company.entities.supportings.Point;
import com.company.util.RandomHelper;
import com.company.util.SettingsHelper;

public class Archer extends Unit {

    private enum AttackType {

        HANDS("руками"),
        ARROWS("стрелами");

        private String nameType;

        AttackType(String nameType) {
            this.nameType = nameType;
        }

        @Override
        public String toString() {
            return nameType;
        }
    }

    private int countArrows;
    private AttackType currentAttackType;


    public Archer(Point point) {
        super(point,
                SettingsHelper.getCharValue("archerSkin"),
                RandomHelper.getRandomInRange(
                        SettingsHelper.getIntValue("archerMinHp"),
                        SettingsHelper.getIntValue("archerMaxHp")
                ),
                SettingsHelper.getIntValue("archerStepDistance"),
                SettingsHelper.getIntValue("archerAttackDistance"),
                SettingsHelper.getIntValue("archerMinDamage"),
                SettingsHelper.getIntValue("archerMaxDamage")
        );
        countArrows = SettingsHelper.getIntValue("archerCountArrows");

        if (countArrows <= 0) {
            countArrows = 0;
            currentAttackType = AttackType.HANDS;
            setAttackDistanceForHandsAttack();
        } else {
            currentAttackType = AttackType.ARROWS;
        }
    }

    @Override
    public void attack(Unit unit) {
        int damage = RandomHelper.getRandomInRange(getMinDamage(), getMaxDamage());

        if (currentAttackType == AttackType.HANDS) {
            damage = damage / 2;
        }

        unit.decreaseHp(damage);

        if (currentAttackType == AttackType.ARROWS) {
            countArrows--;

            if (countArrows == 0) {
                currentAttackType = AttackType.HANDS;
                setAttackDistanceForHandsAttack();
            }
        }
    }

    @Override
    public String getInfo() {
        return String.format("Существо: %c; id: %d, позиция: i-%d, j-%d; здоровье: %d, сила атаки: от %d до %d; оставшееся кол-во стрел: %d, тип атаки: %s, радиус атаки: %d", getSkin(), getId(), getPoint().i, getPoint().j, getHp(), getMinDamage(), getMaxDamage(), countArrows, currentAttackType, getAttackDistance());
    }
}
