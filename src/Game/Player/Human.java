package Game.Player;

import Game.Boats.*;
import Game.Grid;
import Game.utils.Direction;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Human implements Player {
    List<Boat> boatsList = new ArrayList<>();

    public Human() {
        System.out.println("Constructeur human");
        boatsList.add(new Carrier());
        boatsList.add(new Battleship());
        boatsList.add(new Cruiser());
        boatsList.add(new Submarine());
        boatsList.add(new Destroyer());
    }


    @Override
    public void placeBoats() {
        defenseGrid.displayGrid();
        System.out.println("\nYou have 5 boats to place: 1 Carrier, 1 Battleship, 1 Cruiser, 1 Submarine and 1 Destroyer.");
        System.out.println("To place a boat, you have to write a square where to place the stern of the boat and a direction for the boat.");
        System.out.println("Format example: B,6,EAST");
        for (Boat boat : boatsList) {
            System.out.println("Where do you want to place your "+boat.getName()+" (size="+boat.getSize()+") ?");
            Boolean check = checkBoatPosition(readChoice(), boat.getSize());
            while (!check) {
                System.out.println("Placement error.");
                System.out.println("Please try again.");
                check = checkBoatPosition(readChoice(), boat.getSize());
            }
        }
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

    private Boolean checkBoatPosition(String position, int size) {
        Boolean check = false;
        String[] res = position.split(",");
        int row = res[0].charAt(0)-'A'+1;
        int column = Integer.parseInt(res[1]);
        Direction direction = Direction.valueOf(res[2]);

        switch (direction) {
            case NORTH:
                if (row-size-1 > 0) check = true;
                break;
            case EAST:
                if (column+size-1 < 11) check = true;
                break;
            case SOUTH:
                if (row+size-1 < 11) check = true;
                break;
            case WEST:
                if (column-size-1 > 0) check = true;
                break;
        }
        return check;
    }
}
