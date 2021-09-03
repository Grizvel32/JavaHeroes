package com.company;

import com.company.gamelogic.GameManager;


public class Main {

    public static void main(String[] args) throws Exception {
        GameManager gameManager = new GameManager();
        gameManager.init();
        gameManager.gameLoop();
    }
}
