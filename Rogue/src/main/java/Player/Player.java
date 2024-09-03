package Player;

import java.util.ArrayList;
import java.util.List;

public class Player
{

    public Player()
    {
        this.health = 100;
        this.stamina = 100;
    }
    //Статы и функции их изменения

    private int health, stamina;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    //Теги + стартовые теги
    List<String> startTags = List.of("close", "stand", "sword");
    ArrayList<String> tags = new ArrayList<>(startTags);

    //Метод для отправки тегов в другие классы
    public String[] getTags()
    {
        return tags.toArray(new String[0]);
    }

    //Метод добавления и замены тегов. Принимает: Первый тег - что заменить, Следующий за ним - на что.
    // Если не находит на что заменить, то просто добавляет тег в конец(Вместо первого тега, там подаётся none)
    public void setTags(String[] tagChange)
    {
        String[] oldTags = new String[tagChange.length / 2];
        String[] newTags = new String[tagChange.length / 2];

        for (int i = 0; i < tagChange.length; i++)  //заполнение тегами
        {
            int countOld = 0, countNew = 0;
            if(i % 2 == 0)
            {
                oldTags[countOld] = tagChange[i];
                countOld++;
            }
            else
            {
                newTags[countNew] = tagChange[i];
                countNew++;
            }
        }

        for (int i = 0; i < oldTags.length; i++)    //Замена старых, добавление новых
        {
            if(tags.contains(oldTags[i]))
            {
                tags.set(tags.indexOf(oldTags[i]), newTags[i]);
            }
            else
            {
                tags.add(newTags[i]);
            }
        }

    }

    //тестовый метод вывода тегов
    public void printTags()
    {
        for (int i = 0; i < tags.size(); i++)
        {
            System.out.print(tags.get(i) + " ");
        }
    }
}

/* В игроке должно быть:
    базовые статы+

    методы:
        метод принятия и изменения тегов
        создания и отправки тегов+
        изменения статов+

 */
