package Game.Player;

import Game.Boats.*;

import java.util.ArrayList;
import java.util.List;

public class Computer implements Player {

    List<Boat> boatsList = new ArrayList<>();

    public Computer() {
        System.out.println("Constructeur computer");
        boatsList.add(new Carrier());
        boatsList.add(new Battleship());
        boatsList.add(new Cruiser());
        boatsList.add(new Submarine());
        boatsList.add(new Destroyer());
    }

    @Override
    public void placeBoats() {

    }
}
