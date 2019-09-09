package solution;

import javafxextend.standard.Deck;
import model.AbstractBlackjackController;
import model.BlackjackPlayer;
import standard.Card;

import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class BlackjackComputerPlayer extends BlackjackPlayer {

    private final BlackjackHand hand;
    private BlackjackComputerPlayer player = this;

    public BlackjackComputerPlayer() {
        super("Dealer");
        this.hand = new BlackjackHand();
        this.bindBlackjackHand(this.hand);
    }

    /**
     * For student to implement.
     * <p>
     * Computer MUST draw another Card while its score
     * is below 16.
     *
     * @param controller the game controller.
     * @param deck       the deck for the game.
     */
    @Override
    public void takeTurn(AbstractBlackjackController controller, Deck deck) {
        boolean playing = true;
        while (playing) {
            if (this.getScore() < 16) {
                Card card = deck.drawCard();
                this.addCard(card);
            } else {
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
        int handScore = this.hand.getHandScore();
        this.setScore(handScore);
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
    public int bid(AbstractBlackjackController controller) {
        Random random = new Random();
        final int BOUND = 3;
        int choice = random.nextInt(BOUND);
        int bid;

        switch (choice) {
            case 0:
                bid = 1;
                break;
            case 1:
                bid = 3;
                break;
            default:
                bid = 5;
        }
        player.setTokens(player.getTokens() - bid);

        return bid;
    }

    // === Given to the students below this point. ===

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