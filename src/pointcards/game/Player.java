package pointcards.game;

import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;

public class Player {
    IInput input;
    IOutput output;

    public Player(IInput input, IOutput output) {
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
