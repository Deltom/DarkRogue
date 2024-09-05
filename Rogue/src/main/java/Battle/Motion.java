package Battle;

import Player.Player;

import java.lang.reflect.Array;
import java.util.*;

//При добавлении нового метода движений, добавить его в methodsNames и метод fillMethodsWithTags() и doMotion

public class Motion //Родительский класс движений
{
    Map<Integer, List<String>> methodsWithTags = new HashMap<>();   //Список тегов необходимых для метода
    Map<Integer, Integer> idOfMethods = new HashMap<>();   //Список для методов к которым может обратиться игрок и их айди
    List<String> methodsNames = List.of("Attack", "Heal", "Defend");  //Список всех действий

    Scanner scanner = new Scanner(System.in);

    /*String[] resultOfMotion = {"Fail", "Success", "Critical Fail", "Critical Success"};

    String[] tagsPlayerOne;
    String[] tagsPlayerTwo;*/

    public void fillMethodsWithTags()   //Добавление новых методов
    {
        methodsWithTags.put(1, new ArrayList<>(List.of("P1close", "P1stand", "P1sword", "P2close")));   //Attack
        methodsWithTags.put(2, new ArrayList<>(List.of("P1stand")));   // Heal
        methodsWithTags.put(3, new ArrayList<>(List.of("P1stand")));   // Heal
    }


    public void showMethods(int methodNumber)   //выводит доступные методы действий
    {
        System.out.println(methodsNames.get(methodNumber));
    }

  /*  public String outputResult(int resultNumber)    //Метод отправки результатов действий в класс результатов
    {
        return resultOfMotion[resultNumber];
    }

    //Получение тегов обоих игроков для определения доступных действий
    public void getTagsPlayerOne(String[] tagsPlayerOne) { this.tagsPlayerOne = tagsPlayerOne; }

    public void getTagsPlayerTwo(String[] tagsPlayerTwo) { this.tagsPlayerTwo = tagsPlayerTwo; }

    public String[] setTagsPlayerOne() { return tagsPlayerOne; }
    public String[] setTagsPlayerTwo() { return tagsPlayerTwo; }*/

   /* public void movesMethod()   //метод движения
    {
        String[] tagsP1 = {"stand"};
    }*/

    public boolean getMethodsByTags(Player playerOne, Player playerTwo, int numberMethod) //Хранилище тегов методов действий. Так же тут есть функция выбора подходящего тега
    {
        // Словарь с именами методов и их тегами


        // Добавляем методы и их теги


        //Отбор
        String[] p1Tags = playerOne.getTags();
        String[] p2Tags = playerTwo.getTags();

        List<String> needsTagsList = methodsWithTags.get(numberMethod);
        //ArrayList<String> needsTags = new ArrayList<>(need);
        boolean exist = true;

        String[] allNeedTags = needsTagsList.toArray(new String[0]);

        //Хня для подсчёта тегов у игроков и пихание по массивам
        int countOfTagsP1 = 0, countOfTagsP2 = -1;
        for (int i = 0; i < allNeedTags.length; i++)
        {
            if(allNeedTags[i].charAt(1) == '2')
            {
                break;
            }
            countOfTagsP1++;
        }

        String[] p1NeedTags = new String[countOfTagsP1];
        String[] p2NeedTags = new String[Math.abs(countOfTagsP1 - allNeedTags.length)];

        for (int i = 0; i < allNeedTags.length; i++)
        {
            countOfTagsP2++;

            if (i < countOfTagsP1)
            {
                p1NeedTags[i] = allNeedTags[i].replace("P1", "");
            }
            else
            {
                p2NeedTags[i - countOfTagsP2] = allNeedTags[i].replace("P2", "");
            }
        }


        for (int i = 0; i < p1NeedTags.length; i++) //Содержит ли игрок 1, нужные для этого действия теги
        {
            if (!Arrays.asList(p1Tags).contains(p1NeedTags[i]))
            {
                exist = false;
            }
        }

        for (int i = 0; i < p2NeedTags.length; i++) //Содержит ли игрок 2, нужные для этого действия теги
        {
            if (!Arrays.asList(p2Tags).contains(p2NeedTags[i]))
            {
                exist = false;
            }
        }
        return exist;
    }

    public void selectMethodsShow(Player playerOne, Player playerTwo) // Вывод возможных к использованию методов действий
    {
        fillMethodsWithTags();
        int count = 0;
        for (int i = 1; i < methodsNames.size() + 1; i++)
        {
            if(getMethodsByTags(playerOne,playerTwo,i))
            {
                showMethods(i - 1);
                idOfMethods.put(count, i - 1);
                count++;
            }
        }
    }



}

class LowMotion extends Motion //Класс движений с низкой инициативы
{

}

class HighMotion extends Motion //Класс движений с высокой инициативы
{

}

class SameMotion extends Motion //Класс движений с одинаковой инициативой
{
    ResultOfMotion resultOfMotion = new ResultOfMotion();


    public void playerSelectMotion(Player playerOne, Player playerTwo)
    {


        System.out.println("Вам доступно:");
        selectMethodsShow(playerOne, playerTwo);
        for (int i = 0; i < idOfMethods.size(); i++) {
            System.out.println(idOfMethods.get(i));
        }
        System.out.println("Ваше действие:");
        doMotion(playerOne, playerTwo, idOfMethods.get(scanner.nextInt()));
    }

    public void doMotion(Player playerOne, Player playerTwo, int motionID)   // Выполнение действия
    {
        System.out.println("ID mothion: " + motionID);
        switch (motionID) {
            case 0:
                Attack(playerTwo);
                break;
            case 1:
                Heal(playerOne);
                break;
        }
    }

    public void Attack(Player enemy)    // метод Атака
    {
        resultOfMotion.DealDamage(enemy);
    }
    public void Heal(Player you)    //Метод лечения
    {
        resultOfMotion.Heal(you);
    }
}
/*
Кроме методов самих движений, что нужно классу:
    метод определения нужных движений
    результат действия(успешно, неуспешно) В будущем будет(критически успешно, критически неудачно) +
    метод отправки результата действия в класс результатов.                                         +
    метод получения данных игроков(положение, и другая инфа для дадатия тегов)                      +
    метод отправки данных в классы результатов
    метод приближения и отдаления(чуток позже)
    метод смены позы(чуток позже)
    Отправка изменённых тегов игроку                                                                +
    метод составления и вывод возможных к применению действий
        Доп для низкого:
            метод получения и обработки действий высокой инициативы

        Доп для высокого:
            метод отправки действий для низкой инициативы
Через один класс родителя да

Реализация движений.
Будет несколько классов движенений. Низкая, одинаковая и высокая(инициатива)
В классе движений будут методы всех движений(соответсвующей иниц.)
Метод содержит в себе теги(теги нескольких видов: от вас и врага. Типа попытаться прикончить лежачего,
пока противник стоит от тебя в 10 метрах идея так себе)
Каждый класс будет содержать переменные результатов движений чтоб можно было сравнивать
Классы низкой и высокой взаимосвязанны(типа у одного игрока низкая, а у другого высокая), поэтому
Переменные и теги, будут чутка разница

 */