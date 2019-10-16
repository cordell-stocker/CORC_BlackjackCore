package blackjackcore.model.player;

import corc.standard.Card;
import corc.standard.Cardset;

/**
 * A player with basic functionality needed
 * for playing a game of KElevens.
 */
public class KElevensPlayer extends PlayerWithScoreAndTokens {

    private final Cardset CARDSET;

    /**
     * Creates a player with basic functionality needed
     * for playing a game of KElevens.
     *
     * @param name    the name of this player.
     * @param cardset the {@link Cardset} this player uses.
     */
    public KElevensPlayer(String name, Cardset cardset) {
        super(name, cardset);
        this.CARDSET = cardset;
    }

    /**
     * Adds the given {@link Card} to this player's {@link Cardset}.
     *
     * @param card the Card to be added to this player's Cardset.
     */
    public void addCard(Card card) {
        this.CARDSET.addCard(card);
    }

    /**
     * Removes all {@link Card}s from this player's {@link Cardset}.
     */
    public void clearCards() {
        this.CARDSET.clear();
    }

    /**
     * Returns the total number of {@link Card}s in this player's {@link Cardset}.
     *
     * @return number of Cards in the Cardset.
     */
    public int getCardCount() {
        return this.CARDSET.size();
    }
}
