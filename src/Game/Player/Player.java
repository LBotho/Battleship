package Game.Player;

import Game.Boats.Boat;
import Game.Grid.Case;
import Game.utils.Direction;

import java.util.List;

public interface Player {
    void placeBoats();
    boolean checkBoatPosition(int row, int column, Direction direction, int boatSize);
    List<Boat> getBoatsList();
    Case pickTarget();
    int hit(Case target);
    void moveBoat();
    boolean lost();
    void noticeHit(Case target);

}
