package Game.Player;

import Game.utils.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Human implements Player {
    @Override
    public void placeBoats() {
        defenseGrid.displayGrid();
        System.out.println("You have 5 boats to place: 1 Carrier, 1 Battleship, 1 Cruiser, 1 Submarine and 1 Destroyer.");
        System.out.println("To place a boat, you have to write a square where to place the stern of the boat and a direction for the boat.");
        System.out.println("Format example: B,6,EAST");
        System.out.println("Where do you want to place your Carrier (size="+carrier.getSize()+") ?");
        Boolean check = checkBoatPosition(readChoice());
        while (!check) {
            System.out.println("Placement error.");
            System.out.println("Please try again.");
            check = checkBoatPosition(readChoice());
        }

        //defenseGrid.displayGrid();
//        System.out.println("Where do you want to place your Battleship (size="+battleship.getSize()+") ?");
        //defenseGrid.displayGrid();
//        System.out.println("Where do you want to place your Cruiser (size="+cruiser.getSize()+") ?");
        //defenseGrid.displayGrid();
//        System.out.println("Where do you want to place your Submarine (size="+submarine.getSize()+") ?");
        //defenseGrid.displayGrid();
//        System.out.println("Where do you want to place your Destroyer (size="+destroyer.getSize()+") ?");
    }

    private String readChoice() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        while (!input.matches("([A-J]{1}),([1-9]|10),(NORTH|WEST|SOUTH|EAST)")) {
            System.out.println("Format error. Example: B,10,WEST");
            System.out.println("Please try again.");
            reader = new Scanner(System.in);
            input = reader.nextLine();
        }
        return input;
    }

    private Boolean checkBoatPosition(String position) {
        Boolean check = false;
        String[] res = position.split(",");
        int row = res[0].charAt(0)-'A'+1;
        int column = Integer.parseInt(res[1]);
        Direction direction = Direction.valueOf(res[2]);

        switch (direction) {
            case NORTH:
                if (row-carrier.getSize()-1 > 0) check = true;
                break;
            case EAST:
                if (column+carrier.getSize()-1 < 11) check = true;
                break;
            case SOUTH:
                if (row+carrier.getSize()-1 < 11) check = true;
                break;

            case WEST:
                if (column-carrier.getSize()-1 > 0) check = true;
                break;
        }
        return check;
    }
}
