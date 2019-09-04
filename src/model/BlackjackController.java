package model;

import view.BlackjackGUIController;

public abstract class BlackjackController {

    private BlackjackGUIController gui;

    public BlackjackController() {
    }

    public void launchWithGUI() {
        BlackjackGUIController.setGameController(this);
        BlackjackGUIController.launchGUI();
    }

    public abstract void play();

    public abstract void playGame();

    public abstract BlackjackPlayer playHand();

    public String getActionClicked() {
        return this.gui.getActionClicked();
    }

    public void setHandWinner(BlackjackPlayer player) {
        this.gui.showHandWinner(player);
    }

    public void setGameWinner(BlackjackPlayer player) {
        this.gui.showGameWinner(player);
    }

    public String getPlayOptionClicked() {
        return this.gui.getPlayOptionClicked();
    }

    public int getBidClicked(int availableTokens) {
        return this.gui.getBidClicked(availableTokens);
    }

    public abstract BlackjackPlayer getHumanPlayer();

    public abstract BlackjackPlayer getDealerPlayer();

    public void setGuiController(BlackjackGUIController gui) {
        this.gui = gui;
    }
}
