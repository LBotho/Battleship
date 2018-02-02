package Game;

import Game.Boats.Boat;

public class Grid {
    private final int size=10;
    private Case[][] board = new Case[size][size];

    public Grid() {
        for (int i=0; i<size;i++) {
            for (int j=0; j<size;j++) {
                this.board[i][j]= new Case(i,j,' ');
            }
        }
    }

    public void displayGrid() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i == 0 ? "[ ]" : "[" + i + "]");
        }
        System.out.println("");
        for (int i = 0; i < size; i++) {
            char c = (char) ((int) 'A' + i);
            System.out.print("[" + c + "]");

            for (int j = 0; j < size; j++) {
                System.out.print(j == 9 ? "["+ this.board[i][j].getIllustration() +" ]" : "["+ this.board[i][j].getIllustration() +"]");
            }
            System.out.println("");
        }
    }

    public void addBoat(Boat boat) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[(int)boat.getPosition().getPosY()-1-i][(int)boat.getPosition().getPosX()-1].setBoat(boat);
                    board[(int)boat.getPosition().getPosY()-1-i][(int)boat.getPosition().getPosX()-1].setIllustration('x');
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[(int)boat.getPosition().getPosY()-1][(int)boat.getPosition().getPosX()-1+i].setBoat(boat);
                    board[(int)boat.getPosition().getPosY()-1][(int)boat.getPosition().getPosX()-1+i].setIllustration('x');
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[(int)boat.getPosition().getPosY()-1+i][(int)boat.getPosition().getPosX()-1].setBoat(boat);
                    board[(int)boat.getPosition().getPosY()-1+i][(int)boat.getPosition().getPosX()-1].setIllustration('x');
                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[(int)boat.getPosition().getPosY()-1][(int)boat.getPosition().getPosX()-1-i].setBoat(boat);
                    board[(int)boat.getPosition().getPosY()-1][(int)boat.getPosition().getPosX()-1-i].setIllustration('x');
                }
                break;
        }
    }
}
