package pointcards.game;

/**
 * The IGame interface provides a contract for game implementations.
 * Any class that implements this interface must provide an implementation
 * for the run method, which contains the logic to execute the game.
 */
public interface IGame {
    /**
     * Executes the game logic. This method should be implemented by any class
     * that wants to define specific game behavior.
     */
    public void run();
}
