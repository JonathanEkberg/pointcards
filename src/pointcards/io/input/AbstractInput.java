package pointcards.io.input;

/**
 * The AbstractInput class provides base implementations for input operations.
 */
public abstract class AbstractInput implements IInput {
    /**
     * Queries a string input from the user.
     * 
     * @param message The message to display to the user.
     * @return The user's input as a string.
     */
    public abstract String queryString(String message);

    /**
     * Queries an integer input from the user.
     * 
     * @param query The query to display to the user.
     * @return The user's input as an integer.
     */
    public int queryInt(String query) {
        return this.queryInt(query, null);
    }

    /**
     * Queries an integer input from the user within a specified range.
     * 
     * @param query The query to display to the user.
     * @param min   The minimum acceptable value.
     * @param max   The maximum acceptable value.
     * @return The user's input as an integer.
     */
    public int queryInt(String query, int min, int max) {
        int result = this.queryInt(query, null);

        if (result < min) {
            return queryInt(query,
                    String.format("Value must be an integer greater than or equal to %d.", min));
        }

        if (result > max) {
            return queryInt(query,
                    String.format("Value must be an integer less than or equal to %d.", max));
        }

        return result;
    }

    /**
     * Queries an integer input from the user with an error message.
     * 
     * @param query The query to display to the user.
     * @param error The error message to display if the input is invalid.
     * @return The user's input as an integer.
     */
    private int queryInt(String query, String error) {
        try {
            String formattedQuery;

            if (error != null) {
                formattedQuery = String.format("%s\n%s", query, error);
            } else {
                formattedQuery = query;
            }

            String result = this.queryString(formattedQuery);
            int parsed = Integer.parseInt(result);
            return parsed;
        } catch (NumberFormatException e) {
            if (error != null) {
                return queryInt(query, error);
            }

            return queryInt(query, ": Invalid input. Please enter a valid integer.");
        }
    }
}