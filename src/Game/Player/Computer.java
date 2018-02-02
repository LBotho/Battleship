package Game.Player;

import Game.Boats.*;
import Game.Grid;

import java.util.ArrayList;
import java.util.List;

public class Computer implements Player {

    List<Boat> boatsList = new ArrayList<>();
    Grid attackGrid = new Grid();
    Grid defenseGrid = new Grid();

    public Computer() {
        System.out.println("Constructeur computer");
        boatsList.add(new Carrier());
        boatsList.add(new Cruiser());
        boatsList.add(new Destroyer());
        boatsList.add(new Submarine());
        boatsList.add(new Torpedo());
    }

    @Override
    public void placeBoats() {

    }

    @Override
    public void moveBoat() {

    }
}
