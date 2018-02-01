package Game.Boats;

public class Destroyer extends Boat {
    public Destroyer() {
        setSize(3);
        setRange(2);
        setName("Destroyer");
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
