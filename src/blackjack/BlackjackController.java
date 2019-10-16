package blackjack;

import blackjackcore.model.AbstractBlackjackController;
import blackjackcore.model.BlackjackDeck;
import blackjackcore.model.player.BlackjackPlayer;
import corc.standard.*;

public class BlackjackController extends AbstractBlackjackController<BlackjackPlayer> {

    private BlackjackPlayer human;
    private BlackjackPlayer dealer;
    private BlackjackController gameController = this;
    private BlackjackDeck deck;

    public BlackjackController(
            BlackjackPlayer human,
            BlackjackPlayer dealer,
            BlackjackDeck deck
    ) {
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
            boolean response = gameController.getPlayGame();
            playing = response;
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
        int STARTING_TOKENS = 20;
        // For now the goal is to double what the player started with.
        int WINNING_THRESHOLD = 40;
        human.setTokens(STARTING_TOKENS);
        dealer.setTokens(STARTING_TOKENS);
        deck.reset();
        deck.shuffle();

        boolean playing = true;
        while (playing) {
            BlackjackPlayer winner = gameController.playHand();
            gameController.setHandWinner(winner);

            gameController.getContinue(); // completely stops execution until "continue" is pressed in the GUI.

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
    public BlackjackPlayer playHand() {
        int NEW_DECK_THRESHOLD = 15;
        if (deck.size() <= NEW_DECK_THRESHOLD) {
            deck.reset();
            deck.shuffle();
        }

        int bidPool = 0;
        bidPool += human.bid(gameController);
        bidPool += dealer.bid(gameController);

        int STARTING_CARDS = 2;
        for (int i = 0; i < STARTING_CARDS; i++) {
            human.addCard(deck.drawCard());
            dealer.addCard(deck.drawCard());
        }

        int MAX_SCORE = 21;
        int CARD_COUNT_WIN_THRESHOLD = 5;
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
    public int getBidClicked() {
        return super.getBidClicked();
    }

    @Override
    public boolean getPlayGame() {
        return super.getPlayGame();
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
