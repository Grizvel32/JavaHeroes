package com.company.view;

import com.company.entities.Player;
import com.company.entities.Unit;
import com.company.entities.Wizard;
import com.company.util.ConsoleHelper;

public class WizardView extends CommonUnitView {

    private Player player1;
    private Wizard choosePlayer1UnitAsWizard;

    public WizardView(Player player1, Unit choosePlayer1Unit, Player player2, BattleField battleField) {
        super(choosePlayer1Unit, player2, battleField);
        this.player1 = player1;
        choosePlayer1UnitAsWizard = (Wizard) choosePlayer1Unit;
    }

    @Override
    public void showActionsMenu() {
        ConsoleHelper.printlnMessage("Список действий: ");
        ConsoleHelper.printlnMessage("1.Переместиться");
        ConsoleHelper.printlnMessage("2.Атаковать");
        ConsoleHelper.printlnMessage("3.Колдовать");
    }

    @Override
    public void chooseAction() {
        action = ConsoleHelper.inputInt("Выберите действие: ", 1, 3);
    }

    private void action3() {
        int chooseMagicAction = ConsoleHelper.inputInt("Выберите действие (1-благословить своих, 2-проклясть чужих): ", 1, 2);

        switch (chooseMagicAction) {
            case 1: {

                int unitId = ConsoleHelper.inputInt("Введите для благословения id выбранного существа игока " + player1.getPlayerName() + ": ", player1.getAllUnitsIds());
                Unit unit = player1.getUnitById(unitId);

                choosePlayer1UnitAsWizard.bless(unit);

                ConsoleHelper.printlnMessage("Маг благословил существо "+unit.getSkin()+" теперь оно имеет урон = "+unit.getMaxDamage());

            }
            break;

            case 2: {

                int unitId = ConsoleHelper.inputInt("Введите для проклятия id выбранного существа игока " + player2.getPlayerName() + ": ", player2.getAllUnitsIds());
                Unit unit = player2.getUnitById(unitId);

                choosePlayer1UnitAsWizard.curse(unit);

                ConsoleHelper.printlnMessage("Маг проклял существо "+unit.getSkin()+" теперь оно имеет урон = "+unit.getMinDamage());

            }
            break;
        }
    }

    @Override
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

            case 3: {
                if (choosePlayer1UnitAsWizard.canMagic() == false) {
                    ConsoleHelper.printlnMessage("Нет маны, ход потерян");
                } else {
                    action3();
                }
            }
            break;
        }
    }
}
