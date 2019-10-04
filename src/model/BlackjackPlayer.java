package model;

import standard.Card;
import standard.Cardset;
import standard.Deck;

@SuppressWarnings("unused")
public abstract class BlackjackPlayer<C extends AbstractBlackjackController> extends PlayerWithScoreAndTokens {

    private int SCORE = 0;
    private int TOKENS = 0;
    private final Cardset CARDSET;

    public BlackjackPlayer(String name, Cardset cardset) {
        super(name, cardset);
        this.CARDSET = cardset;
    }

    protected void bindBlackjackHand(AbstractBlackjackHand hand) {
        this.CARDSET.bind(hand.getCards());
    }

    /**
     * For student to implement.
     * <p>
     * SHOULD prevent the player from playing (end turn) once
     * their score goes over 21.
     * <p>
     * MUST contain all logic needed for hitting or staying
     *
     * @param controller the game controller.
     * @param deck       the deck used in the game.
     */
    public abstract void takeTurn(C controller, Deck deck);

    /**
     * For the student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#addCard(Card)} method.
     * SHOULD update this player's score.
     *
     * @param card the Card to be added to this player's hand.
     */
    @Override
    public abstract void addCard(Card card);

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#clearCards()} method.
     * SHOULD set this player's score back to 0.
     */
    @Override
    public abstract void clearCards();

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#getCardCount()} method.
     *
     * @return number of Cards in the hand.
     */
    @Override
    public abstract int getCardCount();

    /**
     * For student to implement.
     * <p>
     * MUST remove tokens, and return the amount removed.
     *
     * @return the amount this player is bidding.
     */
    public abstract int bid(C controller);

}
