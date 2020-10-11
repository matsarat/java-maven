package com.example.java.maven.game2048;

import java.util.Arrays;

public class Board {
    private Field[][] fields;
    private int numberOfRows;
    private int numberOfColumns;

    public Board(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.fields = new Field[numberOfRows][numberOfColumns];
        for (Field[] row : this.fields) {
            for (int index = 0; index < row.length; index++) {
                row[index] = new Field();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder strBoardBuilder = new StringBuilder();
        for (Field[] row : this.fields) {
            strBoardBuilder.append(
                    Arrays.toString(row)
                            .replace(',', ' ')
                            .replace('[', ' ')
                            .replace(']', ' ')
            );
            strBoardBuilder.append("\n");
        }
        return strBoardBuilder.toString();
    }
}
