package Game.Grid;

import Game.Boats.Boat;

/**
 * Square class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Square {
    private int row;
    private int column;
    private Boat boat;
    private String symbol;
    private boolean hasBeenHit;

    /**
     * Square constructor 1.
     *
     * @param row The row of the Square.
     * @param column The column of the Square.
     */
    public Square(int row, int column) {
        this.row = row;
        this.column = column;
        this.boat = null;
    }

    /**
     * Square constructor 2.
     *
     * @param row The row of the Square.
     * @param column The column of the Square.
     * @param symbol The symbol of the Square.
     */
    public Square(int row, int column, String symbol) {
        this.row = row;
        this.column = column;
        this.boat = null;
        this.symbol = symbol;
    }

    /**
     * Square constructor 3.
     *
     * @param row The row of the Square.
     * @param column The column of the Square.
     * @param symbol The symbol of the Square.
     * @param hasBeenHit Boolean, true if the Square is hit, false if not.
     * @param boat The boat that occupies the Square.
     */
    public Square(int row, int column, String symbol, boolean hasBeenHit, Boat boat) {
        this.row = row;
        this.column = column;
        this.boat = boat;
        this.symbol = symbol;
        this.hasBeenHit = hasBeenHit;
    }

    /**
     * Get boat.
     *
     * @return The boat
     */
    public Boat getBoat() { return boat; }
    /**
     * Set boat.
     *
     * @param boat The boat that occupies the Square.
     */
    public void setBoat(Boat boat) { this.boat = boat; }

    /**
     * Get symbol.
     *
     * @return The symbol of the Square.
     */
    public String getSymbol() { return symbol; }
    /**
     * Set symbol.
     *
     * @param symbol The symbol of the Square.
     */
    public void setSymbol(String symbol) { this.symbol = symbol; }

    /**
     * Get row.
     *
     * @return The row value of the Square
     */
    public int getRow() { return row; }

    /**
     * Get column.
     *
     * @return The column value of the Square
     */
    public int getColumn() { return column; }

    /**
     * Get hasBeenHit.
     *
     * @return A boolean.
     */
    public boolean isHasBeenHit() { return hasBeenHit; }
    /**
     * Set hasBeenHit.
     *
     * @param hasBeenHit Boolean, true if the Square is hit, false if not.
     */
    public void setHasBeenHit(boolean hasBeenHit) { this.hasBeenHit = hasBeenHit; }
}
