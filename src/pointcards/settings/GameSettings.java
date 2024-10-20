package pointcards.settings;

/**
 * The `GameSettings` class extends `ProgramSettings` to include game-specific
 * settings.
 * It manages the number of players and bots in the game.
 */
public class GameSettings extends ProgramSettings {
    private int numberOfPlayers, numberOfBots;

    /**
     * Constructs a new `GameSettings` instance from an `OptionalGameSettings`
     * instance.
     * 
     * @param current The `OptionalGameSettings` instance to copy settings from.
     */
    public GameSettings(OptionalGameSettings current) {
        super(current.isServer, current.hostname, current.port);
        this.numberOfPlayers = current.numberOfPlayers.orElseThrow();
        this.numberOfBots = current.numberOfBots.orElseThrow();
    }

    /**
     * Constructs a new `GameSettings` instance.
     * 
     * @param isServer        Indicates if the settings are for a server.
     * @param hostname        The hostname for the server or client.
     * @param port            The port number for the server or client.
     * @param numberOfPlayers The number of players in the game.
     * @param numberOfBots    The number of bots in the game.
     */
    public GameSettings(boolean isServer, String hostname, int port, int numberOfPlayers, int numberOfBots) {
        super(isServer, hostname, port);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfBots = numberOfBots;
    }

    /**
     * Returns a string representation of the `GameSettings`.
     * 
     * @return A string representation of the settings.
     */
    public String toString() {
        return "Values{" +
                "isServer=" + isServer +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", numberOfPlayers=" + numberOfPlayers +
                ", numberOfBots=" + numberOfBots +
                '}';
    }

    /**
     * Gets the number of players.
     * 
     * @return The number of players.
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Sets the number of players.
     * 
     * @param numberOfPlayers The number of players to set.
     */
    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Gets the number of bots.
     * 
     * @return The number of bots.
     */
    public int getNumberOfBots() {
        return numberOfBots;
    }

    /**
     * Sets the number of bots.
     * 
     * @param numberOfBots The number of bots to set.
     */
    public void setNumberOfBots(Integer numberOfBots) {
        this.numberOfBots = numberOfBots;
    }
}