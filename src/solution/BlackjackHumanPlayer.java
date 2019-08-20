package solution;

import model.BlackjackController;
import model.BlackjackPlayer;
import observablestandard.Deck;
import standard.Card;
import standard.Face;
import standard.Suit;

public class BlackjackHumanPlayer extends BlackjackPlayer {

    private final BlackjackHand hand;
    private BlackjackHumanPlayer player = this;

    public BlackjackHumanPlayer() {
        super("You");
        this.hand = new BlackjackHand(this);
    }

    /**
     * For student to implement.
     *
     * Should prevent the player from playing (end turn) once
     * their score goes over 21.
     *
     * Should call the BlackjackController#getActionClicked()
     * method to determine if the player wants to "HIT"
     * (draw another Card) or "STAY" (end turn).
     *
     * @param controller the game controller.
     * @param deck the deck used in the game.
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

    @Override
    public int bid(BlackjackController controller) {
        int bid = controller.getBidClicked();
        player.setTokens(player.getTokens() - bid);
        return bid;
    }

    /**
     * For student to implement.
     *
     * Needs to wrap BlackjackHand#addCard(Card) method.
     *
     * @param card Card to be added.
     */
    @Override
    public void addCard(Card card) {
        this.hand.addCard(card);
        this.setScore(this.hand.getHandScore());
    }

    /**
     * For student to implement.
     *
     * Just needs to wrap the BlackjackHand#clearCards() method.
     */
    @Override
    public void clearCards() {
        this.hand.clearCards();
    }

    /**
     * For student to implement.
     *
     * Just needs to wrap the BlackjackHand#getCardCard() method.
     *
     * @return number of Cards in the hand.
     */
    @Override
    public int getCardCount() {
        return this.hand.getCardCount();
    }

    // Given to the students below this point.

    /**
     *
     * @return this player's score.
     */
    @Override
    public int getScore() {
        return super.getScore();
    }

    /**
     *
     * @param value the new score for this player.
     */
    @Override
    public void setScore(int value) {
        super.setScore(value);
    }

    /**
     *
     * @return this player's tokens.
     */
    @Override
    public int getTokens() {
        return super.getTokens();
    }

    /**
     *
     * @param value the new tokens for this player.
     */
    @Override
    public void setTokens(int value) {
        super.setTokens(value);
    }
}
