package Game.Grid;

import Game.Boats.Boat;

public class Case {
    private int row;
    private int column;
    private Boat boat;
    private String illustration;
    private boolean hasBeenHit;

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

    public Case(int row, int column, String illustration, boolean hasBeenHit, Boat boat) {
        this.row = row;
        this.column = column;
        this.boat = boat;
        this.illustration = illustration;
        this.hasBeenHit = hasBeenHit;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public int getRow() { return row; }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isHasBeenHit() { return hasBeenHit; }

    public void setHasBeenHit(boolean hasBeenHit) { this.hasBeenHit = hasBeenHit; }
}
