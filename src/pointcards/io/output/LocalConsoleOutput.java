package pointcards.io.output;

public class LocalConsoleOutput implements IOutput {
    public void send(String message) {
        System.out.println(message);
    }
}
