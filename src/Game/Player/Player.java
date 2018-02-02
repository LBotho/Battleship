package Game.Player;

import Game.Boats.Boat;

import java.util.List;

public interface Player {


    void placeBoats();
    List<Boat> getBoatsList();
    void attack();
    void moveBoat();
}
