package Game.Boats;

public class Carrier extends Boat {
    /**
     * Carrier constructor.
     * @param size The size of the Carrier.
     * @param range The fire range of the Carrier.
     */
    public Carrier(int size, int range) {
        setSize(size);
        setRange(range);
        setName("Carrier");
    }
}
