package Game.Player;

import Game.Grid;

public interface Player {
    Grid attackGrid = new Grid();
    Grid defenseGrid = new Grid();

    void placeBoats();
}
