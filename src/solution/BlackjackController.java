package solution;

import model.AbstractBlackjackController;
import model.BlackjackDeck;
import model.BlackjackPlayer;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal", "unchecked"})
public class BlackjackController extends AbstractBlackjackController {

    private BlackjackPlayer human;
    private BlackjackPlayer dealer;
    private BlackjackController gameController = this;
    private BlackjackDeck deck;
    private final int NEW_DECK_THRESHOLD = 15;
    private final int STARTING_CARDS = 2;
    private final int MAX_SCORE = 21;
    private final int CARD_COUNT_WIN_THRESHOLD = 5;
    private final int STARTING_TOKENS = 20;
    private final int WINNING_THRESHOLD = 40; // For now the goal is to double what the player started with.

    public BlackjackController(BlackjackPlayer human, BlackjackPlayer dealer, BlackjackDeck deck) {
        this.human = human;
        this.dealer = dealer;
        this.deck = deck;
    }


    /**
     * For student to implement.
     * <p>
     * Student should understand that the flow of execution starts here,
     * for the purpose of this project.
     * <p>
     * Should contain all logic necessary for playing multiple
     * full games of blackjack.
     */
    @Override
    public void play() {
        boolean playing = true;
        while (playing) {
            gameController.playGame();
            String response = gameController.getPlayOptionClicked();
            playing = response.equals("PLAY GAME");
        }
        gameController.exitGame();
    }

    /**
     * For student to implement.
     * <p>
     * Should contain all logic necessary for playing an
     * entire game of blackjack (until a player has enough
     * tokens or runs out of tokens).
     */
    @Override
    public void playGame() {
        human.setTokens(STARTING_TOKENS);
        dealer.setTokens(STARTING_TOKENS);
        deck.reset();
        deck.shuffle();

        boolean playing = true;
        while (playing) {
            BlackjackPlayer winner = gameController.playHand();
            gameController.setHandWinner(winner);

            gameController.getContinue();

            human.clearCards();
            dealer.clearCards();

            // Are both players' tokens between 0 and WINNING_THRESHOLD?
            boolean humanTokensValid = WINNING_THRESHOLD > human.getTokens() && human.getTokens() > 0;
            boolean dealerTokensValid = WINNING_THRESHOLD > dealer.getTokens() && dealer.getTokens() > 0;
            playing = humanTokensValid && dealerTokensValid;
        }

        BlackjackPlayer gameWinner;
        if (human.getTokens() >= WINNING_THRESHOLD || dealer.getTokens() <= 0) {
            gameWinner = human;
        } else {
            gameWinner = dealer;
        }

        gameController.setGameWinner(gameWinner);
    }

    /**
     * For student to implement.
     * <p>
     * Should contain all logic necessary for playing a single
     * hand of blackjack, and returning the winner.
     *
     * @return the winner of the hand.
     */
    @Override
    public BlackjackPlayer playHand() {
        if (deck.size() <= NEW_DECK_THRESHOLD) {
            deck.reset();
            deck.shuffle();
        }

        int bidPool = 0;
        bidPool += human.bid(gameController);
        bidPool += dealer.bid(gameController);

        for (int i = 0; i < STARTING_CARDS; i++) {
            human.addCard(deck.drawCard());
            dealer.addCard(deck.drawCard());
        }

        BlackjackPlayer handWinner;
        human.takeTurn(gameController, deck);
        // Dealer won.
        if (human.getScore() > MAX_SCORE) {
            handWinner = dealer;
        }
        // Human won.
        else if (human.getScore() == MAX_SCORE || human.getCardCount() == CARD_COUNT_WIN_THRESHOLD) {
            handWinner = human;
        }
        // No winner yet.
        else {
            dealer.takeTurn(gameController, deck);
            // Human won.
            if (dealer.getScore() > MAX_SCORE) {
                handWinner = human;
            }
            // Dealer won.
            else if (dealer.getCardCount() == CARD_COUNT_WIN_THRESHOLD || dealer.getScore() >= human.getScore()) {
                handWinner = dealer;
            } else {
                handWinner = human;
            }
        }

        handWinner.setTokens(handWinner.getTokens() + bidPool);

        return handWinner;
    }

    // === Given ===

    @Override
    public BlackjackPlayer getHumanPlayer() {
        return this.human;
    }

    @Override
    public BlackjackPlayer getDealerPlayer() {
        return this.dealer;
    }

    @Override
    public String getActionClicked() {
        return super.getActionClicked();
    }

    @Override
    public void setHandWinner(BlackjackPlayer player) {
        super.setHandWinner(player);
    }

    @Override
    public void setGameWinner(BlackjackPlayer player) {
        super.setGameWinner(player);
    }

    @Override
    public int getBidClicked(int availableTokens) {
        return super.getBidClicked(availableTokens);
    }

    @Override
    public String getPlayOptionClicked() {
        return super.getPlayOptionClicked();
    }

    @Override
    public void getContinue() {
        super.getContinue();
    }

    @Override
    public void exitGame() {
        super.exitGame();
    }

}
