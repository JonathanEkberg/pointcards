package pointcards.io.output;

/**
 * The IOutput interface defines methods for output operations.
 */
public interface IOutput {
    /**
     * Sends a message to the output.
     * 
     * @param message The message to send.
     */
    public void send(String message);
}