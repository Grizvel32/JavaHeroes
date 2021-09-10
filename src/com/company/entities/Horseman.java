package com.company.entities;

import com.company.entities.supportings.Point;
import com.company.util.RandomHelper;
import com.company.util.SettingsHelper;

public class Horseman extends Unit {

    public Horseman(Point point) {
        super(point,
                SettingsHelper.getCharValue("horsemanSkin"),
                RandomHelper.getRandomInRange(
                        SettingsHelper.getIntValue("horsemanMinHp"),
                        SettingsHelper.getIntValue("horsemanMaxHp")
                ),
                SettingsHelper.getIntValue("horsemanStepDistance"),
                SettingsHelper.getIntValue("horsemanAttackDistance"),
                SettingsHelper.getIntValue("horsemanMinDamage"),
                SettingsHelper.getIntValue("horsemanMaxDamage")
        );
    }

    @Override
    public int attack(Unit unit) {
        int damage = RandomHelper.getRandomInRange(getMinDamage(), getMaxDamage());

        unit.decreaseHp(damage);

        return damage;
    }

    @Override
    public String getInfo() {
        return String.format("Существо: %c; id: %d, позиция: i-%d, j-%d; здоровье: %d, сила атаки: от %d до %d", getSkin(), getId(), getPoint().i, getPoint().j, getHp(), getMinDamage(), getMaxDamage());
    }
}
