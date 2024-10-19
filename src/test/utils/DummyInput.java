package test.utils;

import pointcards.io.input.IInput;

public class DummyInput implements IInput {
    @Override
    public int queryInt(String query) {
        return 0;
    }

    @Override
    public int queryInt(String query, int min, int max) {
        return 0;
    }

    @Override
    public String queryString(String message) {
        return null;
    }
}
