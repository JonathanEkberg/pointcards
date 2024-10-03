package pointcards.io.input;

public interface IInput {
    public int queryInt(String message);

    public int queryInt(String message, int min, int max);

    public String queryString(String message);

    public char queryChar(String message);

    public String queryChoice(String message, String[] choices);
}
