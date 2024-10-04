package pointcards.settings;

public class GameSettings extends ProgramSettings {
    private int numberOfPlayers, numberOfBots;

    public GameSettings(OptionalGameSettings current) {
        super(current.isServer, current.hostname, current.port);
        this.numberOfPlayers = current.numberOfPlayers.orElseThrow();
        this.numberOfBots = current.numberOfBots.orElseThrow();
    }

    public GameSettings(boolean isServer, String hostname, int port, int numberOfPlayers,
            int numberOfBots) {
        super(isServer, hostname, port);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfBots = numberOfBots;
    }

    public String toString() {
        return "Values{" +
                "isServer=" + isServer +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", numberOfPlayers=" + numberOfPlayers +
                ", numberOfBots=" + numberOfBots +
                '}';
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public int getNumberOfBots() {
        return this.numberOfBots;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setNumberOfBots(Integer numberOfBots) {
        this.numberOfBots = numberOfBots;
    }
}