package Game;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Main class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Main {
    /**
     * Main function
     *
     * @param args Main args
     */
    public static void main(String[] args) {
        Boolean player2Human = player2Choice();
        Game game = new Game(player2Human);
        game.init();
        game.play();
    }

    /**
     * Let the user choose which game mode he wants to play: against another human or a computer.
     *
     * @return True if the user wants to play against a human or False if the user wants to play against the computer.
     */
    private static Boolean player2Choice () {
        System.out.println("#####################################################################");
        System.out.println("#                            BATTLESHIP+                            #");
        System.out.println("#####################################################################");
        System.out.println("#                       CHOOSE YOUR GAME MODE                       #");
        System.out.println("#####################################################################");
        System.out.println("#  "+String.format("%-65s", "1: Play against a human")+"#");
        System.out.println("#  "+String.format("%-65s", "2: Play against a computer")+"#");
        System.out.println("#  "+String.format("%-65s", "3: Quit")+"#");
        System.out.println("#####################################################################");
        System.out.println("Your choice :");

        Boolean player2Human = false;
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        Pattern ptn = Pattern.compile("[1-3]", Pattern.CASE_INSENSITIVE);
        while (!ptn.matcher(input).matches()) {
            System.out.println("You have to choose an option and write '1', '2' or '3'.\nPlease Try again.");
            reader = new Scanner(System.in);
            input = reader.nextLine();
        }

        switch (input) {
            case "1":
                player2Human = true;
                break;
            case "2":
                player2Human = false;
                break;
            case "3":
                System.exit(0);
                break;
        }
        return player2Human;
    }
}