package Game.Player;

import Game.Boats.*;
import Game.Grid;

import java.io.IOException;

public interface Player {
    Grid attackGrid = new Grid();
    Grid defenseGrid = new Grid();
    Carrier carrier = new Carrier();
    Battleship battleship = new Battleship();
    Cruiser cruiser = new Cruiser();
    Submarine submarine = new Submarine();
    Destroyer destroyer = new Destroyer();

    void placeBoats();
}
