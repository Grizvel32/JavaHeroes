package com.company.entities;

import com.company.util.RandomHelper;
import com.company.util.SettingsHelper;

public class Wizard extends Unit {
    private int manaPoints;

    public Wizard(Point point) {
        super(point,
                SettingsHelper.getCharValue("wizardSkin"),
                RandomHelper.getRandomInRange(
                        SettingsHelper.getIntValue("wizardMinHp"),
                        SettingsHelper.getIntValue("wizardMaxHp")
                ),
                SettingsHelper.getIntValue("wizardStepDistance"),
                SettingsHelper.getIntValue("wizardAttackDistance"),
                SettingsHelper.getIntValue("wizardMinDamage"),
                SettingsHelper.getIntValue("wizardMaxDamage")
        );
        manaPoints = SettingsHelper.getIntValue("wizardManaPoints");
    }

    @Override
    public void attack(Unit unit) {
        //todo доделать атаку руками или стрелами, при ближнем бое делить на 2 + выбрать тип атаки
        int damage = RandomHelper.getRandomInRange(getMinDamage(), getMaxDamage());

        unit.decreaseHp(damage);
    }
}
