package pointcards.game;

import java.util.List;

import pointcards.args.GameSettings;
import pointcards.args.OptionalGameSettings;
import pointcards.io.input.IInput;

public interface IGame {
    public GameSettings setGameSettings(OptionalGameSettings settings, IInput input);

    public void init(List<Player> players);
}
