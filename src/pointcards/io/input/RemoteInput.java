package pointcards.io.input;

import java.io.IOException;

import pointcards.network.Client;

public class RemoteInput implements IInput {
    private Client client;

    public RemoteInput(Client client) {
        this.client = client;
    }

    @Override
    public String queryString(String message) {
        try {
            this.client.sendMessage(message + ": ");
            return this.client.receiveMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public char queryChar(String message) {
        try {
            this.client.sendMessage(message + ": ");
            String response = this.client.receiveMessage();

            if (response.length() == 1) {
                return response.charAt(0);
            } else {
                return '\0';
            }
        } catch (IOException e) {
            e.printStackTrace();
            return '\0';
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
