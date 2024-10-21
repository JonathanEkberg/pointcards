package pointcards.game;

import java.util.List;

import pointcards.io.input.IInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;

/**
 * The {@code IGameFactory} interface defines the methods required to create
 * game instances and configure game settings.
 * Implementations of this interface are responsible for converting optional
 * game settings to mandatory settings and
 * creating game instances with specified players and bots.
 */
public interface IGameFactory {
    /**
     * Converts optional game settings to mandatory settings with the correct values
     * for the game mode.
     * For example, having 2-6 players for Point Salad or 1-4 players for Point
     * City, etc.
     * 
     * @param settings The settings object to mutate.
     * @param input    Input from which to query the user for desired values.
     * @return The mutated settings object.
     */
    public GameSettings setGameSettings(OptionalGameSettings settings, IInput input);

    /**
     * Creates a game instance with the specified list of players.
     * 
     * @param players The list of players participating in the game.
     * @return The created game instance.
     */
    public IGame createGame(List<BasePlayer> players);

    /**
     * Creates a game instance with the specified list of players and bots.
     * 
     * @param players The list of players participating in the game.
     * @param bots    The list of bots participating in the game.
     * @return The created game instance.
     */
    public IGame createGame(List<BasePlayer> players, List<BaseBot> bots);
}
