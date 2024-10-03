package pointcards.io.output;

import java.io.IOException;

import pointcards.network.Client;

public class RemoteOutput implements IOutput {
    private Client client;

    public RemoteOutput(Client client) {
        this.client = client;
    }

    public void send(String message) {
        try {
            client.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
