package blackjack;

import blackjackcore.model.player.BlackjackPlayer;
import corc.standard.*;

import java.util.ArrayList;
import java.util.Random;

public class ComputerBlackjackPlayer extends BlackjackPlayer<BlackjackController> {

    private final BlackjackHand hand;
    private ComputerBlackjackPlayer player = this;

    public ComputerBlackjackPlayer() {
        super("Dealer", new Cardset(new ArrayList<>()));
        this.hand = new BlackjackHand();
        this.bindBlackjackHand(this.hand);
    }

    /**
     * For student to implement.
     * <p>
     * MUST draw another Card while its score
     * is below 16. Unless this player has
     * five cards.
     *
     * @param controller the game controller.
     * @param deck       the deck for the game.
     */
    @Override
    public void takeTurn(BlackjackController controller, Deck deck) {
        boolean playing = true;
        while (playing) {
            if (player.getScore() < 16 && hand.getCardCount() < 5) {
                Card card = deck.drawCard();
                player.addCard(card);
            } else {
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
        hand.addCard(card);
        int handScore = hand.getHandScore();
        player.setScore(handScore);
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
        hand.clearCards();
        player.setScore(0);
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
        return hand.getCardCount();
    }

    /**
     * For student to implement.
     * <p>
     * MUST randomly decide between 1, 3, and 5 tokens to
     * bid.
     * <p>
     * MUST NOT bid more tokens than this player has.
     * <p>
     * MUST remove tokens, and return the amount removed.
     *
     * @return the amount this player is bidding.
     */
    @Override
    public int bid(BlackjackController controller) {
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
        while (bid > player.getTokens() && bid >= 3) {
            bid -= 2;
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