package Game.Player;

import Game.Boats.*;
import Game.Grid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Player {
    Grid attackGrid = new Grid();
    Grid defenseGrid = new Grid();



    void placeBoats();
}
