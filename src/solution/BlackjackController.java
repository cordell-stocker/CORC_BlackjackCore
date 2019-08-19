package solution;

import model.BlackjackDeck;
import model.BlackjackPlayer;

public class BlackjackController extends model.BlackjackController {

    private BlackjackPlayer<BlackjackController> human;
    private BlackjackPlayer<BlackjackController> dealer;
    private BlackjackController gameController = this;
    private BlackjackDeck deck;

    /**
     * For student to implement.
     * <p>
     * Student should understand that the flow of execution starts here,
     * for the purpose of this project.
     */
    @Override
    public void playGame() {
        final int STARTING_TOKENS = 20;
        final int WINNING_THRESHHOLD = 40; // For now the goal is to double what the player started with.

        human.setTokens(STARTING_TOKENS);
        dealer.setTokens(STARTING_TOKENS);

        boolean playing = true;
        while (playing) {
            BlackjackPlayer winner = gameController.playHand();
            gameController.setHandWinner(winner);

            playing = human.getTokens() > 0 && human.getTokens() < WINNING_THRESHHOLD
                    && dealer.getTokens() > 0 && dealer.getTokens() < WINNING_THRESHHOLD
                    && gameController.getPlayAgain();
        }

        BlackjackPlayer gameWinner;
        if (human.getTokens() >= WINNING_THRESHHOLD) {
            gameWinner = human;
        } else if (human.getTokens() <= 0) {
            gameWinner = dealer;
        } else if (dealer.getTokens() >= WINNING_THRESHHOLD) {
            gameWinner = dealer;
        } else {
            gameWinner = human;
        }

        gameController.setGameWinner(gameWinner);
    }

    public BlackjackPlayer playHand() {
        final int NEW_DECK_THRESHOLD = 15;
        final int STARTING_CARDS = 2;
        final int WINNING_SCORE = 21;
        final int HOUSE_RULE_WIN = 5;
        BlackjackPlayer handWinner;

        if (deck.size() <= NEW_DECK_THRESHOLD) {
            deck.reset();
            deck.shuffle();
        }

        int bidPool = gameController.getBidPool();

        for (int i = 0; i < STARTING_CARDS; i++) {
            human.addCard(deck.drawCard());
            dealer.addCard(deck.drawCard());
        }

        human.takeTurn(gameController, deck);
        // Dealer won.
        if (human.getScore() > WINNING_SCORE) {
            handWinner = dealer;
        }
        // Human won.
        else if (human.getScore() == WINNING_SCORE || human.getCardCount() == HOUSE_RULE_WIN) {
            handWinner = human;
        }
        // No winner yet.
        else {
            dealer.takeTurn(gameController, deck);
            // Human won.
            if (dealer.getScore() > WINNING_SCORE) {
                handWinner = human;
            }
            // Dealer won.
            else if (dealer.getCardCount() == HOUSE_RULE_WIN || dealer.getScore() > human.getScore()) {
                handWinner = dealer;
            } else {
                handWinner = human;
            }
        }

        handWinner.setTokens(handWinner.getTokens() + bidPool);

        return handWinner;

    }

    public int getBidPool() {
        int bidPool = 0;
        bidPool += human.bid(gameController);
        bidPool += dealer.bid(gameController);

        return bidPool;
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
    public int getBidClicked() {
        return super.getBidClicked();
    }
}
