package Game;

import Game.Boats.Boat;

public class Grid {
    private final int size=10;
    private char[][] board = new char[size][size];

    public Grid() {
        for (int i=0; i<size;i++) {
            for (int j=0; j<size;j++) {
                this.board[i][j]= ' ';
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
                System.out.print(j == 9 ? "["+ this.board[i][j] +" ]" : "["+ this.board[i][j] +"]");
            }
            System.out.println("");
        }
    }

    public void addBoat(Boat boat) {
        switch (boat.getDirection()) {
            case NORTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[(int)boat.getPosition().y-1-i][(int)boat.getPosition().x-1] = 'x';
                }
                break;
            case EAST:
                for (int i=0; i<boat.getSize();i++) {
                    board[(int)boat.getPosition().y-1][(int)boat.getPosition().x-1+i] = 'x';
                }
                break;
            case SOUTH:
                for (int i=0; i<boat.getSize();i++) {
                    board[(int)boat.getPosition().y-1+i][(int)boat.getPosition().x-1] = 'x';
                }
                break;
            case WEST:
                for (int i=0; i<boat.getSize();i++) {
                    board[(int)boat.getPosition().y-1][(int)boat.getPosition().x-1-i] = 'x';
                }
                break;
        }
    }
}
