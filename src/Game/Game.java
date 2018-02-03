package Game;

import Game.Grid.Case;
import Game.Player.Computer;
import Game.Player.Human;
import Game.Player.Player;

import java.io.IOException;

public class Game {
    private Player player1, player2;

    public Game(Boolean player2Human) {
        player1 = new Human();
        if (player2Human) player2 = new Human(); else player2 = new Computer();
    }

    public void init () {
        System.out.println("##################################################");
        System.out.println("#            PLAYER 1 BOATS PLACEMENT            #");
        System.out.println("##################################################");
        player1.placeBoats();
        System.out.println("\n##################################################");
        System.out.println("#            PLAYER 2 BOATS PLACEMENT            #");
        System.out.println("##################################################");
        player2.placeBoats();
    }

    public void play() {
        int player1Shot=0,player2Shot=0;
        while (!player1.getBoatsList().isEmpty() || !player2.getBoatsList().isEmpty()) {
            System.out.println("\n##################################################");
            System.out.println("#                    PLAYER 1                    #");
            System.out.println("##################################################\n");
//            if (player2Shot == 0) {
//                player1.askMove();
//            }
            Case target1 = player1.pickTarget();
            player1Shot = player2.hit(target1);


            System.out.println("\n##################################################");
            System.out.println("#                    PLAYER 2                    #");
            System.out.println("##################################################\n");
//            if (!player1Shot == 0) {
//                player2.askMove();
//            }
            Case target2 = player2.pickTarget();
            player2Shot = player1.hit(target2);

        }
    }
}
