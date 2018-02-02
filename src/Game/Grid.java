package Game;

import Game.Boats.Boat;

public class Grid {
    private final int size=11;
    private Case[][] board = new Case[size][size];

    public Grid() {
        for (int i=0; i<size;i++) {
            for (int j=0; j<size;j++) {
                this.board[i][j]= new Case(i,j," ");
            }
        }

       for (int i = 0; i < size; i++) {
           this.board[0][i] = (i == 0 ? new Case(i,0," ") : new Case(i,0,Integer.toString(i)));
           for (int j = 0; j < size; j++) {
               if(i==0 && j == 0) {
                   this.board[i][j] = new Case(i,j," ");
               } else if (j == 0){
                   int letterValue = i + 64;
                   this.board[i][j] = new Case(i,j,Character.toString((char) letterValue));
               } else {
                   this.board[i][j] = new Case(i,j," ");
               }
           }
       }


    }

    public void displayGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(j == 10 ? "["+ this.board[i][j].getIllustration() +"]" : "["+ this.board[i][j].getIllustration() +"]");
            }
            System.out.println("");
        }
    }

    public void addBoat(Boat boat) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getPosY()-i][boat.getPosition().getPosX()].setBoat(boat);
                    board[boat.getPosition().getPosY()-i][boat.getPosition().getPosX()].setIllustration("x");
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getPosY()][boat.getPosition().getPosX()+i].setBoat(boat);
                    board[boat.getPosition().getPosY()][boat.getPosition().getPosX()+i].setIllustration("x");
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getPosY()+i][boat.getPosition().getPosX()].setBoat(boat);
                    board[boat.getPosition().getPosY()+i][boat.getPosition().getPosX()].setIllustration("x");
                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getPosY()][boat.getPosition().getPosX()-i].setBoat(boat);
                    board[boat.getPosition().getPosY()][boat.getPosition().getPosX()-i].setIllustration("x");
                }
                break;
        }
    }

    public Case[][] getBoard() {
        return board;
    }
}
