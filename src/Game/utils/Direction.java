package Game.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Direction enum that represents the Cardinal directions.
 *
 * @author Loïc Bothorel & Paul Michaud
 */
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Get a random direction from the Enum.
     * @return direction
     */
    public static Direction randomDirection()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
