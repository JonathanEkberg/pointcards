package pointcards.io.input;

import java.util.Scanner;

/**
 * The LocalConsoleInput class handles console input locally.
 */
public class LocalConsoleInput implements IInput {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Queries a string input from the user.
     * 
     * @param message The message to display to the user.
     * @return The user's input as a string.
     */
    @Override
    public String queryString(String message) {
        System.out.printf("%s: ", message);
        return scanner.nextLine();
    }

    /**
     * Queries an integer input from the user.
     * 
     * @param query The query to display to the user.
     * @return The user's input as an integer.
     */
    @Override
    public int queryInt(String query) {
        System.out.printf("%s: ", query);
        return scanner.nextInt();
    }

    /**
     * Queries an integer input from the user within a specified range.
     * 
     * @param query The query to display to the user.
     * @param min   The minimum acceptable value.
     * @param max   The maximum acceptable value.
     * @return The user's input as an integer.
     */
    @Override
    public int queryInt(String query, int min, int max) {
        int result = queryInt(query);

        if (result < min || result > max) {
            System.out.printf("Value must be between %d and %d.%n", min, max);
            return queryInt(query, min, max);
        }

        return result;
    }
}