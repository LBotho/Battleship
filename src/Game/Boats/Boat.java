package Game.Boats;

import Game.utils.Direction;

import java.util.Map;

abstract class Boat {
    protected Map<Integer,Integer> position;
    protected Direction direction;
    protected int range;
    protected int health = 2;

    abstract void move();
    abstract void attack();
    abstract int[] getTargets();
}
