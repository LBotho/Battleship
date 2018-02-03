package Game.Grid;

import Game.Boats.Boat;

public class Case {
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    private Boat boat;
    private String illustration;

    public Case(int line, int column) {
        this.line = line;
        this.column = column;
        this.boat = null;
    }

    public Case(int line, int column, String illustration) {
        this.line = line;
        this.column = column;
        this.boat = null;
        this.illustration = illustration;
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
}
