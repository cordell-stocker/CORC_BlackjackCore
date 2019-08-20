package view;

import javafx.GUIController;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.standard.CardImageView;
import model.BlackjackController;
import model.BlackjackPlayer;

@SuppressWarnings("FieldCanBeLocal")
public class BlackjackGUIController extends GUIController {

    private final double SCREEN_WIDTH = 800;
    private final double SCREEN_HEIGHT = 500;

    private BlackjackController controller;
    private final Integer[] POSSIBLE_BIDS = new Integer[]{1, 3, 5};
    private final BidPanel BID_PANEL = new BidPanel(POSSIBLE_BIDS);
    private final ActionPanel ACTION_PANEL = new ActionPanel();
    private final PlayPanel PLAY_PANEL = new PlayPanel();
    private final WinnerDisplay WINNER_DISPLAY = new WinnerDisplay();
    private final CardImageView DECK_CIV = new CardImageView();

    private final BorderPane MAIN_PANE = new BorderPane();
    private final StackPane CENTER_PANE = new StackPane();

    public void setGameController(BlackjackController controller) {
        this.controller = controller;
    }

    public BlackjackController getGameController() {
        return this.controller;
    }

    public void launch() {
        this.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Scene scene = new Scene(this.MAIN_PANE, this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getActionClicked() {
        this.clearCenterArea();

        String action;
        this.addNodeOnPlatformThread(this.CENTER_PANE, this.ACTION_PANEL);
        action = this.ACTION_PANEL.getActionClicked();
        this.removeNodeOnPlatformThread(this.CENTER_PANE, this.ACTION_PANEL);

        return action;
    }

    public String getPlayOptionClicked() {
        this.clearCenterArea();

        String option;
        this.addNodeToCenter(this.PLAY_PANEL);
        option = this.PLAY_PANEL.getSelectedOption();
        this.removeNodeFromCenter(this.PLAY_PANEL);

        return option;
    }

    public int getBidClicked(int availableTokens) {
        this.clearCenterArea();

        int bid;
        this.addNodeToCenter(this.BID_PANEL);
        bid = this.BID_PANEL.getBidClicked(availableTokens);
        this.removeNodeFromCenter(this.BID_PANEL);

        return bid;
    }

    public void showHandWinner(BlackjackPlayer player) {
        this.clearCenterArea();

        BlackjackGUIController that = this;
        SimpleBooleanProperty startTimer = new SimpleBooleanProperty(false);
        Platform.runLater(() -> {
            that.WINNER_DISPLAY.setHandWinner(player);
            that.addNodeToCenter(that.WINNER_DISPLAY);
            startTimer.set(true);
        });

        this.waitThenRemoveWinnerDisplayFromCenter(startTimer);
    }

    public void showGameWinner(BlackjackPlayer player) {
        this.clearCenterArea();

        BlackjackGUIController that = this;
        SimpleBooleanProperty startTimer = new SimpleBooleanProperty(false);
        Platform.runLater(() -> {
            that.WINNER_DISPLAY.setGameWinner(player);
            that.addNodeToCenter(that.WINNER_DISPLAY);
            startTimer.set(true);
        });

        this.waitThenRemoveWinnerDisplayFromCenter(startTimer);
    }

    private void waitThenRemoveWinnerDisplayFromCenter(SimpleBooleanProperty shouldStart) {
        final long SLEEP_TIME = 100; // millis
        final long SHOW_WINNER_DISPLAY_TIME = 2000; // millis

        // Wait until winner display is updated and shown.
        while (!shouldStart.get()) {
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

    private void addNodeToCenter(Node child) {
        this.addNodeOnPlatformThread(this.CENTER_PANE, child);
    }

    private void removeNodeFromCenter(Node child) {
        this.removeNodeOnPlatformThread(this.CENTER_PANE, child);
    }

    private void clearCenterArea() {
        BlackjackGUIController that = this;
        Platform.runLater(() -> that.CENTER_PANE.getChildren().clear());
    }
}
