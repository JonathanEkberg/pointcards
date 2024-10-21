package pointcards.game;

/**
 * The `BaseBot` class represents a basic bot participant in the game.
 * It extends the `Participant` class and provides a simple implementation
 * with a name attribute.
 * 
 * <p>
 * This class can be used as a base class for more complex bot implementations.
 * 
 * Example usage:
 * 
 * <pre>
 * {@code
 * BaseBot bot = new BaseBot("BotName");
 * System.out.println(bot.getName()); // Outputs: BotName
 * }
 * </pre>
 * 
 * @see Participant
 */
public class BaseBot extends Participant {
    private final String name;

    /**
     * Constructs a new `BaseBot` with the specified name.
     * 
     * @param name The name of the bot.
     */
    public BaseBot(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the bot.
     * 
     * @return The name of the bot.
     */
    public String getName() {
        return name;
    }
}
