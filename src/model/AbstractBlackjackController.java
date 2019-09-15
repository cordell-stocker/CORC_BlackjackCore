package model;

import view.BlackjackGUIController;

public abstract class AbstractBlackjackController {

    private BlackjackGUIController gui;

    public void launchWithGUI() {
        BlackjackGUIController.setGameController(this);
        BlackjackGUIController.launchGUI();
    }

    public abstract void play();

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
    public void setHandWinner(BlackjackPlayer player) {
        this.gui.showHandWinner(player);
    }

    /**
     * Displays the game winner.
     *
     * @param player the player who won the game.
     */
    public void setGameWinner(BlackjackPlayer player) {
        this.gui.showGameWinner(player);
    }

    /**
     * @return either "PLAY GAME" or "EXIT"
     */
    public String getPlayOptionClicked() {
        return this.gui.getPlayOptionClicked();
    }

    /**
     * Will not allow player to bid more tokens than they have.
     *
     * @param availableTokens how many tokens the player has.
     * @return the amount clicked.
     */
    public int getBidClicked(int availableTokens) {
        return this.gui.getBidClicked(availableTokens);
    }

    /**
     * Waits for the "Continue" button to be pressed.
     */
    public void getContinue() {
        this.gui.getContinue();
    }

    /**
     * Closes the game.
     */
    public void exitGame() {
        System.exit(1);
    }

    public abstract BlackjackPlayer getHumanPlayer();

    public abstract BlackjackPlayer getDealerPlayer();

    public void setGuiController(BlackjackGUIController gui) {
        this.gui = gui;
    }

}
