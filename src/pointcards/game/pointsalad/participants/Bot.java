package pointcards.game.pointsalad.participants;

import java.util.ArrayList;
import java.util.List;

import pointcards.game.BaseBot;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.concepts.Card;
import pointcards.game.pointsalad.concepts.Deck;
import pointcards.game.pointsalad.concepts.Hand;
import pointcards.game.pointsalad.concepts.Market;
import pointcards.utils.Logger;
import pointcards.utils.Randomizer;

/**
 * The {@code Bot} class represents a bot player in the Point Salad game.
 * It extends the {@link pointcards.game.BaseBot} class and implements the
 * {@link pointcards.game.pointsalad.participants.IPlayer} interface.
 * The bot can take criteria cards or veggie cards during its turn based on the
 * game state.
 */
public class Bot extends BaseBot implements IPlayer {
    private final Hand hand;

    /**
     * Constructs a new {@code Bot} with the specified name and hand.
     * 
     * @param name The name of the bot.
     * @param hand The hand of the bot.
     */
    public Bot(String name, Hand hand) {
        super(name);
        this.hand = hand;
    }

    /**
     * Gets the hand of the bot.
     * 
     * @return The hand of the bot.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Executes the bot's turn. The bot will either take criteria cards or veggie
     * cards based on the game state and random decisions.
     * 
     * @param state The current game state.
     * @throws IllegalStateException if there are no cards in the market or decks.
     */
    public void doTurn(GameState state) {
        if (state.getMarket().size() == 0 && state.getDecks().allEmptyDecks()) {
            throw new IllegalStateException("Illegal state: No cards in market or decks so cannot play");
        }

        if (state.getDecks().allEmptyDecks()) {
            Logger.debug("All decks are empty, bot is taking veggie cards");
            playTakeVeggieCard(state);
            return;
        }

        if (Randomizer.nextInt(2) == 0) {
            Logger.debug("Bot is taking criteria card");
            playTakeCriteriaCard(state);
        } else {
            Logger.debug("Bot is taking veggie cards");
            playTakeVeggieCard(state);
        }
    }

    /**
     * The bot takes a criteria card from a random non-empty deck and adds it to
     * its hand.
     * 
     * @param state The current game state.
     */
    private void playTakeCriteriaCard(GameState state) {
        Deck deck = state.getDecks().getDeck(Randomizer.nextInt(3));

        while (deck.size() == 0) {
            deck = state.getDecks().getDeck(Randomizer.nextInt(3));
        }

        Card card = deck.takeCard().get();
        hand.addCriteriasCard(card);
    }

    /**
     * The bot takes two veggie cards from the market and adds them to its hand.
     * 
     * @param state The current game state.
     */
    private void playTakeVeggieCard(GameState state) {
        List<Card> cards = new ArrayList<>();
        Market market = state.getMarket();

        while (cards.size() < 2) {
            int column = Randomizer.nextInt(market.getColumns());
            int row = Randomizer.nextInt(market.getRows());

            if (!market.hasCard(column, row)) {
                continue;
            }

            Card card = market.takeCard(column, row);
            cards.add(card);
        }

        for (Card card : cards) {
            hand.addVeggieCard(card);
        }

        if (!state.allDecksEmpty()) {
            state.refillMarketCards();
        }
    }
}
