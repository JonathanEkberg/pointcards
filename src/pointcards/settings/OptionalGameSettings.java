package pointcards.settings;

import java.util.Optional;

/**
 * The `OptionalGameSettings` class extends `ProgramSettings` to include
 * optional settings
 * that can be customized by the user. These settings include the number of
 * players, number of bots,
 * and the path to the game manifest.
 */
public class OptionalGameSettings extends ProgramSettings {
    String manifestPath;
    Optional<Integer> numberOfPlayers;
    Optional<Integer> numberOfBots;

    /**
     * Constructs a new `OptionalGameSettings` instance.
     * 
     * @param isServer        Indicates if the settings are for a server.
     * @param hostname        The hostname for the server or client.
     * @param port            The port number for the server or client.
     * @param numberOfPlayers The number of players in the game.
     * @param numberOfBots    The number of bots in the game.
     * @param manifestPath    The path to the game manifest.
     */
    public OptionalGameSettings(boolean isServer, String hostname, int port, Integer numberOfPlayers,
            Integer numberOfBots, String manifestPath) {
        super(isServer, hostname, port);
        this.numberOfPlayers = Optional.ofNullable(numberOfPlayers);
        this.numberOfBots = Optional.ofNullable(numberOfBots);
        this.manifestPath = manifestPath;
    }

    /**
     * Returns a string representation of the `OptionalGameSettings`.
     * 
     * @return A string representation of the settings.
     */
    public String toString() {
        return "Values{" +
                "isServer=" + isServer +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", numberOfPlayers=" + this.numberOfPlayers +
                ", numberOfBots=" + this.numberOfBots +
                ", manifestPath=" + this.manifestPath +
                '}';
    }

    /**
     * Gets the number of players.
     * 
     * @return An `Optional` containing the number of players.
     */
    public Optional<Integer> getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    /**
     * Sets the number of players.
     * 
     * @param numberOfPlayers The number of players to set.
     */
    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = Optional.of(numberOfPlayers);
    }

    /**
     * Gets the number of bots.
     * 
     * @return An `Optional` containing the number of bots.
     */
    public Optional<Integer> getNumberOfBots() {
        return this.numberOfBots;
    }

    /**
     * Sets the number of bots.
     * 
     * @param numberOfBots The number of bots to set.
     */
    public void setNumberOfBots(Integer numberOfBots) {
        this.numberOfBots = Optional.of(numberOfBots);
    }

    /**
     * Gets the manifest path.
     * 
     * @return The manifest path.
     */
    public String getManifestPath() {
        return this.manifestPath;
    }

    /**
     * Sets the manifest path.
     * 
     * @param manifestPath The manifest path to set.
     */
    public void setManifestPath(String manifestPath) {
        this.manifestPath = manifestPath;
    }
}