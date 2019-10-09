package blackjackcore.model;

import blackjackcore.model.player.HighCardPlayer;
import blackjackcore.view.HighCardGUIController;

public abstract class AbstractHighCardController extends AbstractGameController{

    private HighCardGUIController gui;

    public void play() {
        this.playGame();
    }

    public abstract void playGame();

    public boolean getPlayGame() {
        return gui.getPlayGame();
    }

    public void setWinner(HighCardPlayer player) {
        gui.showHandWinner(player);
    }

    public abstract HighCardPlayer getHumanPlayer();

    public abstract HighCardPlayer getDealerPlayer();

    public void setGuiController(HighCardGUIController gui) {
        this.gui = gui;
    }
}
