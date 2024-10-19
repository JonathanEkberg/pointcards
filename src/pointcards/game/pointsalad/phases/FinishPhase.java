package pointcards.game.pointsalad.phases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import pointcards.game.pointsalad.Card;
import pointcards.game.pointsalad.GameState;
import pointcards.game.pointsalad.Hand;
import pointcards.game.pointsalad.IPlayer;
import pointcards.utils.Logger;
import pointcards.game.IPhase;

public class FinishPhase implements IPhase {
    private final GameState state;

    public FinishPhase(GameState state) {
        this.state = state;
    }

    @Override
    public Optional<IPhase> run() {
        List<IPlayer> players = state.getPlayers();
        Map<IPlayer, Integer> scores = new HashMap<>();

        for (IPlayer player : players) {
            List<List<Card>> opponentVeggies = new ArrayList<>(players.size() - 1);

            for (IPlayer p : players) {
                if (p.equals(player)) {
                    continue;
                }

                opponentVeggies.add(p.getHand().getVeggieCards());
            }

            Hand hand = player.getHand();
            int score = hand.calculateScore(opponentVeggies);
            scores.put(player, score);
        }

        // Sort the map by score
        List<Map.Entry<IPlayer, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort((a, b) -> a.getValue() - b.getValue());

        // Print out the scores and make the last message announce the winner
        StringBuilder message = new StringBuilder();
        for (Map.Entry<IPlayer, Integer> entry : sortedScores) {
            String part = String.format("Player %s scored %d points\n", entry.getKey().getName(), entry.getValue());
            message.append(part);
            Logger.debug(part);
        }

        IPlayer winner = sortedScores.get(sortedScores.size() - 1).getKey();
        String part = String.format("Player %s wins!\n", winner.getName());
        Logger.debug(part);
        message.append(part);

        state.sendMessageToAllPlayers(message.toString());
        // Logger.debug(message.toString());

        return Optional.empty();
    }
}
