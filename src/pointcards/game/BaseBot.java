package pointcards.game;

public class BaseBot extends Participant {
    private final String name;

    public BaseBot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
