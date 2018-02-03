package Game.Grid;

import Game.Boats.Boat;

public class Grid {
    private final int size=11;
    private Case[][] board = new Case[size][size];

    public int getSize() { return size; }

    public Grid() {
       for (int colonne = 0; colonne < size; colonne++) {
           for (int ligne = 0; ligne < size; ligne++) {
               if(colonne == 0 && ligne == 0) {
                   this.board[ligne][colonne] = new Case(ligne,colonne," ");
               } else if (ligne == 0) {
                   this.board[ligne][colonne] = new Case(ligne, colonne, Integer.toString(colonne));
               }else if (colonne == 0) {
                   int letterValue = ligne + 64;
                   this.board[ligne][colonne] = new Case(ligne,colonne,Character.toString((char) letterValue));
               } else {
                   this.board[ligne][colonne] = new Case(ligne,colonne," ");
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
                    board[boat.getPosition().getLigne()-i][boat.getPosition().getColonne()].setBoat(boat);
                    board[boat.getPosition().getLigne()-i][boat.getPosition().getColonne()].setIllustration("x");
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLigne()][boat.getPosition().getColonne()+i].setBoat(boat);
                    board[boat.getPosition().getLigne()][boat.getPosition().getColonne()+i].setIllustration("x");
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLigne()+i][boat.getPosition().getColonne()].setBoat(boat);
                    board[boat.getPosition().getLigne()+i][boat.getPosition().getColonne()].setIllustration("x");
                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLigne()][boat.getPosition().getColonne()-i].setBoat(boat);
                    board[boat.getPosition().getLigne()][boat.getPosition().getColonne()-i].setIllustration("x");
                }
                break;
        }
    }

    public void removeBoat(Boat boat) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLigne()-i][boat.getPosition().getColonne()].setBoat(null);
                    board[boat.getPosition().getLigne()-i][boat.getPosition().getColonne()].setIllustration(" ");
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLigne()][boat.getPosition().getColonne()+i].setBoat(null);
                    board[boat.getPosition().getLigne()][boat.getPosition().getColonne()+i].setIllustration(" ");
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLigne()+i][boat.getPosition().getColonne()].setBoat(null);
                    board[boat.getPosition().getLigne()+i][boat.getPosition().getColonne()].setIllustration(" ");
                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[boat.getPosition().getLigne()][boat.getPosition().getColonne()-i].setBoat(null);
                    board[boat.getPosition().getLigne()][boat.getPosition().getColonne()-i].setIllustration(" ");
                }
                break;
        }

    }

    public Case[][] getBoard() {
        return board;
    }

    public void addTarget(Case target) {
        board[target.getColonne()][target.getLigne()].setIllustration("o");
    }
}
