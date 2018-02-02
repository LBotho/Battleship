package Game.Player;

import Game.Boats.*;
import Game.Case;
import Game.utils.Direction;
import Game.utils.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Human implements Player {
    private List<Boat> boatsList = new ArrayList<>();

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
        Direction direction;
        int posX,posY;
        defenseGrid.displayGrid();
        System.out.println("\nYou have 5 boats to place: 1 Carrier, 1 Torpedo, 1 Cruiser, 1 Submarine and 1 Destroyer.\nTo place a boat, you have to write a square where to place the stern of the boat and a direction for the boat.\nFormat example (case insensitive): B,6,EAST");
        for (Boat boat : boatsList) {
            Boolean check;
            System.out.println("Where do you want to place your "+boat.getName()+" (size="+boat.getSize()+") ?");
            do {
                choice = readChoice();
                posX = Integer.valueOf(choice.split(",")[1]);
                posY = Functions.charToIntPosition(choice.split(",")[0]);
                direction = Direction.valueOf(choice.split(",")[2].toUpperCase());
                check = checkBoatPosition(posX,posY,direction,boat.getSize());
                if (!check) System.out.println("Placement error. \nPlease try again.");
            } while (!check);
            //Init boat
            boat.setDirection(direction);
            boat.setPosition(new Case(posX,posY));
            defenseGrid.addBoat(boat);
            defenseGrid.displayGrid();
        }
    }

    private String readChoice() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        Pattern ptn = Pattern.compile("([A-J]{1}),([1-9]|10),(NORTH|WEST|SOUTH|EAST)", Pattern.CASE_INSENSITIVE);
        while (!ptn.matcher(input).matches()) {
            System.out.println("Format error. Example: B,10,WEST");
            System.out.println("Please try again.");
            reader = new Scanner(System.in);
            input = reader.nextLine();
        }
        return input;
    }

    private Boolean checkBoatPosition(int column, int row, Direction direction, int boatSize) {
        Boolean check = false;
        switch (direction) {
            case NORTH:

                if (row-boatSize >= 0) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                for (int i=0; i<boatSize;i++) {
                    System.out.println("Check : "+Integer.valueOf(row-i)+"/"+column);
                    System.out.println("Boat : "+Player.defenseGrid.getBoard()[row-i-1][column].getBoat());
                    if(Player.defenseGrid.getBoard()[row-i][column].getBoat() != null) {
                        check = false;
                        break;
                    }
                }
                break;
            case EAST:
                if (column+boatSize <= 11) check = true;
                break;
            case SOUTH:
                if (row+boatSize <= 11) check = true;
                break;
            case WEST:
                if (column-boatSize >= 0) check = true;
                break;
        }
        return check;
    }
}
