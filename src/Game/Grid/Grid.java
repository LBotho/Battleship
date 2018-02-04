package Game.Grid;

import Game.Boats.Boat;
import Game.utils.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Grid class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Grid {
    private final int size=11;
    private Case[][] board = new Case[size][size];

    /**
     * Grid constructor
     */
    public Grid() {
       for (int row = 0; row < size; row++) {
           for (int column = 0; column < size; column++) {
               if(row == 0 && column == 0) {
                   this.board[row][column] = new Case(row,column," ");
               } else if (row == 0) {
                   this.board[row][column] = new Case(row,column,Integer.toString(column));
               } else if (column == 0) {
                   int letterValue = row + 64;
                   this.board[row][column] = new Case(row,column,Character.toString((char) letterValue));
               } else {
                   this.board[row][column] = new Case(row,column," ");
               }
           }
       }
    }

    /**
     * Get size.
     * @return size
     */
    public int getSize() { return size; }

    /**
     * Get board.
     * @return board
     */
    public Case[][] getBoard() { return board; }

    public void displayGrid() {
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                System.out.print(row == 10 ? "["+ this.board[column][row].getIllustration() +"]" : "["+ this.board[column][row].getIllustration() +"]");
            }
            System.out.println("");
        }
    }

    /**
     * Add a boat on a grid.
     * @param boat The boat to add on the grid.
     */
    public void addBoat(Boat boat) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setBoat(boat);
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setIllustration("x");
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setHasBeenHit(false);
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setBoat(boat);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setIllustration("x");
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setHasBeenHit(false);
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setBoat(boat);
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setIllustration("x");
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setHasBeenHit(false);

                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setBoat(boat);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setIllustration("x");
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setHasBeenHit(false);

                }
                break;
        }
    }

    /**
     * Remove a boat from a grid.
     * @param boat The boat to remove from the grid.
     * @return removedBoat
     */
    public List<Case> removeBoat(Boat boat) {
        List<Case> removedBoat = new ArrayList<>();
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    removedBoat.add(new Case(boat.getPosition().getRow()-i, boat.getPosition().getColumn(), board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].getIllustration(), board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].isHasBeenHit(), boat));
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setBoat(null);
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setIllustration(" ");
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setHasBeenHit(false);

                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    removedBoat.add(new Case(boat.getPosition().getRow(), boat.getPosition().getColumn()+i, board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].getIllustration(), board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].isHasBeenHit(), boat));
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setBoat(null);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setIllustration(" ");
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setHasBeenHit(false);
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    removedBoat.add(new Case(boat.getPosition().getRow()+i, boat.getPosition().getColumn(), board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].getIllustration(), board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].isHasBeenHit(), boat));
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setBoat(null);
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setIllustration(" ");
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setHasBeenHit(false);

                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    removedBoat.add(new Case(boat.getPosition().getRow(), boat.getPosition().getColumn()-i, board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].getIllustration(), board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].isHasBeenHit(), boat));
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setBoat(null);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setIllustration(" ");
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setHasBeenHit(false);
                }
                break;
        }
        return removedBoat;
    }

    /**
     * Add target on a grid.
     * @param target The target to add on the grid.
     */
    public void addTarget(Case target) {
        if(!board[target.getRow()][target.getColumn()].getIllustration().equals("#")) board[target.getRow()][target.getColumn()].setIllustration("o");
    }

    /**
     * Remove obsolete targets from a grid.
     */
    public void clearPreviousTarget() {
        for (int line = 0; line < size; line++) {
            for (int column = 0; column < size; column++) {
                if(line == 0 && column == 0) {
                    this.board[line][column].setIllustration(" ");
                } else if (line == 0) {
                    this.board[line][column].setIllustration(Integer.toString(column));
                } else if (column == 0) {
                    int letterValue = line + 64;
                    this.board[line][column].setIllustration(Character.toString((char) letterValue));
                } else {
                    if(!this.board[line][column].getIllustration().equals("#")) {
                        this.board[line][column].setIllustration(" ");
                    }
                }
            }
        }
    }

    /**
     * Move a boat on a grid.
     * @param boatOldPos The boat last position.
     * @param dirToMove The boat new direction.
     * @param nbOfMove The boat case number to move.
     */
    public void moveBoat(List<Case> boatOldPos, Direction dirToMove, int nbOfMove) {
        //Add the boat, then we'll set his life and illustration for each case (he might have lost life etc, so we need
        //to keep that)
        switch (dirToMove) {
            case NORTH:
                for (Case c : boatOldPos) {
                    board[c.getRow()-nbOfMove][c.getColumn()].setIllustration(c.getIllustration());
                    board[c.getRow()-nbOfMove][c.getColumn()].setHasBeenHit(c.isHasBeenHit());
                    board[c.getRow()-nbOfMove][c.getColumn()].setBoat(c.getBoat());
                }
                break;
            case EAST:
                for (Case c : boatOldPos) {
                    board[c.getRow()][c.getColumn()+nbOfMove].setIllustration(c.getIllustration());
                    board[c.getRow()][c.getColumn()+nbOfMove].setHasBeenHit(c.isHasBeenHit());
                    board[c.getRow()][c.getColumn()+nbOfMove].setBoat(c.getBoat());
                }
                break;
            case SOUTH:
                for (Case c : boatOldPos) {
                    board[c.getRow()+nbOfMove][c.getColumn()].setIllustration(c.getIllustration());
                    board[c.getRow()+nbOfMove][c.getColumn()].setHasBeenHit(c.isHasBeenHit());
                    board[c.getRow()+nbOfMove][c.getColumn()].setBoat(c.getBoat());
                }
                break;
            case WEST:
                for (Case c : boatOldPos) {
                    board[c.getRow()-nbOfMove][c.getColumn()].setIllustration(c.getIllustration());
                    board[c.getRow()-nbOfMove][c.getColumn()].setHasBeenHit(c.isHasBeenHit());
                    board[c.getRow()-nbOfMove][c.getColumn()].setBoat(c.getBoat());
                }
                break;
        }
    }
}
