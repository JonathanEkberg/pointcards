package pointcards.io.input;

public abstract class AbstractInput implements IInput {
    public abstract String queryString(String message);

    private int queryInt(String query, String error) {
        try {
            String formattedQuery;

            if (error != null) {
                formattedQuery = String.format("%s\n%s", query, error);
            } else {
                formattedQuery = query;
            }

            String result = this.queryString(formattedQuery);
            int parsed = Integer.parseInt(result);
            return parsed;
        } catch (NumberFormatException e) {
            if (error != null) {
                return queryInt(query, error);
            }

            return queryInt(query, ": Invalid input. Please enter a valid integer.");
        }
    }

    public int queryInt(String query) {
        return this.queryInt(query, null);
    }

    public int queryInt(String query, int min, int max) {
        int result = this.queryInt(query, null);

        if (result < min) {
            return queryInt(query,
                    String.format("Value must be an integer greater than or equal to %d.", min));
        }

        if (result > max) {
            return queryInt(query,
                    String.format("Value must be an integer less than or equal to %d.", max));
        }

        return result;
    }
}
