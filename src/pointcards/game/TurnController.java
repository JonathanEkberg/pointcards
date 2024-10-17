package pointcards.game;

import java.util.List;

import pointcards.utils.Logger;

public class TurnController {
    private int turn = 0;
    private final Participant[] turners;

    public TurnController(List<Participant> turners) {
        Logger.error(turners.size());
        this.turners = new Participant[turners.size()];

        for (int i = 0; i < turners.size(); i++) {
            this.turners[i] = turners.get(i);
        }
    }

    public Participant getTurn() {
        return this.turners[this.turn];
    }

    public void next() {
        this.turn = (this.turn + 1) % this.turners.length;
    }
}
