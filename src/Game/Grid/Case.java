package Game.Grid;

import Game.Boats.Boat;

public class Case {
    private int colonne;
    private int ligne;
    private Boat boat;
    private String illustration;

    public Case(int x, int y) {
        this.colonne = x;
        this.ligne = y;
        this.boat = null;
    }

    public Case(int x, int y, String illustration) {
        this.colonne = x;
        this.ligne = y;
        this.boat = null;
        this.illustration = illustration;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
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
