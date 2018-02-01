package Game.Boats;

public class Carrier extends Boat {
    public Carrier() {
        setSize(5);
        setRange(2);
        setName("Carrier");
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
