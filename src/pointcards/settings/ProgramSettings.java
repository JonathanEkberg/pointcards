package pointcards.settings;

public class ProgramSettings {
    protected final boolean isServer;
    protected final String hostname;
    protected final int port;

    public ProgramSettings(boolean isServer, String hostname, int port) {
        this.isServer = isServer;
        this.hostname = hostname;
        this.port = port;
    }

    public String toString() {
        return "ProgramSettings{" +
                "isServer=" + isServer +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                '}';
    }

    public boolean getIsServer() {
        return isServer;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }
}