package pointcards.settings;

import java.util.Optional;

public class OptionalGameSettings extends ProgramSettings {
    String manifestPath;
    Optional<Integer> numberOfPlayers;
    Optional<Integer> numberOfBots;

    public OptionalGameSettings(boolean isServer, String hostname, int port, Integer numberOfPlayers,
            Integer numberOfBots, String manifestPath) {
        super(isServer, hostname, port);
        this.numberOfPlayers = Optional.ofNullable(numberOfPlayers);
        this.numberOfBots = Optional.ofNullable(numberOfBots);
        this.manifestPath = manifestPath;
    }

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

    public Optional<Integer> getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public Optional<Integer> getNumberOfBots() {
        return this.numberOfBots;
    }

    // public Optional<String> getManifestPath() {
    public String getManifestPath() {
        return this.manifestPath;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = Optional.of(numberOfPlayers);
    }

    public void setNumberOfBots(Integer numberOfBots) {
        this.numberOfBots = Optional.of(numberOfBots);
    }

    public void setManifestPath(String manifestPath) {
        this.manifestPath = manifestPath;
    }
    // public void setManifestPath(String manifestPath) {
    // this.manifestPath = Optional.of(manifestPath);
    // }
}