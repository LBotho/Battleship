package Game.Player;

import Game.Boats.*;
import Game.Grid.Square;
import Game.Grid.Grid;
import Game.utils.Direction;
import Game.utils.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Human class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Human implements Player {
    private Grid attackGrid = new Grid();
    private Grid defenseGrid = new Grid();
    private List<Boat> boatsList = new ArrayList<>();

    public Human() {
        boatsList.add(new Carrier(5,2));
//        boatsList.add(new Cruiser(4,2));
//        boatsList.add(new Destroyer(3,2));
//        boatsList.add(new Submarine(3,4));
//        boatsList.add(new Torpedo(2,5));
    }

    /**
     * Get the player boats list.
     *
     * @return The list of the player's boats
     */
    @Override
    public List<Boat> getBoatsList() {
        return this.boatsList;
    }

    /**
     * Boat placement function. Ask the user to write a row (Ex: D), a column (Ex: 2) and a direction (North, East,
     * South or West). Then check if the placement is valid.
     */
    @Override
    public void placeBoats() {
        String choice;
        Direction direction;
        int row,column;
        System.out.println("\nYou have 5 boats to place: 1 Carrier, 1 Torpedo, 1 Cruiser, 1 Submarine and 1 Destroyer.\nTo place a boat, you have to write a square where to place the stern of the boat and a direction for the boat.\nFormat example (case insensitive): B,6,EAST");
        for (Boat boat : boatsList) {
            Boolean check;
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
            boat.setPosition(new Square(row,column));
            defenseGrid.addBoat(boat);
            defenseGrid.displayGrid();
        }
    }

    /**
     * Check if the boat position is valid and if there's no overlapse with other boats.
     *
     * @param row The row the user chose to place the boat start Square.
     * @param column The row the user chose to place the boat start Square.
     * @param direction The direction of the boat.
     * @param boatSize The size of the boat.
     *
     * @return True if the boat placement is valid and false if not.
     */
    @Override
    public boolean checkBoatPosition(int row, int column, Direction direction, int boatSize) {
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
                System.out.println("WEST");

                System.out.println(column);
                System.out.println(boatSize);


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

    /**
     *
     * @param row The row
     * @param column The column
     * @param boatToMove The boat to move.
     *
     * @return True if the move if valid, false if not.
     */
    @Override
    public boolean checkMoveBoat(int row, int column, Boat boatToMove, Direction dirToMove) {
        int boatSize  = boatToMove.getSize();
        Direction boatDir = boatToMove.getDirection();
        Boolean check = true;
        switch (dirToMove) {
            case NORTH:
                if (row < 1) {
                    check = false;
                    break;
                }
                if(boatDir == Direction.NORTH && row-boatSize < 1) {
                    check = false;
                    break;
                }

                break;

            case EAST:
                if (column > 10) {
                    check = false;
                    break;
                }
                if(boatDir == Direction.EAST && column+boatSize > 11) {
                    check = false;
                    break;
                }

                break;
            case SOUTH:
                if (row > 10) {
                    check = false;
                    break;
                }
                if(boatDir == Direction.SOUTH && row+boatSize > 11) {
                    check = false;
                    break;
                }



                break;
            case WEST:
                if (column < 1) {
                    check = false;
                    break;
                }
                if(boatDir == Direction.WEST && column-boatSize < 1) {
                    check = false;
                    break;
                }
                break;
        }
        if(check != false) {
            switch (boatDir) {
                case NORTH:
                    if (row - boatSize >= 0) {

                    } else {
                        check = false;
                        break;
                    }
                    for (int i = 0; i < boatSize; i++) {
                        if (this.defenseGrid.getBoard()[row - i][column].getBoat() != null && this.defenseGrid.getBoard()[row - i][column].getBoat() != boatToMove) {
                            check = false;
                            System.out.println("There is a boat overlapse with your " + this.defenseGrid.getBoard()[row - i][column].getBoat().getName() + ".");
                            break;
                        }
                    }
                    break;
                case EAST:
                    if (column + boatSize <= 11) {

                    } else {
                        check = false;
                        break;
                    }
                    for (int i = 0; i < boatSize; i++) {
                        if (this.defenseGrid.getBoard()[row][column + i].getBoat() != null && this.defenseGrid.getBoard()[row][column + i].getBoat() != boatToMove) {
                            check = false;
                            System.out.println("There is a boat overlapse with your " + this.defenseGrid.getBoard()[row][column + i].getBoat().getName() + ".");
                            break;
                        }
                    }
                    break;
                case SOUTH:
                    if (row + boatSize <= 11) {

                    } else {
                        check = false;
                        break;
                    }

                    for (int i = 0; i < boatSize; i++) {
                        if (this.defenseGrid.getBoard()[row + i][column].getBoat() != null && this.defenseGrid.getBoard()[row + i][column].getBoat() != boatToMove) {
                            check = false;
                            System.out.println("There is a boat overlapse with your " + this.defenseGrid.getBoard()[row + i][column].getBoat().getName() + ".");
                            break;
                        }
                    }
                    break;
                case WEST:
                    if (column - boatSize > 0) {

                    } else {
                        check = false;
                        break;
                    }
                    for (int i = 0; i < boatSize; i++) {
                        if (this.defenseGrid.getBoard()[row][column - i].getBoat() != null && this.defenseGrid.getBoard()[row][column - i].getBoat() != boatToMove) {
                            check = false;
                            System.out.println("There is a boat overlapse with your " + this.defenseGrid.getBoard()[row][column - i].getBoat().getName() + ".");
                            break;
                        }
                    }
                    break;
            }
        }


        return check;
    }

    /**
     * Ask the user to choose a target.
     *
     * @return The target the user chose.
     */
    @Override
    public Square pickTarget() {
        List<Square> targets = getTargets();
        attackGrid.clearPreviousTarget();
        for (Square target: targets) { attackGrid.addTarget(target); }
        showBothGrid();
        System.out.println("What target do you want to fire ?");
        Square targetChoice;
        do {
            String choice = readChoice("[A-J]{1},([1-9]|10)");
            int row = Functions.charToIntPosition(choice.split(",")[0]);
            int column = Integer.valueOf(choice.split(",")[1]);
            targetChoice = new Square(row,column);
            if (!Functions.containsTarget(targets,targetChoice)) System.out.println("Error: the target you selected is not valid.\nPlease try again.");
        } while (!Functions.containsTarget(targets,targetChoice));
        return targetChoice;
    }

    /**
     *
     * @param target The target
     * @return 0 if 'miss', 1 if 'hit' or 2 if 'sink'
     */
    @Override
    public int hit(Square target) {
        Boat boat = defenseGrid.getBoard()[target.getRow()][target.getColumn()].getBoat();
        Boolean alreadyHit = defenseGrid.getBoard()[target.getRow()][target.getColumn()].isHasBeenHit();
        if (boat != null && !alreadyHit) {
            boat.damage();
            defenseGrid.getBoard()[target.getRow()][target.getColumn()].setHasBeenHit(true);
            defenseGrid.getBoard()[target.getRow()][target.getColumn()].setSymbol("#");
            if (boat.getHealth() == 0) {
                defenseGrid.removeBoat(boat);
                System.out.println(boat.getName()+" sank!");
                return 2;
            }
            System.out.println("Hit!");
            return 1;
        }
        System.out.println("Miss!");
        return 0;
    }

    /**
     * Move boat function.
     */
    @Override
    public void moveBoat() {
        System.out.println("Do you want to move a boat (yes/no)");
        String choice = readChoice("(YES|NO)");
        if(choice.equalsIgnoreCase("yes")) {
            showBothGrid();
            int i =0;

            for (Boat boat : boatsList) {
                System.out.println(i+". "+boat.getName());
                i++;
            }
            int nbOfBoat = i-1;

            System.out.println("Select the boat you want to move and the number of move \"Boat number,(NORTH|WEST|SOUTH|EAST),number of move\"");

            Boolean check;
            int nbBoatToMove;
            Direction dirToMove;
            int nbOfMove;
            Boat boatToMove;
            int newRow = 0;
            int newColumn = 0;
            List<Square> removedBoat;
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

                check = checkMoveBoat(newRow,newColumn,boatToMove, dirToMove);
                if (!check) System.out.println("Placement error.\nPlease try again.");
            } while (!check);
            removedBoat = defenseGrid.removeBoat(boatToMove);
            boatToMove.setPosition(new Square(newRow, newColumn));
            defenseGrid.moveBoat(removedBoat, dirToMove, nbOfMove);

        }
    }

    /**
     * Check if the game is over.
     *
     * @return True if the user lost. False if not
     */
    @Override
    public boolean lost() {
        int totalHealth = 0;
        for(Boat boat : boatsList) {
            totalHealth+=boat.getHealth();
        }
        return (totalHealth == 0);
    }

    /**
     * Print on the attack grid the Square the user hit.
     *
     * @param target The target.
     */
    @Override
    public void noticeHit(Square target) {
        this.attackGrid.getBoard()[target.getRow()][target.getColumn()].setSymbol("#");
    }

    /**
     * Read a user input.
     *
     * @param regex The regular expression which control the user input.
     *
     * @return The valid input the user wrote.
     */
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

    /**
     * Get all the possible shots.
     *
     * @return The list of the valid targets.
     */
    private List<Square> getTargets() {
        List<Square> targets = new ArrayList<>();
        for (int row = 1; row < defenseGrid.getSize(); row++) {
            for (int column = 1; column < defenseGrid.getSize(); column++) {
                if (defenseGrid.getBoard()[row][column].getBoat() != null) {
                    int range = defenseGrid.getBoard()[row][column].getBoat().getRange();
                    targets.add(defenseGrid.getBoard()[row][column]);
                    for(int k=1;k<=range; k++) {
                        //We check every time if the potential target isn't out of the grid
                        //We don't check duplicate because it won't matter at the end
                        //North
                        if(row-k > 0) targets.add(defenseGrid.getBoard()[row-k][column]);
                        //East
                        if(column+k <= 10) targets.add(defenseGrid.getBoard()[row][column+k]);
                        //South
                        if(row+k <= 10) targets.add(defenseGrid.getBoard()[row+k][column]);
                        //West
                        if(column-k > 0) targets.add(defenseGrid.getBoard()[row][column-k]);
                    }
                }
            }
        }
        return targets;
    }

    /**
     * Print both the defense grid and the attack grid side by side.
     */
    private void showBothGrid() {
        //Same for both grid
        System.out.println("           Defense grid                        Attack grid");
        int gridSize = attackGrid.getSize();
        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                System.out.print(column == 10 ? "["+ this.defenseGrid.getBoard()[row][column].getSymbol() +"]" : "["+ this.defenseGrid.getBoard()[row][column].getSymbol() +"]");

            }
            System.out.print(row == 0 ? " " : "  ");
            for (int column = 0; column < gridSize; column++) {
                System.out.print(column == 10 ? "["+ this.attackGrid.getBoard()[row][column].getSymbol() +"]" : "["+ this.attackGrid.getBoard()[row][column].getSymbol() +"]");
            }
            System.out.println("");
        }
    }
}
