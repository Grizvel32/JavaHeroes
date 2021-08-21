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

        int minI, maxI, minJ, maxJ;

        int quadRows = battleFieldCountRows / 2 / 4;
        int quadColumns = battleFieldCountColumns / 2 / 4;

        minI = quadRows;
        maxI = quadRows * 3;

        minJ = quadColumns;
        maxJ = quadColumns * 3;

        ConsoleHelper.printlnMessage("Ввод существ для игрока 1: ");
        inputUnitsForPlayer(player1, minI, maxI, minJ, maxJ, countUnits);

        minI = quadRows;
        maxI = quadRows * 3;

        minJ = battleFieldCountColumns / 2 + quadColumns;
        maxJ = battleFieldCountColumns / 2 + quadColumns * 3;

        ConsoleHelper.printlnMessage("Ввод существ для игрока 2: ");
        inputUnitsForPlayer(player2, minI, maxI, minJ, maxJ, countUnits);
    }

    public void drawBattleField() {
        battleField.clear();

        for (int i = 0; i < player1.getUnits().size(); i++) {
            Unit currentUnit = player1.getUnits().get(i);
            battleField.setCellValue(currentUnit.getPoint(), currentUnit.getSkin());
        }

        for (int i = 0; i < player2.getUnits().size(); i++) {
            Unit currentUnit = player2.getUnits().get(i);
            battleField.setCellValue(currentUnit.getPoint(), currentUnit.getSkin());
        }

        battleField.drawField();
    }

    private Point getRandomPointForUnit(int minI, int maxI, int minJ, int maxJ) {

        int i, j;

        do {
            i = RandomHelper.getRandomInRange(minI, maxI);
            j = RandomHelper.getRandomInRange(minJ, maxJ);
        } while (battleField.isEmpty(i, j) == false);

        return new Point(i, j);
    }

    private void inputUnitsForPlayer(Player player, int minI, int maxI, int minJ, int maxJ, int countUnits) {
        for (int i = 0; i < countUnits; i++) {
            ConsoleHelper.printlnMessage(String.format("Существо %d из %d", i + 1, countUnits));

            int typeCurrentUnit = ConsoleHelper.inputInt("Кого вы хотите добавить(1-лучник, 2-маг, 3-наездник): ", 1, 3);

            Point currentPoint = getRandomPointForUnit(minI, maxI, minJ, maxJ);

            switch (typeCurrentUnit) {
                case 1:
                    player.add(new Archer(currentPoint));
                    break;
            }
        }
    }

}
