package Game.Grid;

import Game.Boats.Boat;

public class Case {
    private int row;
    private int column;
    private Boat boat;
    private String illustration;
    private boolean hasBeenHit;

    /**
     * Case constructor 1.
     * @param row The row of the Case.
     * @param column The column of the Case.
     */
    public Case(int row, int column) {
        this.row = row;
        this.column = column;
        this.boat = null;
    }

    /**
     * Case constructor 2.
     * @param row The row of the Case.
     * @param column The column of the Case.
     * @param illustration The illustration of the Case.
     */
    public Case(int row, int column, String illustration) {
        this.row = row;
        this.column = column;
        this.boat = null;
        this.illustration = illustration;
    }

    /**
     * Case constructor 3.
     * @param row The row of the Case.
     * @param column The column of the Case.
     * @param illustration The illustration of the Case.
     * @param hasBeenHit Boolean, true if the Case is hit, false if not.
     * @param boat The boat that occupies the Case.
     */
    public Case(int row, int column, String illustration, boolean hasBeenHit, Boat boat) {
        this.row = row;
        this.column = column;
        this.boat = boat;
        this.illustration = illustration;
        this.hasBeenHit = hasBeenHit;
    }

    /**
     * Get boat.
     * @return boat
     */
    public Boat getBoat() { return boat; }
    /**
     * Set boat.
     * @param boat The boat that occupies the Case.
     */
    public void setBoat(Boat boat) { this.boat = boat; }

    /**
     * Get illustration.
     * @return illustration
     */
    public String getIllustration() { return illustration; }
    /**
     * Set illustration.
     * @param illustration The illustration of the Case.
     */
    public void setIllustration(String illustration) { this.illustration = illustration; }

    /**
     * Get row.
     * @return row
     */
    public int getRow() { return row; }

    /**
     * Get column.
     * @return column
     */
    public int getColumn() { return column; }

    /**
     * Get hasBeenHit.
     * @return hasBeenHit
     */
    public boolean isHasBeenHit() { return hasBeenHit; }
    /**
     * Set hasBeenHit.
     * @param hasBeenHit Boolean, true if the Case is hit, false if not.
     */
    public void setHasBeenHit(boolean hasBeenHit) { this.hasBeenHit = hasBeenHit; }
}
