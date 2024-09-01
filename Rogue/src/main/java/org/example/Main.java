package org.example;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Terminal terminalClass = new Terminal();
        switch (terminalClass.choose()) {

            case "1" -> terminalClass.simbel();

            case "2" -> terminalClass.screenTest();

            case "3" -> terminalClass.drawTest();

            default -> System.out.println("Неверный символ");
        }
    }
}
