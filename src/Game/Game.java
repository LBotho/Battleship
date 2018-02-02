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
        System.out.println("##################################################");
        System.out.println("#            PLAYER 1 BOATS PLACEMENT            #");
        System.out.println("##################################################\n");
        player1.placeBoats();
        System.out.println("##################################################");
        System.out.println("#            PLAYER 2 BOATS PLACEMENT            #");
        System.out.println("##################################################\n");
        player2.placeBoats();
    }
}
