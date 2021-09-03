package com.company.gamelogic;

import com.company.entities.supportings.Point;
import com.company.entities.supportings.RectangleArea;
import com.company.entities.*;
import com.company.entities.Archer;
import com.company.entities.Horseman;
import com.company.entities.Unit;
import com.company.entities.Wizard;
import com.company.util.ConsoleHelper;
import com.company.util.RandomHelper;
import com.company.util.SettingsHelper;
import com.company.view.BattleField;
import com.company.view.ViewHelper;

public class GameManager {

    private Player player1, player2;
    private BattleField battleField;

    private int battleFieldCountRows;
    private int battleFieldCountColumns;

    public GameManager() {
        SettingsHelper.loadFromFile();

        battleFieldCountRows = SettingsHelper.getIntValue("battleFieldCountRows");
        battleFieldCountColumns = SettingsHelper.getIntValue("battleFieldCountColumns");
    }

    public void init() {
        battleField = new BattleField(battleFieldCountRows, battleFieldCountColumns);
        battleField.clear();

        String player1Name = ConsoleHelper.inputString("Введите имя игрока 1: ");
        player1 = new Player(player1Name);

        String player2Name = ConsoleHelper.inputString("Введите имя игрока 2: ");
        player2 = new Player(player2Name);

        int countUnits = ConsoleHelper.inputInt("По сколько существ будет у героев?(от 1 до 5): ", 1, 5);

        ConsoleHelper.printlnMessage("Ввод существ для игрока " + player1.getPlayerName() + ": ");
        inputUnitsForPlayer(player1, getPlayer1RandomArea(), countUnits);

        ConsoleHelper.printlnMessage("Ввод существ для игрока " + player2.getPlayerName() + ": ");
        inputUnitsForPlayer(player2, getPlayer2RandomArea(), countUnits);
    }

    public void gameLoop() throws Exception {
        while (true) {
            drawBattleField();

            ConsoleHelper.printlnMessage("Ход игрока: " + player1.getPlayerName());
            ConsoleHelper.printlnDivider();

            ConsoleHelper.printlnMessage("Список существ игрока " + player1.getPlayerName() + ": ");
            ViewHelper.showPlayerUnits(player1);
            ConsoleHelper.printlnDivider();

            ConsoleHelper.printlnMessage("Список существ игрока " + player2.getPlayerName() + ": ");
            ViewHelper.showPlayerUnits(player2);
            ConsoleHelper.printlnDivider();

            int choosePlayer1UnitId = ConsoleHelper.inputInt("Введите id выбранного существа игока " + player1.getPlayerName() + ": ", player1.getAllUnitsIds());

            Unit choosePlayer1Unit = player1.getUnitById(choosePlayer1UnitId);

            if (choosePlayer1Unit instanceof Horseman) {
                ConsoleHelper.printlnMessage("Список действий: ");
                ConsoleHelper.printlnMessage("1.Переместиться");
                ConsoleHelper.printlnMessage("2.Атаковать");
                int action = ConsoleHelper.inputInt("Выберите действие: ", 1, 2);

                switch (action) {
                    case 1: {
                        RectangleArea fieldArea = new RectangleArea(0, battleFieldCountRows, 0, battleFieldCountColumns);
                        int moveDirection = ConsoleHelper.inputInt("Выберите направление движения(1-вверх, 2-вниз, 3-влево, 4-вправо): ", 1, 4);
                        choosePlayer1Unit.move(moveDirection, fieldArea);
                    }
                    break;
                    case 2: {
                        //todo do attack
                        int defendUnitId = ConsoleHelper.inputInt("Введите id защищающегося существа игока "+player2.getPlayerName()+": ", player2.getAllUnitsIds());

                        Unit choosePlayer2Unit = player2.getUnitById(defendUnitId);

                        choosePlayer1Unit.canAttack(choosePlayer2Unit);
                    }
                    break;
                }
            }

            /*int attackUnitId = ConsoleHelper.inputInt("Введите id атакующего существа игока "+player1.getPlayerName()+": ", player1.getAllUnitsIds());

            int defendUnitId = ConsoleHelper.inputInt("Введите id защищающегося существа игока "+player2.getPlayerName()+": ", player2.getAllUnitsIds());

            ConsoleHelper.printlnDivider();

            ConsoleHelper.printlnMessage("Выбранное атакующее существо: ");
            ViewHelper.showUnit(player1.getUnitById(attackUnitId));

            ConsoleHelper.printlnMessage("Выбранное защищающееся существо: ");
            ViewHelper.showUnit(player2.getUnitById(defendUnitId));*/

            ConsoleHelper.waitEnter();
        }
    }

    //region helper private methods

    private void drawBattleField() {
        battleField.clear();

        fillBattleFieldPlayerUnits(player1);
        fillBattleFieldPlayerUnits(player2);

        battleField.drawField();
    }

    private RectangleArea getPlayer1RandomArea() {
        int minI, maxI, minJ, maxJ;

        int quadRows = battleFieldCountRows / 4;
        int quadColumns = battleFieldCountColumns / 2 / 4;

        minI = quadRows;
        maxI = quadRows * 3;

        minJ = quadColumns;
        maxJ = quadColumns * 3;

        return new RectangleArea(minI, maxI, minJ, maxJ);
    }

    private RectangleArea getPlayer2RandomArea() {
        int minI, maxI, minJ, maxJ;

        int quadRows = battleFieldCountRows / 4;
        int quadColumns = battleFieldCountColumns / 2 / 4;

        minI = quadRows;
        maxI = quadRows * 3;

        minJ = battleFieldCountColumns / 2 + quadColumns;
        maxJ = battleFieldCountColumns / 2 + quadColumns * 3;

        return new RectangleArea(minI, maxI, minJ, maxJ);
    }

    private void fillBattleFieldPlayerUnits(Player player) {
        for (int i = 0; i < player.getUnits().size(); i++) {
            Unit currentUnit = player.getUnits().get(i);
            battleField.setCellValue(currentUnit.getPoint(), currentUnit.getSkin());
        }
    }

    private Point getRandomPointForUnit(RectangleArea rectangleArea) {

        int i, j;

        do {
            i = RandomHelper.getRandomInRange(rectangleArea.minI, rectangleArea.maxI);
            j = RandomHelper.getRandomInRange(rectangleArea.minJ, rectangleArea.maxJ);
        } while (battleField.isEmpty(i, j) == false);

        return new Point(i, j);
    }

    private void inputUnitsForPlayer(Player player, RectangleArea rectangleArea, int countUnits) {
        for (int i = 0; i < countUnits; i++) {
            ConsoleHelper.printlnMessage(String.format("Существо %d из %d", i + 1, countUnits));

            int typeCurrentUnit = ConsoleHelper.inputInt("Кого вы хотите добавить(1-лучник, 2-маг, 3-наездник): ", 1, 3);

            Point currentPoint = getRandomPointForUnit(rectangleArea);

            switch (typeCurrentUnit) {
                case 1:
                    player.addUnit(new Archer(currentPoint));
                    break;
                case 2:
                    player.addUnit(new Wizard(currentPoint));
                    break;
                case 3:
                    player.addUnit(new Horseman(currentPoint));
                    break;
            }
        }
    }

    //endregion

}
