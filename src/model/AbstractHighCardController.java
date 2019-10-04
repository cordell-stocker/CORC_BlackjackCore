package model;

import view.HighCardKElevensGUIController;

public abstract class AbstractHighCardController extends AbstractGameController{

    private HighCardKElevensGUIController gui;

    public void play() {
        this.playGame();
    }

    public abstract void playGame();

    public boolean getPlayGame() {
        return gui.getPlayGame();
    }

    public void setWinner(HighCardKElevensPlayer player) {
        gui.showHandWinner(player);
    }

    public abstract HighCardKElevensPlayer getHumanPlayer();

    public abstract HighCardKElevensPlayer getDealerPlayer();

    public void setGuiController(HighCardKElevensGUIController gui) {
        this.gui = gui;
    }
}
