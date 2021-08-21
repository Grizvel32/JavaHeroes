package com.company.entities;

import com.company.util.RandomHelper;
import com.company.util.SettingsHelper;

public class Archer extends Unit {

    private int countArrows;

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
    }

    @Override
    public void attack(Unit unit) {
        //todo доделать атаку руками или стрелами, при ближнем бое делить на 2 + выбрать тип атаки
        int damage = RandomHelper.getRandomInRange(getMinDamage(), getMaxDamage());

        unit.decreaseHp(damage);
    }
}
