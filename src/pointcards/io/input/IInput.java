package pointcards.io.input;

public interface IInput {
    public String queryString(String message);

    public int queryInt(String query);

    public int queryInt(String query, int min, int max);
}
