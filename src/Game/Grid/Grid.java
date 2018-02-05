package Game.Grid;

import Game.Boats.Boat;
import Game.utils.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Grid class, manages all the grid system.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Grid {
    private final int size=11;
    private Square[][] board = new Square[size][size];

    public Grid() {
       for (int row = 0; row < size; row++) {
           for (int column = 0; column < size; column++) {
               if(row == 0 && column == 0) {
                   this.board[row][column] = new Square(row,column," ");
               } else if (row == 0) {
                   this.board[row][column] = new Square(row,column,Integer.toString(column));
               } else if (column == 0) {
                   int letterValue = row + 64;
                   this.board[row][column] = new Square(row,column,Character.toString((char) letterValue));
               } else {
                   this.board[row][column] = new Square(row,column," ");
               }
           }
       }
    }

    /**
     * Get size.
     *
     * @return The size of the grid.
     */
    public int getSize() { return size; }

    /**
     * Get board.
     *
     * @return The whole grid.
     */
    public Square[][] getBoard() { return board; }

    /**
     * Display the grid.
     */
    public void displayGrid() {
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                System.out.print(row == 10 ? "["+ this.board[column][row].getSymbol() +"]" : "["+ this.board[column][row].getSymbol() +"]");
            }
            System.out.println("");
        }
    }

    /**
     * Add a boat on a grid.
     *
     * @param boat The boat to add on the grid.
     * @param boatIndex The index of the boat in the boat list, used to display each boat differently
     */
    public void addBoat(Boat boat, int boatIndex) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setBoat(boat);
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setSymbol(Integer.toString(boatIndex));
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setHasBeenHit(false);
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setBoat(boat);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setSymbol(Integer.toString(boatIndex));
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setHasBeenHit(false);
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setBoat(boat);
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setSymbol(Integer.toString(boatIndex));
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setHasBeenHit(false);

                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setBoat(boat);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setSymbol(Integer.toString(boatIndex));
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setHasBeenHit(false);

                }
                break;
        }
    }

    /**
     * Remove a boat from a grid.
     *
     * @param boat The boat to remove from the grid.
     *
     * @return The list of all the Square that contains the boat.
     */
    public List<Square> removeBoat(Boat boat) {
        List<Square> removedBoat = new ArrayList<>();
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    removedBoat.add(new Square(boat.getPosition().getRow()-i, boat.getPosition().getColumn(), board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].getSymbol(), board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].isHasBeenHit(), boat));
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setBoat(null);
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setSymbol(" ");
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setHasBeenHit(false);

                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    removedBoat.add(new Square(boat.getPosition().getRow(), boat.getPosition().getColumn()+i, board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].getSymbol(), board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].isHasBeenHit(), boat));
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setBoat(null);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setSymbol(" ");
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setHasBeenHit(false);
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    removedBoat.add(new Square(boat.getPosition().getRow()+i, boat.getPosition().getColumn(), board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].getSymbol(), board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].isHasBeenHit(), boat));
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setBoat(null);
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setSymbol(" ");
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setHasBeenHit(false);

                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    removedBoat.add(new Square(boat.getPosition().getRow(), boat.getPosition().getColumn()-i, board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].getSymbol(), board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].isHasBeenHit(), boat));
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setBoat(null);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setSymbol(" ");
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setHasBeenHit(false);
                }
                break;
        }
        return removedBoat;
    }

    /**
     * Add target on a grid.
     *
     * @param target The target to add on the grid.
     */
    public void addTarget(Square target) {
        if(!board[target.getRow()][target.getColumn()].getSymbol().equals("#")) board[target.getRow()][target.getColumn()].setSymbol("o");
    }

    /**
     * Remove obsolete targets from a grid.
     */
    public void clearPreviousTarget() {
        for (int line = 0; line < size; line++) {
            for (int column = 0; column < size; column++) {
                if(line == 0 && column == 0) {
                    this.board[line][column].setSymbol(" ");
                } else if (line == 0) {
                    this.board[line][column].setSymbol(Integer.toString(column));
                } else if (column == 0) {
                    int letterValue = line + 64;
                    this.board[line][column].setSymbol(Character.toString((char) letterValue));
                } else {
                    if(!this.board[line][column].getSymbol().equals("#")) {
                        this.board[line][column].setSymbol(" ");
                    }
                }
            }
        }
    }

    /**
     * Move a boat on a grid.
     *
     * @param boatOldPos The boat last position.
     * @param dirToMove The boat new direction.
     * @param nbOfMove The boat case number to move.
     */
    public void moveBoat(List<Square> boatOldPos, Direction dirToMove, int nbOfMove) {
        //Add the boat, then we'll set his life and illustration for each case (he might have lost life etc, so we need
        //to keep that)
        switch (dirToMove) {
            case NORTH:
                for (Square c : boatOldPos) {
                    board[c.getRow()-nbOfMove][c.getColumn()].setSymbol(c.getSymbol());
                    board[c.getRow()-nbOfMove][c.getColumn()].setHasBeenHit(c.isHasBeenHit());
                    board[c.getRow()-nbOfMove][c.getColumn()].setBoat(c.getBoat());
                }
                break;
            case EAST:
                for (Square c : boatOldPos) {
                    board[c.getRow()][c.getColumn()+nbOfMove].setSymbol(c.getSymbol());
                    board[c.getRow()][c.getColumn()+nbOfMove].setHasBeenHit(c.isHasBeenHit());
                    board[c.getRow()][c.getColumn()+nbOfMove].setBoat(c.getBoat());
                }
                break;
            case SOUTH:
                for (Square c : boatOldPos) {
                    board[c.getRow()+nbOfMove][c.getColumn()].setSymbol(c.getSymbol());
                    board[c.getRow()+nbOfMove][c.getColumn()].setHasBeenHit(c.isHasBeenHit());
                    board[c.getRow()+nbOfMove][c.getColumn()].setBoat(c.getBoat());
                }
                break;
            case WEST:
                for (Square c : boatOldPos) {
                    board[c.getRow()][c.getColumn()-nbOfMove].setSymbol(c.getSymbol());
                    board[c.getRow()][c.getColumn()-nbOfMove].setHasBeenHit(c.isHasBeenHit());
                    board[c.getRow()][c.getColumn()-nbOfMove].setBoat(c.getBoat());
                }
                break;
        }
    }
}
