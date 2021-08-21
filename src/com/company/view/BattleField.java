package com.company.view;

import com.company.entities.Point;

public class BattleField {

    private final char EMPTY_CELL = '.';

    private int countRows;
    private int countColumns;
    private char[][] field;

    public BattleField(int countRows, int countColumns) {
        this.countRows = countRows;
        this.countColumns = countColumns;
        field = new char[countRows][countColumns];
    }

    public void clear() {
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    public void setCellValue(Point point, char value) {
        field[point.i][point.j] = value;
    }

    public void drawField() {
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
                System.out.printf("%2c", field[i][j]);
            }
            System.out.println();
        }
    }

    public boolean isEmpty(int i, int j) {
        return field[i][j] == EMPTY_CELL;
    }
}
