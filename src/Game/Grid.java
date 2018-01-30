package Game;

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
}
