package pointcards.network;

import java.util.List;

public interface INetworkServer {
    public void start(int port);

    public List<INetworkClient> connectClients(int numberOfClients);
}
