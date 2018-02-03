package Game.Player;

import Game.Boats.Boat;
import Game.Grid.Case;

import java.util.List;

public interface Player {
    void placeBoats();
    List<Boat> getBoatsList();
    Case pickTarget();
    int hit(Case target);
    void moveBoat();
}
