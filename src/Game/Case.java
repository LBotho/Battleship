package Game;

import Game.Boats.Boat;

public class Case {
    private int posX;
    private int posY;
    private Boat boat;
    private char illustration;

    public Case(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Case(int posX, int posY, char illustration) {
        this.posX = posX;
        this.posY = posY;
        this.illustration = illustration;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public char getIllustration() {
        return illustration;
    }

    public void setIllustration(char illustration) {
        this.illustration = illustration;
    }
}
