package pointcards.game;

import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;

public class BasePlayer extends Participant {
    private final String name;
    final IInput input;
    final IOutput output;

    public BasePlayer(String name, final IInput input, final IOutput output) {
        this.name = name;
        this.input = input;
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public IInput getInput() {
        return input;
    }

    public IOutput getOutput() {
        return output;
    }
}
