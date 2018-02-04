package Game.Grid;

import Game.Boats.Boat;

public class Grid {
    private final int size=11;
    private Case[][] board = new Case[size][size];

    public int getSize() { return size; }

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

    public void displayGrid() {
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                System.out.print(row == 10 ? "["+ this.board[column][row].getIllustration() +"]" : "["+ this.board[column][row].getIllustration() +"]");
            }
            System.out.println("");
        }
    }

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

    public void removeBoat(Boat boat) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setBoat(null);
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setIllustration(" ");
                    board[boat.getPosition().getRow()-i][boat.getPosition().getColumn()].setHasBeenHit(false);

                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setBoat(null);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setIllustration(" ");
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()+i].setHasBeenHit(false);

                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setBoat(null);
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setIllustration(" ");
                    board[boat.getPosition().getRow()+i][boat.getPosition().getColumn()].setHasBeenHit(false);

                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setBoat(null);
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setIllustration(" ");
                    board[boat.getPosition().getRow()][boat.getPosition().getColumn()-i].setHasBeenHit(false);

                }
                break;
        }

    }

    public Case[][] getBoard() { return board; }

    public void addTarget(Case target) {
        if(!board[target.getRow()][target.getColumn()].getIllustration().equals("#")) board[target.getRow()][target.getColumn()].setIllustration("o");
    }

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
}
