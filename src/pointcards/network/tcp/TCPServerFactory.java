package pointcards.network.tcp;

import pointcards.network.INetworkServer;
import pointcards.network.IServerFactory;

/**
 * The `TCPServerFactory` class implements the `IServerFactory` interface for
 * creating TCPServer instances.
 */
public class TCPServerFactory implements IServerFactory {
    /**
     * Creates a new instance of a TCP server.
     * 
     * @return A new instance of a TCP server.
     */
    @Override
    public INetworkServer createServer() {
        return new TCPServer();
    }
}