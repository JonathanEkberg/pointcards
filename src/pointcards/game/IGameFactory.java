package pointcards.game;

import java.util.List;

import pointcards.io.input.IInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;

public interface IGameFactory {
    /**
     * Convert game settings with optional settings to settings with none optional
     * and with the correct amount for the game mode. For example having 2-6 players
     * for Point Salad or 1-4 players for Point City etc.
     * 
     * @param settings The settings object to mutate
     * @param input    Input from which to query the user for desired values
     * @return The mutated settings object
     */
    public GameSettings setGameSettings(OptionalGameSettings settings, IInput input);

    public IGame createGame(List<BasePlayer> players);

    public IGame createGame(List<BasePlayer> players, List<BaseBot> bots);
}
