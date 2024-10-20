package test.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import pointcards.game.BasePlayer;
import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.Hand;
import pointcards.game.pointsalad.HumanPlayer;
import pointcards.game.pointsalad.Veggie;
import pointcards.game.pointsalad.criteria.ICriteria;
import pointcards.game.pointsalad.criteria.criterias.CriteriaMost;
import pointcards.game.pointsalad.manifest.json.JSONManifestParser;
import pointcards.io.JSONFileReader;

public class TestUtils {
    public static JSONManifestParser getManifestParser(String manifestPath) throws Exception {
        JSONFileReader reader = new JSONFileReader(manifestPath);
        JSONObject manifest = reader.readJsonFile();
        return new JSONManifestParser(manifest);
    }

    public static List<BasePlayer> createBasePlayers(int numPlayers) {
        List<BasePlayer> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new BasePlayer("Player " + i, new DummyInput(), new DummyOutput()));
        }
        return players;
    }

    public static List<HumanPlayer> createHumanPlayers(int numPlayers) {
        List<HumanPlayer> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new HumanPlayer("Player " + i, new DummyInput(), new DummyOutput(), new Hand()));
        }
        return players;
    }

    public static Card[] createHand(Veggie type, int amount) {
        ICriteria anyCriteria = new CriteriaMost(type, amount);
        Card[] hand = new Card[amount];

        for (int i = 0; i < amount; i++) {
            hand[i] = new Card(type, anyCriteria);
        }

        return hand;
    }
}