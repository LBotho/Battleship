package Game.Boats;

public class Torpedo extends Boat {
    /**
     * Torpedo constructor.
     * @param size The size of the Torpedo.
     * @param range The fire range of the Torpedo.
     */
    public Torpedo(int size, int range) {
        setSize(size);
        setRange(range);
        setName("Torpedo");
    }
}
