package Game.Boats;

public class Torpedo extends Boat {
    public Torpedo() {
        setSize(2);
        setRange(5);
        setName("Torpedo");
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
