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

     //tym sie nie przejmuj, tego raczej nie bedziesz umial uzyc
    private Board(Board toCopy){
        this.numberOfRows = toCopy.numberOfRows;
        this.numberOfColumns = toCopy.numberOfColumns;
        this.fields = getCopyOfFields();
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

    public void placeTwoInitialElementsOnBoard() {
        for (int i = 0; i < 2; i++) {
            placeNextElementOnBoard();
        }
    }

    public void placeNextElementOnBoard() {
        Field nextElementOnBoard = getRandomFieldWithZeroValue();
        nextElementOnBoard.setValue(2);
    }

    public void sumInRowOrColumn(Field[] rowOrColumn) {
        for (int index = 0; index < rowOrColumn.length - 1; index++) {
            Field currentField = rowOrColumn[index];
            Field nextField = rowOrColumn[index + 1];
            if (currentField.getValue() == nextField.getValue()) {
                currentField.addToValue(nextField.getValue());
                nextField.setValue(0);
            }
        }
    }

    public void moveLeft() {
        for (Field[] row : this.fields) {
            while (sortedLeftOrUp(row)) {
                leftOrUpSort(row);
            }
        }
    }

    public void moveRight() {
        for (Field[] row : this.fields) {
            while (sortedRightOrDown(row)) {
                rightOrDownSort(row);
            }
        }
    }

    public void moveUp() {
        for (Field[] column : getColumns()) {
            while (sortedRightOrDown(column)) {
                rightOrDownSort(column);
            }
        }
    }

    public void moveDown() {
        for (Field[] column : getColumns()) {
            while (sortedRightOrDown(column)) {
                rightOrDownSort(column);
            }
        }
    }

    public void sumLeftOrRight() {
        for (Field[] row : this.fields) {
            sumInRowOrColumn(row);
        }
    }

    public void sumUpOrDown() {
        for (Field[] column : getColumns()) {
            sumInRowOrColumn(column);
        }
    }


    private Field[][] getCopyOfFields(){
        Field[][] copy = new Field[numberOfRows][numberOfColumns];
        for (int rowNumber = 0; rowNumber < numberOfColumns; rowNumber++) {
            for (int columnNumber = 0; columnNumber < numberOfRows; columnNumber++) {
                copy[rowNumber][columnNumber] = new Field(this.fields[rowNumber][columnNumber]);
            }
        }
        return copy;
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
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.hashCode(fields);
        return result;
    }


    public boolean canMoveUp() {
        Board copyOfCurrentBoard = new Board(this);
        copyOfCurrentBoard.moveUp();
        return !copyOfCurrentBoard.equals(this);
    }

    public boolean canMoveDown() {
        Board copyOfCurrentBoard = new Board(this);
        copyOfCurrentBoard.moveDown();
        return !copyOfCurrentBoard.equals(this);
    }

    public boolean canMoveRight() {
        Board copyOfCurrentBoard = new Board(this);
        copyOfCurrentBoard.moveRight();
        return !copyOfCurrentBoard.equals(this);
    }

    public boolean canMoveLeft() {
        Board copyOfCurrentBoard = new Board(this);
        copyOfCurrentBoard.moveLeft();
        return !copyOfCurrentBoard.equals(this);
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

    private boolean sortedLeftOrUp(Field[] rowOrColumn) {
        for (int index = 0; index < rowOrColumn.length - 1; index++) {
            Field currentField = rowOrColumn[index];
            Field nextField = rowOrColumn[index + 1];
            if (currentField.getValue() == 0 && nextField.getValue() > 0) {
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
            Field currentField = rowOrColumn[index];
            Field nextField = rowOrColumn[index + 1];
            if (currentField.getValue() == 0 && nextField.getValue() > 0) {
                int currentFieldValue = currentField.getValue();
                int nextFieldValue = nextField.getValue();
                currentField.setValue(nextFieldValue);
                nextField.setValue(currentFieldValue);
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


