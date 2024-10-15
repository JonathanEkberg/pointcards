package pointcards.network.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pointcards.network.INetworkClient;

public class TCPClient implements INetworkClient {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public TCPClient(ObjectInputStream in, ObjectOutputStream out) throws IOException {
        this.in = in;
        this.out = out;
    }

    public void send(String message) {
        try {
            this.out.writeObject(message);
        } catch (IOException e) {
            // TODO Update erorr handling
        }
    }

    public String receive() {
        try {
            String message = (String) this.in.readObject();
            return message;
        } catch (Exception e) {
            // TODO Update erorr handling
            return null;
        }
    }

    public void close() {
        try {
            this.in.close();
            this.out.close();
        } catch (IOException e) {
            // TODO Update erorr handling
        }
    }
}
