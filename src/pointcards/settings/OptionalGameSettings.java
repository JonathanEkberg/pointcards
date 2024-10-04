package pointcards.settings;

import java.util.Optional;

public class OptionalGameSettings extends ProgramSettings {
    Optional<Integer> numberOfPlayers;
    Optional<Integer> numberOfBots;

    public OptionalGameSettings(boolean isServer, String hostname, int port, Integer numberOfPlayers,
            Integer numberOfBots) {
        super(isServer, hostname, port);
        this.numberOfPlayers = Optional.ofNullable(numberOfPlayers);
        this.numberOfBots = Optional.ofNullable(numberOfBots);
    }

    public String toString() {
        return "Values{" +
                "isServer=" + isServer +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", numberOfPlayers=" + this.numberOfPlayers +
                ", numberOfBots=" + this.numberOfBots +
                '}';
    }

    public Optional<Integer> getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public Optional<Integer> getNumberOfBots() {
        return this.numberOfBots;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = Optional.of(numberOfPlayers);
    }

    public void setNumberOfBots(Integer numberOfBots) {
        this.numberOfBots = Optional.of(numberOfBots);
    }
}