package Game.Boats;

/**
 * Cruiser class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Cruiser extends Boat {
    /**
     * @param size The size of the Cruiser.
     * @param range The fire range of the Cruiser.
     */
    public Cruiser (int size, int range) {
        setSize(size);
        setRange(range);
        setName("Cruiser");
    }
}
