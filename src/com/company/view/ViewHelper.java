package com.company.view;

import com.company.entities.Player;
import com.company.entities.Unit;

public class ViewHelper {
    public static void showPlayerUnits(Player player) {
        for (int i = 0; i < player.getUnits().size(); i++) {
            Unit currentUnit = player.getUnits().get(i);

            System.out.printf("%s\n", currentUnit.getInfo());
        }
    }

    public static void showUnit(Unit unit) {
        System.out.printf("%s\n", unit.getInfo());
    }
}
