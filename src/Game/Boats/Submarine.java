package Game.Boats;

public class Submarine extends Boat {
    public Submarine() {
        setSize(3);
        setRange(4);
        setName("Submarine");
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
