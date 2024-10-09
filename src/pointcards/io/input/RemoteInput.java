package pointcards.io.input;

import java.io.IOException;

import pointcards.network.INetworkClient;
import pointcards.network.tcp.TCPClient;

public class RemoteInput implements IInput {
    private INetworkClient client;

    public RemoteInput(INetworkClient client) {
        this.client = client;
    }

    @Override
    public String queryString(String message) {
        this.client.send(message + ": ");
        return this.client.receive();
    }

    @Override
    public char queryChar(String message) {
        this.client.send(message + ": ");
        String response = this.client.receive();

        if (response.length() == 1) {
            return response.charAt(0);
        } else {
            return '\0';
        }
    }

    @Override
    public String queryChoice(String message, String[] choices) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int queryInt(String message) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int queryInt(String message, int min, int max) {
        // TODO Auto-generated method stub
        return 0;
    }
}
