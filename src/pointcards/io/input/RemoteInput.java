package pointcards.io.input;

import pointcards.network.INetworkClient;

/**
 * The RemoteInput class handles remote input via network clients.
 */
public class RemoteInput extends AbstractInput {
    private INetworkClient client;

    public RemoteInput(INetworkClient client) {
        this.client = client;
    }

    /**
     * Queries a string input from the user.
     * 
     * @param message The message to display to the user.
     * @return The user's input as a string.
     */
    @Override
    public String queryString(String message) {
        client.send(message);
        return client.receive();
    }
}