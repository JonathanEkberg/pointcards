package pointcards.game;

/**
 * The Participant class represents a participant in the point cards game.
 * This class serves as a base class for different types of participants
 * such as players, bots, etc.
 */
public class Participant {
    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
}
