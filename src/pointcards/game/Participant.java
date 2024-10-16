package pointcards.game;

public class Participant {
    private static int nextId = 0;
    public final int id;

    public Participant() {
        this.id = ++Participant.nextId;
    }
}
