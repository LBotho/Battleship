package Game.Player;

import Game.Boats.Boat;
import Game.Grid.Case;
import Game.utils.Direction;

import java.util.List;

/**
 * Player interface.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public interface Player {
    List<Boat> getBoatsList();
    void placeBoats();
    boolean checkBoatPosition(int row, int column, Direction direction, int boatSize);
    boolean checkMoveBoat(int row, int column,Boat boat, Direction dirToMove);

    Case pickTarget();
    int hit(Case target);
    void moveBoat();
    boolean lost();
    void noticeHit(Case target);
}
