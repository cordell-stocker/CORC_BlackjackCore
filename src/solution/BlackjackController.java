package solution;

import model.BlackjackDeck;
import model.BlackjackPlayer;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
public class BlackjackController extends model.BlackjackController {

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
     *
     * Should contain all logic necessary for playing multiple
     * full games of blackjack.
     */
    @Override
    public void play() {
        boolean playing = gameController.shouldPlay();
        while (playing) {
            gameController.playGame();
            playing = gameController.shouldPlay();
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
            playing = gameController.doPlayersHaveTokensInsideLimit(human, dealer, 0, WINNING_THRESHOLD);
        }

        BlackjackPlayer gameWinner = gameController.getGameWinner();
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

        handWinner = gameController.doHumanAndDealerTurnAndReturnWinner();
        handWinner.setTokens(handWinner.getTokens() + bidPool);

        return handWinner;
    }

    // === Not required, just makes code look nicer. Most students will just have big methods. ===

    public BlackjackPlayer doHumanAndDealerTurnAndReturnWinner() {
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
            handWinner = gameController.doDealerTurnAndReturnWinner();
        }
        return handWinner;
    }

    public BlackjackPlayer doDealerTurnAndReturnWinner() {
        BlackjackPlayer handWinner;
        dealer.takeTurn(gameController, deck);
        // Human won.
        if (dealer.getScore() > MAX_SCORE) {
            handWinner = human;
        }
        // Dealer won.
        else if (dealer.getCardCount() == CARD_COUNT_WIN_THRESHOLD || dealer.getScore() > human.getScore()) {
            handWinner = dealer;
        } else {
            handWinner = human;
        }
        return handWinner;
    }

    public boolean doPlayersHaveTokensInsideLimit
            (BlackjackPlayer firstPlayer, BlackjackPlayer secondPlayer, int min, int max) {
        return gameController.hasTokensInsideLimit(firstPlayer, min, max)
                && gameController.hasTokensInsideLimit(secondPlayer, min, max);
    }

    public boolean hasTokensInsideLimit(BlackjackPlayer player, int min, int max) {
        return max > player.getTokens() && player.getTokens() > min;
    }

    public int getBidPool() {
        int bidPool = 0;
        bidPool += human.bid(gameController);
        bidPool += dealer.bid(gameController);

        return bidPool;
    }

    public BlackjackPlayer getGameWinner() {
        BlackjackPlayer gameWinner;
        if (human.getTokens() >= WINNING_THRESHOLD || dealer.getTokens() <= 0) {
            gameWinner = human;
        } else {
            gameWinner = dealer;
        }
        return gameWinner;
    }

    public boolean shouldPlay() {
        String play = gameController.getPlayOptionClicked();
        return play.equals("PLAY GAME");
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

    @Override
    public BlackjackPlayer getDealer() {
        return this.dealer;
    }

    @Override
    public BlackjackPlayer getHuman() {
        return this.human;
    }
}
