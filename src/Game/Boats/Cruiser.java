package Game.Boats;

/**
 * Cruiser class.
 *
 * @author Loïc Bothorel & Paul Michaud
 */
public class Cruiser extends Boat {
    /**
     * Cruiser constructor.
     * @param size The size of the Cruiser.
     * @param range The fire range of the Cruiser.
     */
    public Cruiser (int size, int range) {
        setSize(size);
        setRange(range);
        setName("Cruiser");
    }
}
