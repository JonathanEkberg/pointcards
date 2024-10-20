package pointcards.io.output;

/**
 * The LocalConsoleOutput class handles console output locally.
 */
public class LocalConsoleOutput implements IOutput {
    /**
     * Sends a message to the console.
     * 
     * @param message The message to send.
     */
    @Override
    public void send(String message) {
        System.out.println(message);
    }
}