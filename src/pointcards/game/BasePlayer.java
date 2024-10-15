package pointcards.game;

import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;

public class BasePlayer extends Entity {
    final IInput input;
    final IOutput output;

    public BasePlayer(final IInput input, final IOutput output) {
        this.input = input;
        this.output = output;
    }

    public IInput getInput() {
        return input;
    }

    public IOutput getOutput() {
        return output;
    }
}
