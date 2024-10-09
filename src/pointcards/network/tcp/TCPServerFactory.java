package pointcards.network.tcp;

import pointcards.network.INetworkServer;
import pointcards.network.IServerFactory;

public class TCPServerFactory implements IServerFactory {
    public INetworkServer createServer() {
        return new TCPServer();
    }
}
