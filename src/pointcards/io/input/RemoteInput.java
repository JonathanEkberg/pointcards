package pointcards.io.input;

import pointcards.network.INetworkClient;

public class RemoteInput extends AbstractInput {
    private INetworkClient client;

    public RemoteInput(INetworkClient client) {
        this.client = client;
    }

    @Override
    public String queryString(String query) {
        this.client.send(query + ": ");
        return this.client.receive();
    }
}
