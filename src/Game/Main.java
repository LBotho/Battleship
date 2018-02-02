package Game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Boolean player2Human = player2Choice();
        Game game = new Game(player2Human);
        game.init();
        game.play();
    }

    private static Boolean player2Choice () {
        //Scanner reader = new Scanner(System.in);
        Boolean player2Human = false;
        System.out.println("You want to play against a human player or a computer ? (human/computer)");
        //String str = reader.nextLine();
        String str = "human";

        if (str.equals("human")) {
            player2Human = true;
        } else if (str.equals("computer")) {
            player2Human = false;
        } else {
            System.out.println("Error, you have to write 'computer' or 'human'. Try again.");
            player2Choice();
        }
        return player2Human;
    }
}