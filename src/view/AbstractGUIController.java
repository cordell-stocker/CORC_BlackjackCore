package view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafxextend.structure.GUIController;
import model.AbstractPlayer;

public abstract class AbstractGUIController extends GUIController {

    final StackPane CENTER_PANE = new StackPane();
    private final PlayPanel PLAY_PANEL = new PlayPanel();
    private final WinnerDisplay WINNER_DISPLAY = new WinnerDisplay();
    private final ContinuePanel CONTINUE_PANEL = new ContinuePanel();

    private volatile boolean displayingWinner;

    public String getPlayOptionClicked() {
        this.addNodeToCenter(this.PLAY_PANEL);
        PlayPanel.Result option = this.PLAY_PANEL.getSelectedOption();
        this.removeNodeFromCenter(this.PLAY_PANEL);

        return option.toString();
    }

    void getContinue(CodeWrapper code) {
        this.addNodeToCenter(this.CONTINUE_PANEL);
        this.CONTINUE_PANEL.getContinue();
        this.removeNodeFromCenter(this.CONTINUE_PANEL);
        Platform.runLater(code::run);
    }

    void showHandWinner(AbstractPlayer player, CodeWrapper code) {
        GUIController.waitOnGUI();
        displayingWinner = true;
        Platform.runLater(() -> {
            WINNER_DISPLAY.setHandWinner(player);
            code.run();
            addNodeToCenter(WINNER_DISPLAY);
            displayingWinner = false;
        });

        this.waitThenRemoveWinnerDisplayFromCenter();
    }

    void showGameWinner(AbstractPlayer player, CodeWrapper code) {
        GUIController.waitOnGUI();
        displayingWinner = true;
        Platform.runLater(() -> {
            WINNER_DISPLAY.setGameWinner(player);
            code.run();
            addNodeToCenter(WINNER_DISPLAY);
            displayingWinner = false;
        });

        this.waitThenRemoveWinnerDisplayFromCenter();
    }

    private void waitThenRemoveWinnerDisplayFromCenter() {
        final long SLEEP_TIME = 100; // millis
        final long SHOW_WINNER_DISPLAY_TIME = 2000; // millis

        // Wait until winner display is updated and shown.
        while (displayingWinner) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Show the winner display for specified time.
        try {
            Thread.sleep(SHOW_WINNER_DISPLAY_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.removeNodeFromCenter(this.WINNER_DISPLAY);
    }

    void addNodeToCenter(Node child) {
        this.addNodeOnPlatformThread(this.CENTER_PANE, child);
    }

    void removeNodeFromCenter(Node child) {
        this.removeNodeOnPlatformThread(this.CENTER_PANE, child);
    }

}
