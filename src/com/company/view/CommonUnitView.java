package com.company.view;

import com.company.entities.Player;
import com.company.entities.Unit;
import com.company.util.ConsoleHelper;

public class CommonUnitView {
    protected Unit choosePlayer1Unit;
    protected Player player2;
    protected BattleField battleField;

    protected int action;

    public CommonUnitView(Unit choosePlayer1Unit, Player player2, BattleField battleField) {
        this.choosePlayer1Unit = choosePlayer1Unit;
        this.player2 = player2;
        this.battleField = battleField;
    }

    public void showActionsMenu() {
        ConsoleHelper.printlnMessage("Список действий: ");
        ConsoleHelper.printlnMessage("1.Переместиться");
        ConsoleHelper.printlnMessage("2.Атаковать");
    }

    public void chooseAction() {
        action = ConsoleHelper.inputInt("Выберите действие: ", 1, 2);
    }

    protected void action1(){
        int moveDirection = ConsoleHelper.inputInt("Выберите направление движения(1-вверх, 2-вниз, 3-влево, 4-вправо): ", 1, 4);
        choosePlayer1Unit.move(moveDirection, battleField.getBattleFieldArea());

        ConsoleHelper.printlnMessage(choosePlayer1Unit.getSkin() + " походил в направлении " + moveDirection + " на " + choosePlayer1Unit.getStepDistance() + " клеток");
    }

    protected void action2(){
        boolean canAttackSomeBody = false;
        for (int i = 0; i < player2.getUnits().size(); i++) {
            if (choosePlayer1Unit.canAttack(player2.getUnits().get(i)) == true) {
                canAttackSomeBody = true;
                break;
            }
        }

        if (canAttackSomeBody == false) {
            ConsoleHelper.printlnMessage("Нет ни одного юнита в зоне атаки, ход потерян");
        } else {

            boolean canAttack;
            Unit choosePlayer2Unit;

            do {
                int defendUnitId = ConsoleHelper.inputInt("Введите id защищающегося существа игока " + player2.getPlayerName() + ": ", player2.getAllUnitsIds());

                choosePlayer2Unit = player2.getUnitById(defendUnitId);

                canAttack = choosePlayer1Unit.canAttack(choosePlayer2Unit);

                if (canAttack == false) {
                    ConsoleHelper.printlnMessage("Юнит находиться вне зоны атаки");
                }
            }
            while (canAttack == false);

            int damage = choosePlayer1Unit.attack(choosePlayer2Unit);

            ConsoleHelper.printlnMessage(choosePlayer1Unit.getSkin() + " атаковал " + choosePlayer2Unit.getSkin() + " на " + damage + " едениц");
            ConsoleHelper.printlnMessage("У атакованного " + choosePlayer2Unit.getSkin() + " осталось " + choosePlayer2Unit.getHp() + " едениц здоровья");
        }
    }

    public void doAction() {
        switch (action) {
            case 1: {
                action1();
            }
            break;
            case 2: {
                action2();
            }
            break;
        }
    }
}
