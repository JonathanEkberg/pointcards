package pointcards.game;

import pointcards.io.input.IInput;
import pointcards.io.output.IOutput;

/**
 * The BasePlayer class represents a player in the game.
 * It extends the Participant class and includes additional
 * attributes for the player's name, input, and output.
 */
public class BasePlayer extends Participant {
    private final String name;
    final IInput input;
    final IOutput output;

    /**
     * Constructs a new BasePlayer with the specified name, input, and output.
     *
     * @param name   the name of the player
     * @param input  the input interface for the player
     * @param output the output interface for the player
     */
    public BasePlayer(String name, final IInput input, final IOutput output) {
        this.name = name;
        this.input = input;
        this.output = output;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the input interface for the player.
     *
     * @return the input interface for the player
     */
    public IInput getInput() {
        return input;
    }

    /**
     * Returns the output interface for the player.
     *
     * @return the output interface for the player
     */
    public IOutput getOutput() {
        return output;
    }
}
