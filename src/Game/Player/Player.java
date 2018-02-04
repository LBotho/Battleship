package Game.Player;

import Game.Boats.Boat;
import Game.Grid.Case;
import Game.utils.Direction;

import java.util.List;

public interface Player {
    List<Boat> getBoatsList();
    void placeBoats();
    boolean checkBoatPosition(int row, int column, Direction direction, int boatSize);
    boolean checkMoveBoat(int row, int column,Boat boat);
    Case pickTarget();
    int hit(Case target);
    void moveBoat();
    boolean lost();
    void noticeHit(Case target);
}
