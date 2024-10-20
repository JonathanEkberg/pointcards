package pointcards.io.output;

import pointcards.network.INetworkClient;

/**
 * The RemoteOutput class handles remote output via network clients.
 */
public class RemoteOutput implements IOutput {
    private INetworkClient client;

    public RemoteOutput(INetworkClient client) {
        this.client = client;
    }

    /**
     * Sends a message to the remote client.
     * 
     * @param message The message to send.
     */
    @Override
    public void send(String message) {
        client.send(message);
    }
}