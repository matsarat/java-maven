package com.example.java.maven.game2048;

import java.util.Arrays;

public class Board {
    private final Field[][] fields;
    private final int numberOfRows;
    private final int numberOfColumns;


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

    private Field[] getColumn(int columnNumber) {
        Field[] column = new Field[numberOfRows];
        for (int rowNumber = 0; rowNumber < numberOfRows; rowNumber++) {
            column[rowNumber] = fields[rowNumber][columnNumber];
        }
        return column;
    }

    private Field[][] getColumns() {
        Field[][] columns = new Field[numberOfRows][numberOfColumns];
        for (int columnNumber = 0; columnNumber < numberOfColumns; columnNumber++) {
            columns[columnNumber] = getColumn(columnNumber);
        }
        return columns;
    }


    private boolean sortedLeftOrUp(Field[] rowOrColumn) {
        for (int index = 0; index < rowOrColumn.length - 1; index++) {
            if (rowOrColumn[index].getValue() == 0 && rowOrColumn[index + 1].getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean sortedRightOrDown(Field[] rowOrColumn) {
        for (int index = rowOrColumn.length - 1; index > -1; index--) {
            if (rowOrColumn[index].getValue() == 0 && rowOrColumn[index - 1].getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    private void leftOrUpSort(Field[] rowOrColumn) {
        for (int index = 0; index < rowOrColumn.length - 1; index++) {
            if (rowOrColumn[index].getValue() == 0 && rowOrColumn[index + 1].getValue() > 0) {
                int currentFieldValue = rowOrColumn[index].getValue();
                int nextFieldValue = rowOrColumn[index + 1].getValue();
                rowOrColumn[index].setValue(nextFieldValue);
                rowOrColumn[index + 1].setValue(currentFieldValue);
            }
        }
    }


    private void rightOrDownSort(Field[] rowOrColumn) {
        for (int index = rowOrColumn.length - 1; index > -1; index--) {
            if (rowOrColumn[index].getValue() == 0 && rowOrColumn[index - 1].getValue() > 0) {
                int currentFieldValue = rowOrColumn[index].getValue();
                int previousFieldValue = rowOrColumn[index - 1].getValue();
                rowOrColumn[index].setValue(previousFieldValue);
                rowOrColumn[index - 1].setValue(currentFieldValue);
            }
        }
    }


}

