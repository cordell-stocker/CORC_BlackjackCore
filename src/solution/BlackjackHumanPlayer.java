package solution;

import model.BlackjackController;
import model.BlackjackPlayer;
import observablestandard.Deck;
import standard.Card;

@SuppressWarnings("WeakerAccess")
public class BlackjackHumanPlayer extends BlackjackPlayer {

    private final BlackjackHand hand;
    private BlackjackHumanPlayer player = this;

    public BlackjackHumanPlayer() {
        super("You");
        this.hand = new BlackjackHand();
        this.bindHand(this.hand);
    }

    /**
     * For student to implement.
     * <p>
     * SHOULD prevent the player from playing (end turn) once
     * their score goes over 21.
     * <p>
     * MUST call the {@link BlackjackController#getActionClicked()}
     * method to determine if the player wants to "HIT"
     * (draw another Card) or "STAY" (end turn).
     *
     * @param controller the game controller.
     * @param deck       the deck used in the game.
     */
    @Override
    public void takeTurn(BlackjackController controller, Deck deck) {
        boolean playing = true;
        while (playing) {
            String action = controller.getActionClicked();
            if (action.equals("HIT")) {
                Card card = deck.drawCard();
                this.addCard(card);
            } else if (action.equals("STAY")) {
                playing = false;
            }
            if (this.getScore() >= 21) {
                playing = false;
            }
        }
    }

    /**
     * For the student to implement.
     * <p>
     * MUST wrap the {@link BlackjackHand#addCard(Card)} method.
     * SHOULD update this player's score.
     *
     * @param card the Card to be added to this player's hand.
     */
    @Override
    public void addCard(Card card) {
        this.hand.addCard(card);
        this.setScore(this.hand.getHandScore());
    }

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link BlackjackHand#clearCards()} method.
     * SHOULD set this player's score back to 0.
     */
    @Override
    public void clearCards() {
        this.hand.clearCards();
        this.setScore(0);
    }

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link BlackjackHand#getCardCount()} method.
     *
     * @return number of Cards in the hand.
     */
    @Override
    public int getCardCount() {
        return this.hand.getCardCount();
    }

    /**
     * For student to implement.
     * <p>
     * MUST remove tokens, and return the amount removed.
     *
     * @return the amount this player is bidding.
     */
    @Override
    public int bid(BlackjackController controller) {
        int bid = controller.getBidClicked(this.getTokens());
        player.setTokens(player.getTokens() - bid);
        return bid;
    }

    // === Given to the students below this point ===

    /**
     * @return this player's score.
     */
    @Override
    public int getScore() {
        return super.getScore();
    }

    /**
     * @param value the new score for this player.
     */
    @Override
    public void setScore(int value) {
        super.setScore(value);
    }

    /**
     * @return this player's tokens.
     */
    @Override
    public int getTokens() {
        return super.getTokens();
    }

    /**
     * @param value the new tokens for this player.
     */
    @Override
    public void setTokens(int value) {
        super.setTokens(value);
    }
}
