package solution;

import model.BlackjackController;
import model.BlackjackPlayer;
import observablestandard.Deck;
import standard.Card;
import standard.Face;
import standard.Suit;

import java.util.Random;

public class BlackjackComputerPlayer extends BlackjackPlayer {

    private final BlackjackHand hand;
    private BlackjackComputerPlayer player = this;

    public BlackjackComputerPlayer() {
        super("Dealer");
        this.hand = new BlackjackHand(this);
    }

    /**
     * For student to implement.
     *
     * Computer should draw another Card while its score
     * is below 16.
     *
     * @param controller the game controller.
     * @param deck the deck for the game.
     */
    @Override
    public void takeTurn(BlackjackController controller, Deck deck) {
        boolean playing = true;
        while (playing) {
            if (this.getScore() < 16) {
                Card card = deck.drawCard();
                this.addCard(card);
            } else {
                playing = false;
            }
        }
        return;
    }

    /**
     * For student to implement.
     *
     * Should remove tokens from this player, and
     * return the number of tokens removed.
     *
     * @param controller the game controller.
     * @return amount this player is bidding.
     */
    @Override
    public int bid(BlackjackController controller) {
        Random random = new Random();
        final int BOUND = 3;
        int choice = random.nextInt(BOUND);
        int bid;

        switch (choice) {
            case 0: bid = 1; break;
            case 1: bid = 3; break;
            default: bid = 5;
        }
        player.setTokens(player.getTokens() - bid);

        return bid;
    }

    /**
     * For student to implement.
     *
     * Just needs to wrap {@link BlackjackHand#addCard(Card)} method.
     *
     * @param card Card to be added.
     */
    @Override
    public void addCard(Card card) {
        this.hand.addCard(card);
        int handScore = this.hand.getHandScore();
        this.setScore(handScore);
    }

    /**
     * For student to implement.
     *
     * Just needs to wrap the {@link BlackjackHand#addCard(Card)} method.
     */
    @Override
    public void clearCards() {
        this.hand.clearCards();
        this.setScore(0);
    }

    /**
     * For student to implement.
     *
     * Just needs to wrap the {@link BlackjackHand#addCard(Card)} method.
     *
     * @return number of Cards in the hand.
     */
    @Override
    public int getCardCount() {
        return this.hand.getCardCount();
    }

    // === Given to the students below this point. ===

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
     * @param value the new amount of tokens for this player.
     */
    @Override
    public void setTokens(int value) {
        super.setTokens(value);
    }
}