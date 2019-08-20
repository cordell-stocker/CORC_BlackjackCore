package model;

import view.BlackjackGUIController;

public abstract class BlackjackController {

    private BlackjackGUIController gui;

    public BlackjackController() {
    }

    public void launchWithGUI() {
        this.gui = new BlackjackGUIController();
        this.gui.setGameController(this);
        this.gui.launch();
    }

    public abstract void playGame();

    public String getActionClicked() {
        return this.gui.getActionClicked();
    }

    public void setHandWinner(BlackjackPlayer player) {
        this.gui.showHandWinner(player);
    }

    public void setGameWinner(BlackjackPlayer player) {
        this.gui.showGameWinner(player);
    }

    public boolean getPlayAgain() {
        return this.gui.getPlayAgain();
    }

    public int getBidClicked() {
        return this.gui.getBidClicked();
    }

    public abstract BlackjackPlayer getHumanPlayer();

    public abstract BlackjackPlayer getDealerPlayer();
}
