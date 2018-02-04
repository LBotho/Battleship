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

    public Computer() {
        boatsList.add(new Carrier(5,2));
//        boatsList.add(new Cruiser(4,2));
//        boatsList.add(new Destroyer(3,2));
//        boatsList.add(new Submarine(3,4));
//        boatsList.add(new Torpedo(2,5));
    }

    @Override
    public void placeBoats() {
        Direction direction;
        int max=10,min=1;
        int row,column;
        for (Boat boat : boatsList) {
            Boolean check = false;
            do {
                Random rand = new Random();
                row = rand.nextInt(max - min + 1) + min;
                column = rand.nextInt(max - min + 1) + min;
                direction = Direction.randomDirection();
                check = checkBoatPosition(row,column,direction,boat.getSize());
            } while (!check);
            //Init boat
            boat.setDirection(direction);
            boat.setPosition(new Case(row,column));
            defenseGrid.addBoat(boat);
        }
    }

    @Override
    public void moveBoat() {

    }

    @Override
    public List<Boat> getBoatsList() {
        return this.boatsList;
    }

    @Override
    public Case pickTarget() {
        Case targetChoice = new Case(0,0);
        return targetChoice;
    }

    @Override
    public int hit(Case target) {
        return 0;
    }

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

    public boolean lost(){
        return true;
    }

    public void noticeHit(Case target) {

    }
}
