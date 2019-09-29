package solution;

import model.BlackjackPlayer;
import standard.Card;
import standard.Cardset;
import standard.Deck;

import java.util.ArrayList;

/**
 * Represents the human player in a game of Blackjack.
 * <p>
 * A BlackjackHumanPlayer will need to makes calls to its
 * controller to get user input.
 */
@SuppressWarnings("WeakerAccess")
public class HumanBlackjackPlayer extends BlackjackPlayer<BlackjackController> {

    private final BlackjackHand hand;
    private HumanBlackjackPlayer player = this;

    public HumanBlackjackPlayer() {
        super("You", new Cardset(new ArrayList<>()));
        this.hand = new BlackjackHand();
        this.bindBlackjackHand(this.hand);
    }

    /**
     * For student to implement.
     * <p>
     * SHOULD prevent the player from playing once
     * their score goes over 21.
     * <p>
     * MAY prevent the player from playing once
     * their score is 21.
     * <p>
     * SHOULD prevent the player from playing once
     * they have 5 cards.
     * <p>
     * MUST call {@link BlackjackController#getActionClicked()}
     * to determine if the player wants to "HIT"
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
                player.addCard(card);
            } else if (action.equals("STAY")) {
                playing = false;
            }
            if (player.getScore() >= 21 || player.getCardCount() == 5) {
                playing = false;
            }
        }
    }

    /**
     * For the student to implement.
     * <p>
     * MUST call {@link BlackjackHand#addCard(Card)}.
     * <p>
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
     * MUST call {@link BlackjackHand#clearCards()}.
     * <p>
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
     * MUST call {@link BlackjackHand#getCardCount()}.
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
     * MUST call {@link BlackjackController#getBidClicked(int)}.
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
     * @param value the new amount of tokens for this player.
     */
    @Override
    public void setTokens(int value) {
        super.setTokens(value);
    }
}
