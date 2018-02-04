package Game;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    /**
     * Main function
     */
    public static void main(String[] args) {
        Boolean player2Human = player2Choice();
        Game game = new Game(player2Human);
        // Game initialization
        game.init();
        game.play();
    }

    /**
     * Let the user choose which game mode he wants to play: against another human or a computer.
     *
     * @return boolean player2Human
     */
    private static Boolean player2Choice () {
        System.out.println("#################################################");
        System.out.println("#                  BATTLESHIP                   #");
        System.out.println("#################################################");
        System.out.println("#             CHOOSE YOUR GAME MODE             #");
        System.out.println("#################################################");
        System.out.println("#  1: Play against a human                      #");
        System.out.println("#  2: Play against a computer                   #");
        System.out.println("#  3: Quit                                      #");
        System.out.println("#################################################");
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