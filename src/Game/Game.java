package Game;

import Game.Player.Computer;
import Game.Player.Human;

public class Game {
    private Game.Player.Player player1, player2;
    private boolean player2Human;

    public Game() {
        player2Human = true;
        player1 = new Human();
        if (player2Human) player2 = new Human(); else player2=new Computer();
    }
}
