package org.example;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.Random;

public class Terminal {

    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();   //Нужно для отрисовки консоли
    com.googlecode.lanterna.terminal.Terminal terminal;   //Сама консоль
    Screen screen;
    Random random = new Random();

    public static void main(String[] args)
    {

    }

    public void callTerm() throws IOException
    {
        terminal = defaultTerminalFactory.createTerminalEmulator();  //Создаём консоль
    }

    public String choose() throws IOException //Нажата клавиша
    {
        String answer;

        //terminal = defaultTerminalFactory.createTerminalEmulator();     //Создаём консоль
        callTerm();
        final TextGraphics textGraphics = terminal.newTextGraphics();   //Для текста

        textGraphics.drawLine(5, 4, terminal.getTerminalSize().getColumns() - 1, 4, ' ');   //Местоположение текста
        textGraphics.putString(5, 4, "Нажмите номер, для вызова команды начиная с 1: ", SGR.BOLD);
        textGraphics.putString(5, 5, "  1 -> Проверка обработки нажатия клавиш");
        textGraphics.putString(5, 6, "  2 -> Случайные цвета");
        textGraphics.putString(5, 7, "  3 -> Рисование линий");

        terminal.setCursorVisible(false);   //Курсор печати невидим

        KeyStroke keyStroke = terminal.readInput();     //Для обработки клавиш

        answer = String.valueOf(keyStroke.getCharacter());
        terminal.flush();       //Отображение на терминал, отрисовка результата
        terminal.close();   //Завершает работу

        return answer;

    }
    public void drawTest() throws IOException   //Рисование
    {
        //com.googlecode.lanterna.terminal.Terminal terminal = defaultTerminalFactory.createTerminal();
        callTerm();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);     //Создание консоли

        TextGraphics textGraphics = screen.newTextGraphics();   //Новые символы для консоли
        TerminalSize terminalSize = screen.getTerminalSize();   //Размер окна консоли

        String sizeLabel = "Terminal Size: " + terminalSize;    //Переменная которую выводят на экран
        TerminalSize labelBoxSize = new TerminalSize(sizeLabel.length() + 2, 3);    //Переменная содержит данные о необходимом для текста месте сначала столбцы, потом строки
        TerminalPosition labelBoxTopLeft = new TerminalPosition(1, 1);  //Переменная с позицией 1 1, для корректировки отрисовки слева
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 1);    //Для корректировки отрисовки справа


        textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');     //Надо будет разузнать

        textGraphics.drawLine(              //Отрисовка верхней горизонтальной линии
                labelBoxTopLeft.withRelativeColumn(1),      //Начальная точка
                labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 2),      //Конечная точка        Используется метод getColumns
                Symbols.DOUBLE_LINE_HORIZONTAL);        //Тип символа
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(labelBoxSize.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);

        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);            //Отрисовка вертикалей слева
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);    //Справа
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

        textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), sizeLabel);  //Вывод переменной внутри получившейся коробки

        screen.refresh();
    }
    public void screenTest() throws IOException //Покрас фона терминала
    {
        //com.googlecode.lanterna.terminal.Terminal terminal = defaultTerminalFactory.createTerminal();
        callTerm();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);     //Обычный запуск

        TerminalSize terminalSize = screen.getTerminalSize();   //Переменная в которую записан размер окна терминала

        for(int column = 0; column < terminalSize.getColumns(); column++) {
            for(int row = 0; row < terminalSize.getRows(); row++) {            //Покрывает весь экран
                screen.setCharacter(column, row, new TextCharacter(
                        ' ',
                        TextColor.ANSI.DEFAULT,
                        TextColor.ANSI.values()[random.nextInt(TextColor.ANSI.values().length)]));  //Случайным образом закрышивает квадратик
            }
        }
        screen.refresh();       //Отрисока на терминал
    }
    public void simbel() throws IOException   //Проверка обработки нажатия клавиш
    {
        //terminal = defaultTerminalFactory.createTerminal();     //Создаём консоль
        callTerm();
        final TextGraphics textGraphics = terminal.newTextGraphics();   //Для текста
        textGraphics.putString(5, 2, "Проверка обработки нажатия клавиш: ", SGR.BOLD);     //Вывод
        terminal.setCursorVisible(false);

        KeyStroke keyStroke = terminal.readInput();     //Для обработки клавиш

        while(keyStroke.getKeyType() != KeyType.Escape) {       //Обработка

            textGraphics.drawLine(5, 4, terminal.getTerminalSize().getColumns() - 1, 4, ' ');   //Местоположение текста
            textGraphics.putString(5, 4, "Last Keystroke: ", SGR.BOLD);     //Вывод
            textGraphics.putString(5 + "Last Keystroke: ".length(), 4, keyStroke.toString());   //Тоже вывод + нажатая клавиша

            terminal.setCursorVisible(false);   //Курсор печати невидим
            terminal.flush();       //Отображение на терминале, отрисовка результата

            keyStroke = terminal.readInput();   //Обработка клавиши

        }
        terminal.close();   //Завершает работу



    }
}
