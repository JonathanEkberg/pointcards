package pointcards.network;

public interface INetworkClient {
    public String receive();

    public void send(String message);
}
