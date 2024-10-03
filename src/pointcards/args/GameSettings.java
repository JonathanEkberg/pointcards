package pointcards.args;

import pointcards.game.GameMode;

public class GameSettings extends ProgramSettings {
    private GameMode gameMode;
    private int numberOfPlayers, numberOfBots;

    public GameSettings(OptionalGameSettings current) {
        super(current.isServer, current.hostname, current.port);
        this.gameMode = current.gameMode.orElseThrow();
        this.numberOfPlayers = current.numberOfPlayers.orElseThrow();
        this.numberOfBots = current.numberOfBots.orElseThrow();
    }

    public GameSettings(GameMode gameMode, boolean isServer, String hostname, int port, int numberOfPlayers,
            int numberOfBots) {
        super(isServer, hostname, port);
        this.gameMode = gameMode;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfBots = numberOfBots;
    }

    public String toString() {
        return "Values{" +
                "gameMode=" + gameMode +
                ", isServer=" + isServer +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", numberOfPlayers=" + numberOfPlayers +
                ", numberOfBots=" + numberOfBots +
                '}';
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public int getNumberOfBots() {
        return this.numberOfBots;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setNumberOfBots(Integer numberOfBots) {
        this.numberOfBots = numberOfBots;
    }
}