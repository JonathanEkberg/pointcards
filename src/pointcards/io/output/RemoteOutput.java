package pointcards.io.output;

import pointcards.network.INetworkClient;

public class RemoteOutput implements IOutput {
    private INetworkClient client;

    public RemoteOutput(INetworkClient client) {
        this.client = client;
    }

    public void send(String message) {
        client.send(message);
    }
}
