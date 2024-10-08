package org.example;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;

import java.awt.*;
import java.io.IOException;
import com.github.lalyos.jfiglet.FigletFont;
import com.googlecode.lanterna.input.MouseAction;

public class Menu
{
    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();   //Нужно для отрисовки консоли
    com.googlecode.lanterna.terminal.Terminal terminal;
    Screen screen;




    public void callTerm() throws IOException   //Штука для создания консоли
    {
        terminal = defaultTerminalFactory.createTerminalEmulator();  //Создаём консоль
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    public void createMenu() throws IOException, InterruptedException {
        callTerm(); //Вызов терминала

        final TextGraphics textGraphics = terminal.newTextGraphics();   //Для текста

        String text = "Dark Rogue";  // Что будет в названии

        String asciiArt = FigletFont.convertOneLine(text);

        // Получаем размер терминала
        TerminalSize terminalSize = terminal.getTerminalSize();
        int terminalWidth = terminalSize.getColumns();

        // Разбиваем текст на строки и печатаем их по центру
        String[] lines = asciiArt.split("\n");
        int y = 4; // начальная строка для печати

        for (String line : lines) {
            int textStartPosition = (terminalWidth - line.length()) / 2;
            textGraphics.putString(textStartPosition, y++, line);
        }

        terminal.setCursorVisible(false);

        KeyStroke keyStroke = terminal.readInput();

        terminal.flush();

        int R = 169, G = 169, B = 169;  //Название затемняется
        while (R > 0)
        {
            coolCloseName(R, G, B, text, 500);
            if(R - 20 > 0)
            {
                R -= 20;
                G -= 20;
                B -= 20;
            }
            else
            {
                R = 0;
                G = 0;
                B = 0;
            }
        }

        R = 255; G = 255; B = 255;
        coolCloseName(R, G, B, "©JOYCAP", 10000);

        terminal.close();
    }

    public void coolCloseName(int R, int G, int B, String text, int sleepTime) throws IOException, InterruptedException //Вывод разного текста во весь экран
    {
        final TextGraphics textGraphics = terminal.newTextGraphics();
        String asciiArt = FigletFont.convertOneLine(text);

        TerminalSize terminalSize = terminal.getTerminalSize();
        int terminalWidth = terminalSize.getColumns();

        // Разбиваем текст на строки и печатаем их по центру
        String[] lines = asciiArt.split("\n");
        int y = 4; // начальная строка для печати

        terminal.clearScreen();

        TextColor.RGB myColor = new TextColor.RGB(R, G, B);
        textGraphics.setForegroundColor(myColor);
        for (String line : lines) {
            int textStartPosition = (terminalWidth - line.length()) / 2;
            textGraphics.putString(textStartPosition, y++, line);
        }
        terminal.flush();
        Thread.sleep(sleepTime);

    }

}
