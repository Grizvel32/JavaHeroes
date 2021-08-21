package com.company.gamelogic;

import com.company.entities.Archer;
import com.company.entities.Player;
import com.company.entities.Point;
import com.company.entities.Unit;
import com.company.util.ConsoleHelper;
import com.company.util.RandomHelper;
import com.company.util.SettingsHelper;
import com.company.view.BattleField;

public class GameManager {
    private class RandomArea{
        public int minI, maxI, minJ, maxJ;

        public RandomArea(int minI, int maxI, int minJ, int maxJ) {
            this.minI = minI;
            this.maxI = maxI;
            this.minJ = minJ;
            this.maxJ = maxJ;
        }
    }

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

        int countUnits = ConsoleHelper.inputInt("По сколько существ будет у героев?: ", 1, 5);

        ConsoleHelper.printlnMessage("Ввод существ для игрока 1: ");
        inputUnitsForPlayer(player1, getPlayer1RandomArea(), countUnits);

        ConsoleHelper.printlnMessage("Ввод существ для игрока 2: ");
        inputUnitsForPlayer(player2, getPlayer2RandomArea(), countUnits);
    }

    public void drawBattleField() {
        battleField.clear();

        fillBattleFieldPlayerUnits(player1);
        fillBattleFieldPlayerUnits(player2);

        battleField.drawField();
    }

    private RandomArea getPlayer1RandomArea(){
        int minI, maxI, minJ, maxJ;

        int quadRows = battleFieldCountRows / 2 / 4;
        int quadColumns = battleFieldCountColumns / 2 / 4;

        minI = quadRows;
        maxI = quadRows * 3;

        minJ = quadColumns;
        maxJ = quadColumns * 3;

        return new RandomArea(minI, maxI, minJ, maxJ);
    }

    private RandomArea getPlayer2RandomArea(){
        int minI, maxI, minJ, maxJ;

        int quadRows = battleFieldCountRows / 2 / 4;
        int quadColumns = battleFieldCountColumns / 2 / 4;

        minI = quadRows;
        maxI = quadRows * 3;

        minJ = battleFieldCountColumns / 2 + quadColumns;
        maxJ = battleFieldCountColumns / 2 + quadColumns * 3;

        return new RandomArea(minI, maxI, minJ, maxJ);
    }

    private void fillBattleFieldPlayerUnits(Player player) {
        for (int i = 0; i < player.getUnits().size(); i++) {
            Unit currentUnit = player.getUnits().get(i);
            battleField.setCellValue(currentUnit.getPoint(), currentUnit.getSkin());
        }
    }

    private Point getRandomPointForUnit(RandomArea randomArea) {

        int i, j;

        do {
            i = RandomHelper.getRandomInRange(randomArea.minI, randomArea.maxI);
            j = RandomHelper.getRandomInRange(randomArea.minJ, randomArea.maxJ);
        } while (battleField.isEmpty(i, j) == false);

        return new Point(i, j);
    }

    private void inputUnitsForPlayer(Player player, RandomArea randomArea, int countUnits) {
        for (int i = 0; i < countUnits; i++) {
            ConsoleHelper.printlnMessage(String.format("Существо %d из %d", i + 1, countUnits));

            int typeCurrentUnit = ConsoleHelper.inputInt("Кого вы хотите добавить(1-лучник, 2-маг, 3-наездник): ", 1, 3);

            Point currentPoint = getRandomPointForUnit(randomArea);

            switch (typeCurrentUnit) {
                case 1:
                    player.add(new Archer(currentPoint));
                    break;
            }
        }
    }

}
