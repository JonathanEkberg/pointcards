package pointcards.exceptions;

public class UnimplementedGameModeException extends Exception {
    public UnimplementedGameModeException() {
        super("Game mode not implemented");
    }
}
