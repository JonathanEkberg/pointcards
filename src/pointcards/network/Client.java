package pointcards.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public Client(ObjectInputStream in, ObjectOutputStream out) throws IOException {
        this.in = in;
        this.out = out;
    }

    public void sendMessage(String message) throws IOException {
        this.out.writeObject(message);
    }

    public String receiveMessage() throws IOException, ClassNotFoundException {
        return (String) this.in.readObject();
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
    }
}
