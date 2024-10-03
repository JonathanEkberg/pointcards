package pointcards.args;

import java.util.Optional;

import pointcards.game.GameMode;

public class OptionalGameSettings extends ProgramSettings {
    Optional<GameMode> gameMode;
    Optional<Integer> numberOfPlayers;
    Optional<Integer> numberOfBots;

    public OptionalGameSettings(GameMode gameMode, boolean isServer, String hostname, int port, Integer numberOfPlayers,
            Integer numberOfBots) {
        super(isServer, hostname, port);
        this.gameMode = Optional.ofNullable(gameMode);
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

    public Optional<GameMode> getGameMode() {
        return this.gameMode;
    }

    public Optional<Integer> getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public Optional<Integer> getNumberOfBots() {
        return this.numberOfBots;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = Optional.of(gameMode);
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = Optional.of(numberOfPlayers);
    }

    public void setNumberOfBots(Integer numberOfBots) {
        this.numberOfBots = Optional.of(numberOfBots);
    }
}