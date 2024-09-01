package org.example;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        //Классы для работы
        Audio audio = new Audio();
        Terminal terminalClass = new Terminal();
        Menu menu = new Menu();

        // Создаем и запускаем поток для выполнения audio.test()
        Thread audioThread = new Thread(() -> {
            audio.playMenu();
        });

        audioThread.start(); // Запускаем поток

        // Продолжаем выполнение кода в основном потоке
        menu.createMenu();
        /* switch (terminalClass.choose())
        {
            case "1" -> terminalClass.simbel();
            case "2" -> terminalClass.screenTest();
            case "3" -> terminalClass.drawTest();
            default -> System.out.println("Неверный символ");
        }*/

        // Ждем завершения потока audioThread (если нужно)
            audioThread.join();
    }
}
