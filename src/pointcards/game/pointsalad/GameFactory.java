package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import pointcards.game.BaseBot;
import pointcards.game.BasePlayer;
import pointcards.game.IGameFactory;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.io.JSONFileReader;
import pointcards.io.input.IInput;
import pointcards.settings.GameSettings;
import pointcards.settings.OptionalGameSettings;

public class GameFactory implements IGameFactory {
    private final List<Card> cards;

    public GameFactory(String manifestPath) throws Exception {
        try {
            JSONFileReader reader = new JSONFileReader(manifestPath);
            System.out.println("Reading manifest file from " + manifestPath);
            JSONObject manifest = reader.readJsonFile();
            JSONManifestParser manifestParser = new JSONManifestParser(manifest);
            this.cards = manifestParser.getCards();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to read manifest file");
        }
    }

    public GameSettings setGameSettings(final OptionalGameSettings settings, final IInput input) {
        if (settings.getNumberOfPlayers().isEmpty() || settings.getNumberOfPlayers().get() < 1
                || settings.getNumberOfPlayers().get() > 6) {
            var players = input.queryInt("Enter the number of players", 1,
                    Integer.MAX_VALUE);
            settings.setNumberOfPlayers(players);
        }

        if (settings.getNumberOfBots().isEmpty() || settings.getNumberOfBots().get() < 0
                || settings.getNumberOfBots().get() > 5) {
            var bots = input.queryInt("Enter the number of bots", 0, Integer.MAX_VALUE);
            settings.setNumberOfBots(bots);
        }
        return new GameSettings(settings);
    }

    public PointSaladGame createGame(final List<BasePlayer> players) {
        return this.createGame(players, List.of());
    }

    private List<Card> participantCountToCards(int participantCount) {
        int cardFromEachVeggie = 6;
        switch (participantCount) {
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
        Collections.shuffle(shuffled);
        return shuffled;
    }

    public PointSaladGame createGame(final List<BasePlayer> players, final List<BaseBot> bots) {
        List<Player> psPlayers = List
                .of(players.stream().map(player -> new Player(player)).toArray(Player[]::new));
        List<Bot> psBots = List
                .of(bots.stream().map(bot -> new Bot()).toArray(Bot[]::new));
        // Create a deck with all the cards from the manifest.
        List<Card> gameCards = this.participantCountToCards(players.size() + bots.size());
        Deck gameDeck = new Deck(gameCards);

        return new PointSaladGame(new GameState(psPlayers, psBots, gameDeck));
    }
}
