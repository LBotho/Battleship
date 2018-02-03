package Game.utils;

import Game.Grid.Case;

import java.util.List;

public class Functions {
    public static int charToIntPosition(String posChar) {
        return (int)posChar.toLowerCase().charAt(0)-96;
    }
    public static boolean containsTarget(List<Case> list, Case target) {
        for (Case square: list) {
            if (target.getRow() == square.getRow() && target.getColumn() == square.getColumn()) return true;
        }
        return false;
    }
}
