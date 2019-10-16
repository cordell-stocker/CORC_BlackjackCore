package blackjackcore.model.player;

import corc.standard.Cardset;

/**
 * A player with basic functionality needed
 * for playing a game of High Card.
 */
public class HighCardPlayer extends KElevensPlayer {

    /**
     * Creates a player with basic functionality needed
     * for playing a game of High Card.
     *
     * @param name    the name of this player.
     * @param cardset the {@link Cardset} this player uses.
     */
    public HighCardPlayer(String name, Cardset cardset) {
        super(name, cardset);
    }

}
