package pointcards.io.input;

/**
 * The IInput interface defines methods for input operations.
 */
public interface IInput {
    /**
     * Queries a string input from the user.
     * 
     * @param message The message to display to the user.
     * @return The user's input as a string.
     */
    public String queryString(String message);

    /**
     * Queries an integer input from the user.
     * 
     * @param query The query to display to the user.
     * @return The user's input as an integer.
     */
    public int queryInt(String query);

    /**
     * Queries an integer input from the user within a specified range.
     * 
     * @param query The query to display to the user.
     * @param min   The minimum acceptable value.
     * @param max   The maximum acceptable value.
     * @return The user's input as an integer.
     */
    public int queryInt(String query, int min, int max);
}