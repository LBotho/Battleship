package Game.Boats;

/**
 * Aircraft Carrier class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Carrier extends Boat {
    /**
     * @param size The size of the Carrier.
     * @param range The fire range of the Carrier.
     */
    public Carrier(int size, int range) {
        setSize(size);
        setRange(range);
        setName("Carrier");
    }
}
