package solution;

import model.BlackjackPlayer;
import observablestandard.Deck;
import standard.Card;

import java.util.Random;

public class BlackjackComputerPlayer extends BlackjackPlayer<BlackjackController> {

    private final BlackjackHand hand;
    private BlackjackComputerPlayer player = this;

    public BlackjackComputerPlayer() {
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
    }

    /**
     * For student to implement.
     *
     * @param controller the game controller.
     * @return amount this player is bidding.
     */
    @Override
    public int bid(BlackjackController controller) {
        Random random = new Random();
        int choice = random.nextInt(3);
        int bid = 0;

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