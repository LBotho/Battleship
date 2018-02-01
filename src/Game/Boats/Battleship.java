package Game.Boats;

public class Battleship extends Boat {
    public Battleship() {
        setSize(2);
        setRange(5);
        setName("Battleship");
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
