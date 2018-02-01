package Game.Boats;

public class Cruiser extends Boat {

    public Cruiser () {
        setSize(4);
        setRange(2);
        setName("Cruiser");
    }

    @Override
    void move() {

    }

    @Override
    void attack() {

    }

    @Override
    int[] getTargets() {
        return new int[0];
    }
}
