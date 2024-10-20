package pointcards.settings;

/**
 * The `ProgramSettings` class serves as a base class for common settings used
 * by both server and client.
 * It includes basic configurations such as server status, hostname, and port.
 */
public class ProgramSettings {
    protected final boolean isServer;
    protected final String hostname;
    protected final int port;

    /**
     * Constructs a new `ProgramSettings` instance.
     * 
     * @param isServer Indicates if the settings are for a server.
     * @param hostname The hostname for the server or client.
     * @param port     The port number for the server or client.
     */
    public ProgramSettings(boolean isServer, String hostname, int port) {
        this.isServer = isServer;
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Returns a string representation of the `ProgramSettings`.
     * 
     * @return A string representation of the settings.
     */
    public String toString() {
        return "ProgramSettings{" +
                "isServer=" + isServer +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                '}';
    }

    /**
     * Gets the server status.
     * 
     * @return `true` if the settings are for a server, otherwise `false`.
     */
    public boolean getIsServer() {
        return isServer;
    }

    /**
     * Gets the hostname.
     * 
     * @return The hostname.
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Gets the port number.
     * 
     * @return The port number.
     */
    public int getPort() {
        return port;
    }
}