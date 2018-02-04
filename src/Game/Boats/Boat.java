package Game.Boats;

import Game.Grid.Case;
import Game.utils.Direction;

abstract public class Boat {
    private Case position;
    private Direction direction;
    private int size;
    private int range;
    private int health = 2;
    private String name;

    /**
     * Get boat's position.
     * @return position
     */
    public Case getPosition() { return position; }
    /**
     * Set boat's position.
     * @param position The position the user chose.
     */
    public void setPosition(Case position) { this.position = position; }

    /**
     * Get boat's direction.
     * @return direction
     */
    public Direction getDirection() { return direction; }
    /**
     * Set boat's direction.
     * @param direction The direction the user chose (North, East, South or West).
     */
    public void setDirection(Direction direction) { this.direction = direction; }

    /**
     * Get boat's size.
     * @return size
     */
    public int getSize() { return size; }
    /**
     * Set boat's size.
     * @param size The size of the boat.
     */
    public void setSize(int size) { this.size = size; }

    /**
     * Get boat's range.
     * @return range
     */
    public int getRange() { return range; }
    /**
     * Set boat's range.
     * @param range The range of fire.
     */
    public void setRange(int range) { this.range = range; }

    /**
     * Get boat's health.
     * @return health
     */
    public int getHealth() { return health; }

    /**
     * Get boat's name.
     * @return name
     */
    public String getName() { return name; }
    /**
     * Set boat name.
     * @param name The name of the boat.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Decrements boat's health.
     */
    public void damage() { this.health--; }
}
