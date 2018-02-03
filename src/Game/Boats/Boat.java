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

    // GETTERS AND SETTERS

    public void setPosition(Case position) { this.position = position; }
    public Case getPosition() { return position; }

    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public int getRange() { return range; }
    public void setRange(int range) { this.range = range; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void damage() { this.health--; }
}
