package com.company.entities;

import com.company.entities.supportings.Point;
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
    public int attack(Unit unit) {
        int damage = RandomHelper.getRandomInRange(getMinDamage(), getMaxDamage());

        unit.decreaseHp(damage);

        return damage;
    }

    public boolean canMagic() {
        return manaPoints > 0;
    }

    public void bless(Unit unit) {
        unit.setMinDamage(unit.getMaxDamage());
        manaPoints--;
    }

    public void curse(Unit unit) {
        unit.setMaxDamage(unit.getMinDamage());
        manaPoints--;
    }

    @Override
    public String getInfo() {
        return String.format("Существо: %c; id: %d, позиция: i-%d, j-%d; здоровье: %d, сила атаки: от %d до %d; оставшееся кол-во маны: %d", getSkin(), getId(), getPoint().i, getPoint().j, getHp(), getMinDamage(), getMaxDamage(), manaPoints);
    }
}
