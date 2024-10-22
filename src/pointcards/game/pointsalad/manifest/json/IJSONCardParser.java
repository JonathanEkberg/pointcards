package pointcards.game.pointsalad.manifest.json;

import java.util.List;

import pointcards.game.pointsalad.concepts.Card;

/**
 * The `IJSONCardParser` interface defines the methods for parsing cards from a
 * JSON manifest.
 * Implementations of this interface should provide the logic for extracting
 * card information
 * based on the specific format of the manifest.
 */
public interface IJSONCardParser {
    /**
     * Parses and returns the list of cards from the JSON manifest.
     * 
     * @return The list of cards.
     */
    List<Card> parseCards();
}
