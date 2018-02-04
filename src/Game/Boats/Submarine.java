package Game.Boats;

/**
 * Submarine class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Submarine extends Boat {
    /**
     * @param size The size of the Submarine.
     * @param range The fire range of the Submarine.
     */
    public Submarine(int size, int range) {
        setSize(size);
        setRange(range);
        setName("Submarine");
    }
}
