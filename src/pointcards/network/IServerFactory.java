package pointcards.network;

/**
 * The `IServerFactory` interface defines a factory for creating instances of
 * network servers.
 */
public interface IServerFactory {
    /**
     * Creates a new instance of a network server.
     * 
     * @return A new instance of a network server.
     */
    public INetworkServer createServer();
}