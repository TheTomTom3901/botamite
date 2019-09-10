package io.github.thetomtom3901.botamite;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Botamite implements Bot {

    private int dynamitesUsed = 0;

    public Move makeMove(Gamestate gamestate) {
        final Move[] moves = {Move.R, Move.P, Move.S};

        final int roundSize = gamestate.getRounds().size();
        final List<Round> allRounds = gamestate.getRounds();

        if(roundSize > 0) {
            final Round lastRound = allRounds.get(roundSize - 1);
            if (lastRound.getP1() == lastRound.getP2()) {
                if(dynamitesUsed < 100) {
                    dynamitesUsed++;
                    return Move.D;
                }
            }

            if(roundSize > 2) {
                if(allRounds.subList(roundSize - 2, roundSize)
                        .stream().allMatch(round -> round.getP2() == Move.D)) {
//                if(allRounds.get(roundSize - 1).getP1() != Move.W) {
//                    return Move.W;
//                }
                    return Move.W;
                }
            }
        }

        return moves[ThreadLocalRandom.current().nextInt(moves.length)];
    }

}
