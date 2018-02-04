package Game.Player;

import Game.Boats.*;
import Game.Grid.Case;
import Game.Grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class Computer implements Player {
    private Grid attackGrid = new Grid();
    private Grid defenseGrid = new Grid();
    private List<Boat> boatsList = new ArrayList<>();

    public Computer() {
        boatsList.add(new Carrier(5,2));
        //  boatsList.add(new Cruiser(4,2));
        //  boatsList.add(new Destroyer(3,2));
        //  boatsList.add(new Submarine(3,4));
        //  boatsList.add(new Torpedo(2,5));
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
    public Case pickTarget() {
        Case targetChoice = new Case(0,0);
        return targetChoice;
    }

    @Override
    public int hit(Case target) {
        return 0;
    }

    public boolean lost(){
        return true;
    }

    public void noticeHit(Case target) {

    }
}
