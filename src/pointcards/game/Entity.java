package pointcards.game;

public class Entity {
    private static int nextId = 0;
    public final int id;

    public Entity() {
        this.id = ++Entity.nextId;
    }
}
