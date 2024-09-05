package Battle;

import Player.Player;

public class ResultOfMotion
{

    public void DealDamage(Player player)    //Результат наносящий урон
    {
        player.setHealth(player.getHealth() - 10);
    }

    public void Heal(Player player)    //Результат хилящий
    {
        if(player.getHealth() > 90 && player.getHealth() < 100)
        {
            player.setHealth(100);
        }
        else if (player.getHealth() != 100)
        {
            player.setHealth(player.getHealth() + 10);
        }

    }
}
