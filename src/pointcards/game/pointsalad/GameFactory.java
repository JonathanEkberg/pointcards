package pointcards.game.pointsalad;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import pointcards.game.BaseBot;
import pointcards.game.BasePlayer;
import pointcards.game.IGameFactory;
import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Deck;
import pointcards.game.pointsalad.concepts.Hand;
import pointcards.game.pointsalad.concepts.Veggie;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.game.pointsalad.participants.Bot;
import pointcards.game.pointsalad.participants.HumanPlayer;
import pointcards.io.JSONFileReader;
import pointcards.io.input.IInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;
import pointcards.utils.Randomizer;

/**
 * The {@code GameFactory} class is responsible for creating instances of the
 * Point Salad game. It reads the game manifest, sets up game settings, and
 * creates game instances with the specified players and bots.
 */
public class GameFactory implements IGameFactory {
    private final List<Card> cards;

    /**
     * Constructs a new {@code GameFactory} with the specified manifest path.
     * 
     * @param manifestPath The path to the game manifest file.
     * @throws FileNotFoundException    If the manifest file is not found.
     * @throws IllegalArgumentException If the manifest is invalid.
     */
    public GameFactory(String manifestPath) throws FileNotFoundException {
        JSONFileReader reader = new JSONFileReader();
        JSONObject manifest = reader.readJSONFile(manifestPath);
        JSONManifestParser manifestParser = new JSONManifestParser(manifest);
        try {
            this.cards = manifestParser.getCards();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid manifest provided. " + e.getMessage());
        }
    }

    /**
     * Sets the game settings based on the provided optional settings and input.
     * 
     * @param settings The optional game settings.
     * @param input    The input handler for querying user input.
     * @return The configured game settings.
     */
    public GameSettings setGameSettings(final OptionalGameSettings settings, final IInput input) {
        Optional<Integer> numberOfPlayers = settings.getNumberOfPlayers();
        if (numberOfPlayers.isEmpty() || numberOfPlayers.get() < 0 || numberOfPlayers.get() > 6) {
            int players = input.queryInt("Enter the number of players", 0, 6);
            settings.setNumberOfPlayers(players);
        }

        if (settings.getNumberOfPlayers().get() == 6) {
            settings.setNumberOfBots(0);
            return new GameSettings(settings);
        }

        if (settings.getNumberOfBots().isEmpty() || settings.getNumberOfBots().get() < 0
                || settings.getNumberOfBots().get() > 6) {
            var bots = input.queryInt("Enter the number of bots", 0, 6 - settings.getNumberOfPlayers().get());
            settings.setNumberOfBots(bots);
        }
        return new GameSettings(settings);
    }

    /**
     * Creates a new instance of the Point Salad game with the specified players.
     * 
     * @param players The list of players participating in the game.
     * @return A new instance of the Point Salad game.
     */
    public PointSaladGame createGame(final List<BasePlayer> players) {
        return this.createGame(players, List.of());
    }

    /**
     * Maps the number of participants to the corresponding number of cards from
     * each veggie type.
     * 
     * @param participantCount The number of participants in the game.
     * @return The list of cards for the specified number of participants.
     */
    protected List<Card> participantCountToCards(int participantCount) {
        int cardFromEachVeggie = -1;

        switch (participantCount) {
            case 2:
                cardFromEachVeggie = 6;
                break;
            case 3:
                cardFromEachVeggie = 9;
                break;
            case 4:
                cardFromEachVeggie = 12;
                break;
            case 5:
                cardFromEachVeggie = 15;
                break;
            case 6:
                cardFromEachVeggie = 18;
                break;
            default:
                throw new IllegalArgumentException("Player count must be between 2 and 6");
        }

        HashMap<Veggie, Card> veggieToCard = new HashMap<>();

        for (Card card : this.cards) {
            veggieToCard.put(card.getVeggie(), card);
        }

        List<Card> cards = new ArrayList<>();

        for (Veggie veggie : Veggie.values()) {
            for (int i = 0; i < cardFromEachVeggie; i++) {
                cards.add(veggieToCard.get(veggie));
            }
        }

        List<Card> shuffled = new ArrayList<>(cards);
        Randomizer.shuffle(shuffled);
        return shuffled;
    }

    public PointSaladGame createGame(final List<BasePlayer> basePlayers, final List<BaseBot> baseBots) {
        if (basePlayers.size() + baseBots.size() < 2 || basePlayers.size() + baseBots.size() > 6) {
            throw new IllegalArgumentException("Player + bot count must be between 2 and 6");
        }

        List<HumanPlayer> humanPlayers = List
                .of(basePlayers.stream().map(player -> new HumanPlayer(player, new Hand()))
                        .toArray(HumanPlayer[]::new));
        List<Bot> bots = List
                .of(baseBots.stream().map(bot -> new Bot(bot.getName(), new Hand())).toArray(Bot[]::new));
        // Create a deck with all the cards from the manifest.
        List<Card> gameCards = this.participantCountToCards(basePlayers.size() + baseBots.size());
        Deck gameDeck = new Deck(gameCards);

        return new PointSaladGame(new GameState(humanPlayers, bots, gameDeck));
    }
}
