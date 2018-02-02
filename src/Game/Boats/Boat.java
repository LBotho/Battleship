package Game.Boats;

import Game.utils.Direction;
import com.sun.javafx.geom.Vec2d;

import java.util.Map;
import java.util.Vector;

abstract public class Boat {
    private Vec2d position;
    private Direction direction;
    private int size;
    private int range;
    private int health = 2;
    private String name;


    abstract void move();
    abstract void attack();
    abstract int[] getTargets();

    // GETTERS AND SETTERS


    public void setPosition(Vec2d position) { this.position = position; }
    public Vec2d getPosition() { return position; }

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

}
