package Game;

import Game.Grid.Square;
import Game.Player.Computer;
import Game.Player.Human;
import Game.Player.Player;

/**
 * Game class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Game {
    private Player player1, player2;

    /**
     * Creates 2 players: player 1 is human (the user) and player 2 is human or computer according to the user's choice.
     *
     * @param player2Human The user game mode choice (true: against a human, false: against the computer).
     */
    public Game(Boolean player2Human) {
        player1 = new Human();
        if (player2Human) player2 = new Human(); else player2 = new Computer();
    }

    /**
     * Game initialization function (boats placement for both players).
     */
    public void init () {
        System.out.println("#####################################################################");
        System.out.println("#                      PLAYER 1 BOATS PLACEMENT                     #");
        System.out.println("#####################################################################");
        player1.placeBoats();
        System.out.println("#####################################################################");
        System.out.println("#                      PLAYER 2 BOATS PLACEMENT                     #");
        System.out.println("#####################################################################");
        player2.placeBoats();
    }

    /**
     * Game loop function. Each rounds, both players can move a boat (if none of the boats were hit the previous round)
     * then pick a target to fire. The game ends when 1 of the 2 players no longer has boats alive.
     */
    public void play() {
        int player1Shot=0,player2Shot=0;
        int round = 1;
        while (true) {
            //PLAYER 1
            System.out.println("\n#####################################################################");
            System.out.println("#                          ROUND "+round+": PLAYER 1                        #");
            System.out.println("#####################################################################\n");

            if (player2Shot == 0 && round > 1) player1.moveBoat();
            Square target1 = player1.pickTarget();
            player1Shot = player2.hit(target1);
            player1.noticeHit(target1,player1Shot);

            if(player2.lost()) {
                System.out.println("#####################################################################");
                System.out.println("#                            PLAYER 1 WON                           #");
                System.out.println("#####################################################################\n");
                break;
            }

            //PLAYER 2
            System.out.println("\n#####################################################################");
            System.out.println("#                          ROUND "+round+": PLAYER 2                        #");
            System.out.println("#####################################################################\n");
            if (player1Shot == 0 && round > 1) player2.moveBoat();

            Square target2 = player2.pickTarget();
            player2Shot = player1.hit(target2);
            player2.noticeHit(target2,player2Shot);

            if(player1.lost()){
                System.out.println("#####################################################################");
                System.out.println("#                            PLAYER 2 WON                           #");
                System.out.println("#####################################################################\n");
                break;
            }

            round++;
        }
    }
}
