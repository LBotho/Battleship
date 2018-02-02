package Game.Player;

import Game.Boats.*;
import Game.Grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class Computer implements Player {

    private List<Boat> boatsList = new ArrayList<>();
    private Grid attackGrid = new Grid();
    private Grid defenseGrid = new Grid();

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

    @Override
    public List<Boat> getBoatsList() {
        return this.boatsList;
    }

    @Override
    public void attack() {

    }
}
