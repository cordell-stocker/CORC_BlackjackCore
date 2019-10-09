package blackjackcore.model;

import blackjackcore.model.player.PlayerWithScoreAndTokens;
import blackjackcore.view.BlackjackKElevensGUIController;

public abstract class AbstractBlackjackController<P extends PlayerWithScoreAndTokens> extends AbstractGameController {

    private BlackjackKElevensGUIController gui;

    public abstract void playGame();

    /**
     * @return either "HIT" or "STAY"
     */
    public String getActionClicked() {
        return this.gui.getActionClicked();
    }

    /**
     * Displays the hand winner.
     *
     * @param player the player who won the hand.
     */
    public void setHandWinner(P player) {
        this.gui.showHandWinner(player);
    }

    /**
     * Displays the game winner.
     *
     * @param player the player who won the game.
     */
    public void setGameWinner(P player) {
        this.gui.showGameWinner(player);
    }

    /**
     * @return
     */
    public boolean getPlayGame() {
        return this.gui.getPlayGame();
    }

    /**
     * Will not allow player to bid more tokens than they have.
     *
     * @return the amount clicked.
     */
    public int getBidClicked() {
        return this.gui.getBidClicked();
    }

    /**
     * Waits for the "Continue" button to be pressed.
     */
    public void getContinue() {
        this.gui.getContinue();
    }

    public abstract P getHumanPlayer();

    public abstract P getDealerPlayer();

    public void setGuiController(BlackjackKElevensGUIController gui) {
        this.gui = gui;
    }

}
