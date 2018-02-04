package Game.Boats;

public class Submarine extends Boat {
    /**
     * Submarine constructor.
     * @param size The size of the Submarine.
     * @param range The fire range of the Submarine.
     */
    public Submarine(int size, int range) {
        setSize(size);
        setRange(range);
        setName("Submarine");
    }
}
