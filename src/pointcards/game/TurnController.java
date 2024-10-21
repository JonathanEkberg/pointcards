package pointcards.game;

import java.util.List;

/**
 * The TurnController class manages the turns of participants in the point cards
 * game.
 * It ensures that each participant gets their turn in a round-robin fashion.
 * 
 * <p>
 * This class is initialized with a list of participants and provides methods
 * to get the current participant's turn and to move to the next turn.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * List<Participant> participants = Arrays.asList(new Participant(), new Participant());
 * TurnController turnController = new TurnController(participants);
 * Participant currentTurn = turnController.getTurn();
 * turnController.next();
 * </pre>
 */
public class TurnController {
    private int turn = 0;
    private final Participant[] turners;

    /**
     * Constructs a TurnController with the specified list of participants.
     * 
     * @param turners the list of participants
     * @throws IllegalArgumentException if the list of participants is empty
     */
    public TurnController(List<Participant> turners) {
        assert turners.size() > 0 : "No turners provided";
        this.turners = new Participant[turners.size()];

        for (int i = 0; i < turners.size(); i++) {
            this.turners[i] = turners.get(i);
        }
    }

    /**
     * Returns the participant whose turn it currently is.
     * 
     * @return the current participant
     */
    public Participant getTurn() {
        return this.turners[this.turn];
    }

    /**
     * Advances to the next participant's turn.
     */
    public void next() {
        this.turn = (this.turn + 1) % this.turners.length;
    }
}
