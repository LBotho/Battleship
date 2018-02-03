package Game.Player;

import Game.Boats.*;
import Game.Grid.Case;
import Game.Grid.Grid;
import Game.utils.Direction;
import Game.utils.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Human implements Player {
    private Grid attackGrid = new Grid();
    private Grid defenseGrid = new Grid();
    private List<Boat> boatsList = new ArrayList<>();

    public Human() {
        boatsList.add(new Carrier());
//        boatsList.add(new Cruiser());
//        boatsList.add(new Destroyer());
//        boatsList.add(new Submarine());
//        boatsList.add(new Torpedo());
    }

    @Override
    public void placeBoats() {
        String choice;
        Direction direction;
        int row,column;
        System.out.println("\nYou have 5 boats to place: 1 Carrier, 1 Torpedo, 1 Cruiser, 1 Submarine and 1 Destroyer.\nTo place a boat, you have to write a square where to place the stern of the boat and a direction for the boat.\nFormat example (case insensitive): B,6,EAST");
        for (Boat boat : boatsList) {
            Boolean check = false;
            System.out.println("Where do you want to place your "+boat.getName()+" (size="+boat.getSize()+") ?");
            do {
                choice = readChoice("([A-J]{1}),([1-9]|10),(NORTH|WEST|SOUTH|EAST)");
                row = Functions.charToIntPosition(choice.split(",")[0]);
                column = Integer.valueOf(choice.split(",")[1]);
                direction = Direction.valueOf(choice.split(",")[2].toUpperCase());
                check = checkBoatPosition(row,column,direction,boat.getSize());
                if (!check) System.out.println("Placement error.\nPlease try again.");
            } while (!check);
            //Init boat
            boat.setDirection(direction);
            boat.setPosition(new Case(row,column));
            defenseGrid.addBoat(boat);
            defenseGrid.displayGrid();
        }
    }

    @Override
    public List<Boat> getBoatsList() {
        return this.boatsList;
    }

    @Override
    public Case pickTarget() {
        List<Case> targets = getTargets();
        attackGrid.clear();
        for (Case target: targets) { attackGrid.addTarget(target); }
        showBothGrid();
        System.out.println("What target do you want to fire ?");
        Case targetChoice = null;
        do {
            String choice = readChoice("[A-J]{1},([1-9]|10)");
            int row = Functions.charToIntPosition(choice.split(",")[0]);
            int column = Integer.valueOf(choice.split(",")[1]);
            targetChoice = new Case(row,column);
            if (!Functions.containsTarget(targets,targetChoice)) System.out.println("Error: the target you selected is not valid.\nPlease try again.");
        } while (!Functions.containsTarget(targets,targetChoice));
        return targetChoice;
    }

    @Override
    public int hit(Case target) {
        Boat boat = defenseGrid.getBoard()[target.getRow()][target.getColumn()].getBoat();
        if (boat != null) {
            boat.damage();
            if (boat.getHealth() == 0) {
                defenseGrid.removeBoat(boat);
                System.out.println("Well played, you sank your opponent's "+boat.getName()+"!");
                return 2;
            }
            System.out.println("Nice, you hit one of your opponent's boats!");
            return 1;
        }
        System.out.println("You missed!");
        return 0;
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

    private Boolean checkBoatPosition(int row, int column, Direction direction, int boatSize) {
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

    private List<Case> getTargets() {
        List<Case> targets = new ArrayList<>();
        for (int row = 1; row < defenseGrid.getSize(); row++) {
            for (int column = 1; column < defenseGrid.getSize(); column++) {
                if (defenseGrid.getBoard()[row][column].getBoat() != null) {
                    int range = defenseGrid.getBoard()[row][column].getBoat().getRange();
                    targets.add(defenseGrid.getBoard()[row][column]);
                    for(int k=1;k<=range; k++) {
                        //We check every time if the potential target isn't out of the grid
                        //We don't check duplicate because it won't matter at the end
                        //north
                        if(row-k > 0) targets.add(defenseGrid.getBoard()[row-k][column]);
                        //east
                        if(column+k <= 10) targets.add(defenseGrid.getBoard()[row][column+k]);
                        //south
                        if(row+k <= 10) targets.add(defenseGrid.getBoard()[row+k][column]);
                        //west
                        if(column-k > 0) targets.add(defenseGrid.getBoard()[row][column-k]);
                    }
                }
            }
        }
        return targets;
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
            int newRow = 0;
            int newColumn = 0;

            do {
                String choiceBoat = readChoice("[0-"+nbOfBoat+"],(NORTH|WEST|SOUTH|EAST),[1-2]");
                nbBoatToMove = Integer.parseInt(choiceBoat.split(",")[0]);
                dirToMove = Direction.valueOf(choiceBoat.split(",")[1].toUpperCase());
                nbOfMove = Integer.parseInt(choiceBoat.split(",")[2]);
                boatToMove = boatsList.get(nbBoatToMove);

                switch (dirToMove) {
                    case NORTH:
                        newRow = boatToMove.getPosition().getRow()-nbOfMove;
                        newColumn = boatToMove.getPosition().getColumn();
                        break;
                    case SOUTH:
                        newRow = boatToMove.getPosition().getRow()+nbOfMove;
                        newColumn = boatToMove.getPosition().getColumn();

                        break;
                    case WEST:
                        newRow = boatToMove.getPosition().getRow();
                        newColumn = boatToMove.getPosition().getColumn()-nbOfMove;
                        break;
                    case EAST:
                        newRow = boatToMove.getPosition().getRow();
                        newColumn = boatToMove.getPosition().getColumn()+nbOfMove;
                        break;
                }
                check = checkBoatPosition(newRow,newColumn,boatToMove.getDirection(),boatToMove.getSize());
                if (!check) System.out.println("Placement error.\nPlease try again.");
            } while (!check);

            defenseGrid.removeBoat(boatToMove);
            boatToMove.setPosition(new Case(newRow, newColumn));
            defenseGrid.addBoat(boatToMove);

        } else if (choice.equalsIgnoreCase("no")) {
            return;
        }
    }

    public void showBothGrid() {
        //Same for both grid
        System.out.println("           Defense grid                        Attack grid");
        int gridSize = attackGrid.getSize();
        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                System.out.print(column == 10 ? "["+ this.defenseGrid.getBoard()[row][column].getIllustration() +"]" : "["+ this.defenseGrid.getBoard()[row][column].getIllustration() +"]");

            }
            System.out.print(row == 0 ? " " : "  ");
            for (int column = 0; column < gridSize; column++) {
                System.out.print(column == 10 ? "["+ this.attackGrid.getBoard()[row][column].getIllustration() +"]" : "["+ this.attackGrid.getBoard()[row][column].getIllustration() +"]");
            }
            System.out.println("");
        }
    }

    public boolean lost() {
        int totalHealth = 0;
        for(Boat boat : boatsList) {
            totalHealth+=boat.getHealth();
        }
        return (totalHealth == 0 ? true : false);
    }
}
