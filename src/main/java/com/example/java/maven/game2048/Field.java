package com.example.java.maven.game2048;

import java.util.Objects;

public class Field {
    private int value;

    public Field() {
    }

    public Field(Field toCopy) {
        this.value = toCopy.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addToValue(int value) {
        this.value += value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return value == field.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
