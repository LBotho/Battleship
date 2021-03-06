package Game.utils;

import Game.Grid.Square;

import java.util.List;

/**
 * Functions class.
 * Contains all the "tools" functions we need.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Functions {
    /**
     * Convert char to integer.
     *
     * @param posChar The char to convert.
     *
     * @return The char converted to integer (A=1,B=2,etc...).
     */
    public static int charToIntPosition(String posChar) { return (int)posChar.toLowerCase().charAt(0)-96; }

    /**
     * Check if the list contains the target.
     *
     * @param list The list in which we check if contains the target.
     * @param target The target to check in the list.
     *
     * @return True if the list contains the target, false if not.
     */
    public static boolean containsTarget(List<Square> list, Square target) {
        for (Square square: list) {
            if (target.getRow() == square.getRow() && target.getColumn() == square.getColumn()) return true;
        }
        return false;
    }
}
