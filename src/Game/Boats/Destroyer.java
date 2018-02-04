package Game.Boats;

/**
 * Destroyer class.
 *
 * @author Loic Bothorel
 * @author Paul Michaud
 */
public class Destroyer extends Boat {
    /**
     * Destroyer constructor.
     * @param size The size of the Destroyer.
     * @param range The fire range of the Destroyer.
     */
    public Destroyer(int size, int range) {
        setSize(size);
        setRange(range);
        setName("Destroyer");
    }
}
