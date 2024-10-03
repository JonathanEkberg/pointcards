package pointcards.args;

public class ProgramSettings {
    protected boolean isServer;
    protected String hostname;
    protected int port;

    public ProgramSettings(boolean isServer, String hostname, int port) {
        this.isServer = isServer;
        this.hostname = hostname;
        this.port = port;
    }

    public String toString() {
        return "Values{" +
                "isServer=" + isServer +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                '}';
    }

    public boolean getIsServer() {
        return isServer;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public void setIsServer(boolean isServer) {
        this.isServer = isServer;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }
}