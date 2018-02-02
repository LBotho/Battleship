package Game.Player;

import Game.Boats.*;
import Game.Case;
import Game.Grid;
import Game.utils.Direction;
import Game.utils.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Human implements Player {
    private List<Boat> boatsList = new ArrayList<>();
    Grid attackGrid = new Grid();
    Grid defenseGrid = new Grid();

    public Human() {
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
        System.out.println("\nYou have 5 boats to place: 1 Carrier, 1 Torpedo, 1 Cruiser, 1 Submarine and 1 Destroyer.\nTo place a boat, you have to write a square where to place the stern of the boat and a direction for the boat.\nFormat example (case insensitive): B,6,EAST");
        for (Boat boat : boatsList) {
            Boolean check = false;
            System.out.println("Where do you want to place your "+boat.getName()+" (size="+boat.getSize()+") ?");
            do {
                choice = readChoice("([A-J]{1}),([1-9]|10),(NORTH|WEST|SOUTH|EAST)");
                posX = Integer.valueOf(choice.split(",")[1]);
                posY = Functions.charToIntPosition(choice.split(",")[0]);
                direction = Direction.valueOf(choice.split(",")[2].toUpperCase());
                check = checkBoatPosition(posX,posY,direction,boat.getSize());
                if (!check) System.out.println("Placement error.\nPlease try again.");
            } while (!check);
            //Init boat
            boat.setDirection(direction);
            boat.setPosition(new Case(posX,posY));
            defenseGrid.addBoat(boat);
            defenseGrid.displayGrid();
        }
        defenseGrid.displayGrid();

    }

    private String readChoice(String regex) {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        while (!ptn.matcher(input).matches()) {
            System.out.println("Format error.");
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
                    if(this.defenseGrid.getBoard()[row-i][column].getBoat() != null) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row-i][column].getBoat().getName()+".");
                        break;
                    }
                }
                break;
            case EAST:
                if (column+boatSize <= 11) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                for (int i=0; i<boatSize;i++) {
                    if(this.defenseGrid.getBoard()[row][column+i].getBoat() != null) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row][column+i].getBoat().getName()+".");
                        break;
                    }
                }
                break;
            case SOUTH:
                if (row+boatSize <= 11) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                for (int i=0; i<boatSize;i++) {
                    if(this.defenseGrid.getBoard()[row+i][column].getBoat() != null) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row+i][column].getBoat().getName()+".");
                        break;
                    }
                }
                break;
            case WEST:
                if (column-boatSize >= 0) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                for (int i=0; i<boatSize;i++) {
                    if(this.defenseGrid.getBoard()[row][column-i].getBoat() != null) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row][column-i].getBoat().getName()+".");
                        break;
                    }
                }
                break;
        }
        return check;
    }

    @Override
    public void moveBoat() {
        System.out.println("Do you want to move a boat (yes/no)");
        String choice = readChoice("(YES|NO)");
        if(choice.equalsIgnoreCase("yes")) {
            int i =0;

            for (Boat boat : boatsList) {
                System.out.println(i+". "+boat.getName());
                i++;
            }
            int nbOfBoat = i-1;

            System.out.println("Select the boat you want to move and the number of move \"Boat number,(NORTH|WEST|SOUTH|EAST),number of move\"");
            Boolean check = false;
            int nbBoatToMove;
            Direction dirToMove;
            int nbOfMove;
            Boat boatToMove;
            int newPosX = 0;
            int newPosY = 0;
            do {
                String choiceBoat = readChoice("[0-"+nbOfBoat+"],(NORTH|WEST|SOUTH|EAST),[1-2]");
                nbBoatToMove = Integer.parseInt(choiceBoat.split(",")[0]);
                dirToMove = Direction.valueOf(choiceBoat.split(",")[1].toUpperCase());
                nbOfMove = Integer.parseInt(choiceBoat.split(",")[2]);
                boatToMove = boatsList.get(nbBoatToMove);

                switch (dirToMove) {
                    case NORTH:
                        newPosX = boatToMove.getPosition().getPosX();
                        newPosY = boatToMove.getPosition().getPosY()-nbOfMove;
                        break;
                    case SOUTH:
                        newPosX = boatToMove.getPosition().getPosX();
                        newPosY = boatToMove.getPosition().getPosY()+nbOfMove;

                        break;
                    case WEST:
                        newPosX = boatToMove.getPosition().getPosX()-nbOfMove;
                        newPosY = boatToMove.getPosition().getPosY();
                        break;
                    case EAST:
                        newPosX = boatToMove.getPosition().getPosX()+nbOfMove;
                        newPosY = boatToMove.getPosition().getPosY();
                        break;
                }
                check = checkBoatPosition(newPosX,newPosY,boatToMove.getDirection(),boatToMove.getSize());
                if (!check) System.out.println("Placement error.\nPlease try again.");
            } while (!check);

            defenseGrid.removeBoat(boatToMove);
            boatToMove.setPosition(new Case(newPosX, newPosY));
            defenseGrid.addBoat(boatToMove);

        } else if (choice.equalsIgnoreCase("no")) {
            return;
        }

        ;
    }
}
