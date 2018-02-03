package Game;

import Game.Grid.Case;
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
        System.out.println("##################################################");
        player1.placeBoats();
//        System.out.println("\n##################################################");
//        System.out.println("#            PLAYER 2 BOATS PLACEMENT            #");
//        System.out.println("##################################################");
//        player2.placeBoats();

    }

    public void play() {
//        boolean player1Fail=false,player2Fail=false;
        while (!player1.getBoatsList().isEmpty() || !player2.getBoatsList().isEmpty()) {
            System.out.println("\n##################################################");
            System.out.println("#                    PLAYER 1                    #");
            System.out.println("##################################################\n");
//            if (player2Fail) {
//                player1.askMove();
//            }
            Case target1 = player1.pickTarget();


            System.out.println("\n##################################################");
            System.out.println("#                    PLAYER 2                    #");
            System.out.println("##################################################\n");
//            if (player1Fail) {
//                player1.askMove();
//            }
            player2.pickTarget();
            Case target2 = player2.pickTarget();

        }
    }
}
