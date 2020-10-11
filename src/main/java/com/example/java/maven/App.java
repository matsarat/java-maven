package com.example.java.maven;

import com.example.java.maven.game2048.Field;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Field pizda = new Field();
        Field cipa = new Field();
        pizda.addToValue(5);
        cipa.addToValue(5);
        System.out.println(pizda.getValue());
        System.out.println(pizda);
        System.out.println(cipa);

        System.out.println(pizda.getValue() == cipa.getValue());
        System.out.println(pizda == cipa);
        System.out.println(pizda.equals(cipa));

        Field[] array = new Field[4];
        System.out.println(array.length);
        for (Field field : array) {
            System.out.println(field);
        }
        for (int index = 0; index < array.length; index++) {
            array[index] = new Field();
        }
        for (Field field : array) {
            System.out.println(field);
        }
        int counter = array.length;
        while (counter > 0) {
            counter--;
            array[counter].addToValue(counter + 1);
        }
        System.out.println(Arrays.toString(array));
        Field[][] fields = new Field[2][3];
        System.out.println(fields);
        System.out.println(Arrays.toString(fields));

        for (Field[] row : fields) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(ifChecker("PIZDA"));
    }

    public static String ifChecker(String string) {
        if (string.equalsIgnoreCase("pizda")) {
            return "nad głową";
        } else if (string.equals("kutas")) {
            return "kozła";
        } else {
            return "niekutasniepizda";
        }
    }
}
