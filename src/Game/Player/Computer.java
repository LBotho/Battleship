package Game.Player;

import Game.Boats.*;
import Game.Grid.Case;
import Game.Grid.Grid;
import Game.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer implements Player {
    private Grid attackGrid = new Grid();
    private Grid defenseGrid = new Grid();
    private List<Boat> boatsList = new ArrayList<>();
    private Random randomGenerator = new Random();

    /**
     * Computer constructor
     */
    public Computer() {
        boatsList.add(new Carrier(5,2));
//        boatsList.add(new Cruiser(4,2));
//        boatsList.add(new Destroyer(3,2));
//        boatsList.add(new Submarine(3,4));
//        boatsList.add(new Torpedo(2,5));
    }

    /**
     * Get the player boats list.
     * @return The list of the player's boats
     */
    @Override
    public List<Boat> getBoatsList() {
        return this.boatsList;
    }

    /**
     * Boat placement function
     */
    @Override
    public void placeBoats() {
        Direction direction;
        int row,column;
        for (Boat boat : boatsList) {
            Boolean check;
            do {
                int min = 1,max = 10;
                row = randomGenerator.nextInt(max - min + 1) + min;
                column = randomGenerator.nextInt(max - min + 1) + min;
                direction = Direction.randomDirection();
                check = checkBoatPosition(row,column,direction,boat.getSize());
            } while (!check);
            //Init boat
            boat.setDirection(direction);
            boat.setPosition(new Case(row,column));
            defenseGrid.addBoat(boat);
        }
        System.out.println("\n##################################################");
        System.out.println("#          PLAYER 2 BOATS PLACEMENT OK           #");
        System.out.println("##################################################");
    }

    /**
     * Check if the boat position is valid and if there's no overlapse with other boats.
     * @param row The row the user chose to place the boat start Case.
     * @param column The row the user chose to place the boat start Case.
     * @param direction The direction of the boat.
     * @param boatSize The size of the boat.
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
                        break;
                    }
                }
                break;
        }
        return check;
    }

    /**
     * Computer's target choice.
     * @return The target the computer chose.
     */
    @Override
    public Case pickTarget() {
        List<Case> targets = getTargets();
        attackGrid.clearPreviousTarget();
        int index = randomGenerator.nextInt(targets.size());
        Case targetChoice = targets.get(index);
        for (Case target: targets) {
            attackGrid.addTarget(target);
        }
        return targetChoice;
    }

    @Override
    public int hit(Case target) {
        Boat boat = defenseGrid.getBoard()[target.getRow()][target.getColumn()].getBoat();
        Boolean alreadyHit = defenseGrid.getBoard()[target.getRow()][target.getColumn()].isHasBeenHit();
        if (boat != null && !alreadyHit) {
            boat.damage();
            defenseGrid.getBoard()[target.getRow()][target.getColumn()].setHasBeenHit(true);
            defenseGrid.getBoard()[target.getRow()][target.getColumn()].setIllustration("#");
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

    @Override
    public void moveBoat() {

    }

    @Override
    public boolean lost() {
        int totalHealth = 0;
        for(Boat boat : boatsList) {
            totalHealth+=boat.getHealth();
        }
        return (totalHealth == 0);
    }

    /**
     *
     * @param target The target.
     */
    @Override
    public void noticeHit(Case target) {
        this.attackGrid.getBoard()[target.getRow()][target.getColumn()].setIllustration("#");
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

    @Override
    public boolean checkMoveBoat(int row, int column, Boat boatToMove) {
        Direction direction = boatToMove.getDirection();
        int boatSize  = boatToMove.getSize();

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
                    if(this.defenseGrid.getBoard()[row-i][column].getBoat() != null && this.defenseGrid.getBoard()[row-i][column].getBoat() != boatToMove) {
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
                    if(this.defenseGrid.getBoard()[row][column+i].getBoat() != null && this.defenseGrid.getBoard()[row][column+i].getBoat() != boatToMove) {
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
                    if(this.defenseGrid.getBoard()[row+i][column].getBoat() != null && this.defenseGrid.getBoard()[row+i][column].getBoat() != boatToMove) {
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
                    if(this.defenseGrid.getBoard()[row][column-i].getBoat() != null && this.defenseGrid.getBoard()[row][column-i].getBoat() != boatToMove) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row][column-i].getBoat().getName()+".");
                        break;
                    }
                }
                break;
        }
        return check;
    }


}
