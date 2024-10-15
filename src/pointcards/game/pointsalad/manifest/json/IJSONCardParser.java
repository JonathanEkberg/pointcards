package pointcards.game.pointsalad.manifest.json;

import java.util.List;

import pointcards.game.pointsalad.Card;

public interface IJSONCardParser {
    List<Card> parseCards();
}
