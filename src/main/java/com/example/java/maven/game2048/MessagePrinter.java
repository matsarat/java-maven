package com.example.java.maven.game2048;

public class MessagePrinter {

    private MessagePrinter() {
    }

    public static void printInstructionsBeforeGame(){
        String instructions = ("INSTRUCTIONS:" + '\n' +
                "To move left   - insert a" + '\n' +
                "To move right  - insert d" + '\n' +
                "To move up     - insert w" +'\n' +
                "To move down   - insert s" + '\n'
        );

        System.out.println(instructions);
    }
}
