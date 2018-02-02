package Game.Player;

import Game.Boats.*;
import Game.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import Game.utils.Functions;
import com.sun.javafx.geom.Vec2d;

import java.util.Scanner;

public class Human implements Player {
    List<Boat> boatsList = new ArrayList<>();

    public Human() {
        System.out.println("Constructeur human");
        boatsList.add(new Carrier());
        boatsList.add(new Cruiser());
        boatsList.add(new Destroyer());
        boatsList.add(new Submarine());
        boatsList.add(new Torpedo());
    }


    @Override
    public void placeBoats() {
        String choice;
        defenseGrid.displayGrid();
        System.out.println("\nYou have 5 boats to place: 1 Carrier, 1 Torpedo, 1 Cruiser, 1 Submarine and 1 Destroyer.");
        System.out.println("To place a boat, you have to write a square where to place the stern of the boat and a direction for the boat.");
        System.out.println("Format example: B,6,EAST");
        for (Boat boat : boatsList) {
            System.out.println("Where do you want to place your "+boat.getName()+" (size="+boat.getSize()+") ?");
            choice = readChoice();
            Boolean check = checkBoatPosition(choice, boat.getSize());
            while (!check) {
                System.out.println("Placement error.");
                System.out.println("Please try again.");
                choice = readChoice();
                check = checkBoatPosition(choice, boat.getSize());
            }
            //Si on arrive ici c'est que le check est bon donc on init le bateau
            Direction dir = Direction.valueOf(choice.split(",")[2]);
            int posY = Functions.charToIntPosition(choice.split(",")[0]);
            int posX = Integer.valueOf(choice.split(",")[1]);
            boat.setDirection(dir);
            boat.setPosition(new Vec2d(posX,posY));
            defenseGrid.addBoat(boat);
            defenseGrid.displayGrid();
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
        int row = Functions.charToIntPosition(res[0]);
        int column = Integer.valueOf(res[1]);
        Direction direction = Direction.valueOf(res[2]);

        switch (direction) {
            case NORTH:
                if (row-size >= 0) check = true;
                break;
            case EAST:
                if (column+size <= 11) check = true;
                break;
            case SOUTH:
                if (row+size <= 11) check = true;
                break;
            case WEST:
                if (column-size >= 0) check = true;
                break;
        }
        return check;
    }
}
