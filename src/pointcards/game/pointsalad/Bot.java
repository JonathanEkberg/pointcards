package pointcards.game.pointsalad;

import java.util.ArrayList;
import java.util.List;

import pointcards.game.BaseBot;
import pointcards.utils.Logger;
import pointcards.utils.Randomizer;

public class Bot extends BaseBot implements IPlayer {
    private final Hand hand;

    public Bot(String name, Hand hand) {
        super(name);
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

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

    private void playTakeCriteriaCard(GameState state) {
        Deck deck = state.getDecks().getDeck(Randomizer.nextInt(3));

        while (deck.size() == 0) {
            deck = state.getDecks().getDeck(Randomizer.nextInt(3));
        }

        Card card = deck.takeCard().get();
        hand.addCriteriasCard(card);
    }

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
