package com.company;

import com.company.entities.Archer;
import com.company.gamelogic.GameManager;
import com.company.util.RandomHelper;

public class Main {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.init();
        gameManager.drawBattleField();
    }
}
