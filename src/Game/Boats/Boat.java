package Game.Boats;

import Game.Grid.Square;
import Game.utils.Direction;

/**
 * Boat abstract class that contains all the boats parameters (like the direction, the size, the fire range, etc)
 * which are common to all the boats.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
abstract public class Boat {
    private Square position;
    private Direction direction;
    private int size;
    private int range;
    private int health = 2;
    private String name;

    /**
     * Get boat's position.
     *
     * @return The start position of the boat on the grid.
     */
    public Square getPosition() { return position; }
    /**
     * Set boat's position.
     *
     * @param position The start position of the boat on the grid.
     */
    public void setPosition(Square position) { this.position = position; }

    /**
     * Get boat's direction.
     *
     * @return The direction of the boat (North, East, South or West).
     */
    public Direction getDirection() { return direction; }
    /**
     * Set boat's direction.
     *
     * @param direction The direction of the boat (North, East, South or West).
     */
    public void setDirection(Direction direction) { this.direction = direction; }

    /**
     * Get boat's size.
     *
     * @return The size of the boat.
     */
    public int getSize() { return size; }
    /**
     * Set boat's size.
     *
     * @param size The size of the boat.
     */
    public void setSize(int size) { this.size = size; }

    /**
     * Get boat's range.
     *
     * @return The fire range of the boat.
     */
    public int getRange() { return range; }
    /**
     * Set boat's range.
     *
     * @param range The fire range of the boat.
     */
    public void setRange(int range) { this.range = range; }

    /**
     * Get boat's health.
     *
     * @return The health of the boat.
     */
    public int getHealth() { return health; }

    /**
     * Get boat's name.
     *
     * @return The name of the boat.
     */
    public String getName() { return name; }
    /**
     * Set boat name.
     *
     * @param name The name of the boat.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Decrements boat's health.
     */
    public void damage() { this.health--; }
}
