package Game.Grid;

import Game.Boats.Boat;

public class Case {
    private int row;
    private int column;
    private Boat boat;
    private String illustration;

    public Case(int row, int column) {
        this.row = row;
        this.column = column;
        this.boat = null;
    }

    public Case(int row, int column, String illustration) {
        this.row = row;
        this.column = column;
        this.boat = null;
        this.illustration = illustration;
    }

    public int getRow() { return row; }

    public void setRow(int row) { this.row = row; }

    public int getColumn() { return column; }

    public void setColumn(int column) { this.column = column; }

    public Boat getBoat() { return boat; }

    public void setBoat(Boat boat) { this.boat = boat; }

    public String getIllustration() { return illustration; }

    public void setIllustration(String illustration) { this.illustration = illustration; }
}
