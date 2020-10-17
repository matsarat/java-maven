package com.example.java.maven.game2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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


    public Board(Board toCopy) {
        this.numberOfRows = toCopy.numberOfRows;
        this.numberOfColumns = toCopy.numberOfColumns;
        this.fields = getCopyOfFields(toCopy);
    }

    public void placeTwoInitialElementsOnBoard() {
        for (int i = 0; i < 2; i++) {
            placeNextElementOnBoard();
        }
    }

    public void placeNextElementOnBoard() {
        Field nextElementOnBoard = getRandomFieldWithZeroValue();
        nextElementOnBoard.setValue(2);
    }

    public void sumInRowOrColumnLeftOrUp(Field[] rowOrColumn) {
        for (int index = 0; index < rowOrColumn.length - 1; index++) {
            int currentField = rowOrColumn[index].getValue();
            int nextField = rowOrColumn[index + 1].getValue();
            if (currentField == nextField) {
                rowOrColumn[index].addToValue(currentField);
                rowOrColumn[index + 1].setValue(0);

            }
        }
    }

    public void sumInRowOrColumnRightOrDown(Field[] rowOrColumn) {
        for (int index = (rowOrColumn.length - 1); index > 0; index--) {
            int currentField = rowOrColumn[index].getValue();
            int nextField = rowOrColumn[index - 1].getValue();
            if (currentField == nextField) {
                rowOrColumn[index].addToValue(currentField);
                rowOrColumn[index - 1].setValue(0);

            }
        }
    }

    public void moveLeft() {
        for (Field[] row : this.fields) {
            while (notSortedLeftOrUp(row)) {
                leftOrUpSort(row);
            }
        }
    }

    public void moveRight() {
        for (Field[] row : this.fields) {
            while (notSortedRightOrDown(row)) {
                rightOrDownSort(row);
            }
        }
    }

    public void moveUp() {
        for (Field[] column : getColumns()) {
            while (notSortedLeftOrUp(column)) {
                leftOrUpSort(column);
            }
        }
    }

    public void moveDown() {
        for (Field[] column : getColumns()) {
            while (notSortedRightOrDown(column)) {
                rightOrDownSort(column);
            }
        }
    }

    public void sumLeft() {
        for (Field[] row : this.fields) {
            sumInRowOrColumnLeftOrUp(row);
        }
    }

    public void sumDown() {
        for (Field[] column : getColumns()) {
            sumInRowOrColumnRightOrDown(column);
        }
    }

    public void sumUp() {
        for (Field[] column : getColumns()) {
            sumInRowOrColumnLeftOrUp(column);
        }
    }

    public void sumRight() {
        for (Field[] row : this.fields) {
            sumInRowOrColumnRightOrDown(row);
        }
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.hashCode(fields);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return numberOfRows == board.numberOfRows &&
                numberOfColumns == board.numberOfColumns &&
                Arrays.deepEquals(fields, board.fields);
    }

    @Override
    public String toString() {
        StringBuilder strBoardBuilder = new StringBuilder();
        for (Field[] row : this.fields) {
            strBoardBuilder.append(
                    Arrays.toString(row)
                            .replace(",", "")
                            .replace("[", "")
                            .replace("]", "")
            );
            strBoardBuilder.append("\n");
        }
        return strBoardBuilder.toString().trim();
    }

    public boolean checkIfWon(int winningNumber) {
        for (Field[] row : this.fields) {
            for (Field field : row) {
                if (field.getValue() == winningNumber) {
                    System.out.print(this);
                    System.out.println("YOU WON!");
                    return true;

                }
            }
        }
        return false;
    }

    public boolean checkIfSimilarNeighboringFields() {
        for (Field[] row : this.fields) {
            for (int index = 0; index < row.length - 1; index++) {
                if (row[index].getValue() == row[index + 1].getValue()) {
                    return true;
                }
            }
        }
        for (Field[] column : getColumns()) {
            for (int index = 0; index < column.length - 1; index++) {
                if (column[index].getValue() == column[index + 1].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfGameOver() {
        int zeroValuesOnBoard = getFieldsWithZeroValue().size();
        if (zeroValuesOnBoard > 0) {
            return false;
        }
        if (checkIfSimilarNeighboringFields()) {
            return false;
        }
        System.out.println("YOU LOST!");
        return true;
    }

    private Field[][] getCopyOfFields(Board toCopy) {
        Field[][] copy = new Field[numberOfRows][numberOfColumns];
        for (int rowNumber = 0; rowNumber < numberOfColumns; rowNumber++) {
            for (int columnNumber = 0; columnNumber < numberOfRows; columnNumber++) {
                copy[rowNumber][columnNumber] = new Field(toCopy.fields[rowNumber][columnNumber]);
            }
        }
        return copy;
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

    private List<Field> getFieldsWithZeroValue() {
        List<Field> fieldsWithZeroValue = new ArrayList<>();
        for (Field[] row : this.fields) {
            for (Field field : row) {
                if (field.getValue() == 0) {
                    fieldsWithZeroValue.add(field);
                }
            }
        }
        return fieldsWithZeroValue;
    }

    private Field getRandomFieldWithZeroValue() {
        Random random = new Random();
        List<Field> fieldsWithZeroValues = getFieldsWithZeroValue();
        return fieldsWithZeroValues.get(random.nextInt(fieldsWithZeroValues.size()));
    }

    private boolean notSortedLeftOrUp(Field[] rowOrColumn) {
        for (int index = 0; index < rowOrColumn.length - 1; index++) {
            if (rowOrColumn[index].getValue() == 0 && rowOrColumn[index + 1].getValue() > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean notSortedRightOrDown(Field[] rowOrColumn) {
        for (int index = rowOrColumn.length - 1; index > 0; index--) {
            int currentFieldValue = rowOrColumn[index].getValue();
            int previousFieldValue = rowOrColumn[index - 1].getValue();
            if (currentFieldValue == 0 && previousFieldValue > 0) {
                return true;
            }
        }
        return false;
    }

    private void leftOrUpSort(Field[] rowOrColumn) {
        for (int index = 0; index < rowOrColumn.length - 1; index++) {
            int currentFieldValue = rowOrColumn[index].getValue();
            int nextFieldValue = rowOrColumn[index + 1].getValue();
            if (currentFieldValue == 0 && nextFieldValue > 0) {
                rowOrColumn[index].setValue(nextFieldValue);
                rowOrColumn[index + 1].setValue(currentFieldValue);

            }
        }
    }

    private void rightOrDownSort(Field[] rowOrColumn) {
        for (int index = rowOrColumn.length - 1; index > 0; index--) {
            int currentFieldValue = rowOrColumn[index].getValue();
            int previousFieldValue = rowOrColumn[index - 1].getValue();
            if (currentFieldValue == 0 && previousFieldValue > 0) {
                rowOrColumn[index].setValue(previousFieldValue);
                rowOrColumn[index - 1].setValue(currentFieldValue);
            }
        }
    }

}


