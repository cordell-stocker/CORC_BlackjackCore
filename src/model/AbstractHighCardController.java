package model;

import view.HighCardKElvensGUIController;

public abstract class AbstractHighCardController extends AbstractGameController{

    private HighCardKElvensGUIController gui;

    public void play() {
        this.playGame();
    }

    public abstract void playGame();

    public boolean getPlayAgain() {
        return gui.getPlayOptionClicked().equals("PLAY_GAME");
    }

    public void setWinner(HighCardKElevensPlayer player) {
        gui.showHandWinner(player);
    }

    public abstract HighCardKElevensPlayer getHumanPlayer();

    public abstract HighCardKElevensPlayer getDealerPlayer();

    public void setGuiController(HighCardKElvensGUIController gui) {
        this.gui = gui;
    }
}
