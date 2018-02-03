package Game.Grid;

import Game.Boats.Boat;

public class Grid {
    private final int size=11;
    private Case[][] board = new Case[size][size];

    public int getSize() { return size; }

    public Grid() {
       for (int line = 0; line < size; line++) {
           for (int column = 0; column < size; column++) {
               if(line == 0 && column == 0) {
                   this.board[line][column] = new Case(line,column," ");
               } else if (line == 0) {
                   this.board[line][column] = new Case(line,column,Integer.toString(column));
               } else if (column == 0) {
                   int letterValue = line + 64;
                   this.board[line][column] = new Case(line,column,Character.toString((char) letterValue));
               } else {
                   this.board[line][column] = new Case(line,column," ");
               }
           }
       }
    }

    public void displayGrid() {
        for (int column = 0; column < size; column++) {
            for (int line = 0; line < size; line++) {
                System.out.print(line == 10 ? "["+ this.board[column][line].getIllustration() +"]" : "["+ this.board[column][line].getIllustration() +"]");
            }
            System.out.println("");
        }
    }

    public void addBoat(Boat boat) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLine()-i][boat.getPosition().getColumn()].setBoat(boat);
                    board[boat.getPosition().getLine()-i][boat.getPosition().getColumn()].setIllustration("x");
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLine()][boat.getPosition().getColumn()+i].setBoat(boat);
                    board[boat.getPosition().getLine()][boat.getPosition().getColumn()+i].setIllustration("x");
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLine()+i][boat.getPosition().getColumn()].setBoat(boat);
                    board[boat.getPosition().getLine()+i][boat.getPosition().getColumn()].setIllustration("x");
                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLine()][boat.getPosition().getColumn()-i].setBoat(boat);
                    board[boat.getPosition().getLine()][boat.getPosition().getColumn()-i].setIllustration("x");
                }
                break;
        }
    }

    public void removeBoat(Boat boat) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLine()-i][boat.getPosition().getColumn()].setBoat(null);
                    board[boat.getPosition().getLine()-i][boat.getPosition().getColumn()].setIllustration(" ");
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLine()][boat.getPosition().getColumn()+i].setBoat(null);
                    board[boat.getPosition().getLine()][boat.getPosition().getColumn()+i].setIllustration(" ");
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLine()+i][boat.getPosition().getColumn()].setBoat(null);
                    board[boat.getPosition().getLine()+i][boat.getPosition().getColumn()].setIllustration(" ");
                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLine()][boat.getPosition().getColumn()-i].setBoat(null);
                    board[boat.getPosition().getLine()][boat.getPosition().getColumn()-i].setIllustration(" ");
                }
                break;
        }

    }

    public Case[][] getBoard() {
        return board;
    }

    public void addTarget(Case target) {
        board[target.getLine()][target.getColumn()].setIllustration("o");
    }
}
