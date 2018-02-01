package Game;

import Game.Player.Computer;
import Game.Player.Human;
import Game.Player.Player;

public class Game {
    private Player player1, player2;

    public Game(Boolean player2Human) {
        player1 = new Human();
        if (player2Human) player2 = new Human(); else player2 = new Computer();
    }

    public void init () {
        player1.placeBoats();
        //player2.placeBoats();
    }
}
