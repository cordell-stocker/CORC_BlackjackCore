package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.standard.CardImageView;
import model.BlackjackController;
import model.BlackjackPlayer;

public class BlackjackGUIController extends Application {

    private final double SCREEN_WIDTH = 800;
    private final double SCREEN_HEIGHT = 500;

    private BlackjackController controller;
    private final int[] POSSIBLE_BIDS = new int[]{1, 3, 5};
    private final BidPanel BID_PANEL = new BidPanel(POSSIBLE_BIDS);
    private final ActionPanel ACTION_PANEL = new ActionPanel();
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
        this.addPaneOnPlatformThread(this.CENTER_PANE, this.ACTION_PANEL);
        action = this.ACTION_PANEL.getActionClicked();
        this.removePaneOnPlatformThresh(this.CENTER_PANE, this.ACTION_PANEL);

        return action;
    }

    public boolean getPlayAgain() {
        // TODO
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public int getBidClicked(int availableTokens) {
        this.clearCenterArea();

        int bid;
        this.addPaneOnPlatformThread(this.CENTER_PANE, this.BID_PANEL);
        bid = this.BID_PANEL.getBidClicked(availableTokens);
        this.removePaneOnPlatformThresh(this.CENTER_PANE, this.BID_PANEL);

        return bid;
    }

    public void showHandWinner(BlackjackPlayer player) {
        this.clearCenterArea();

        BlackjackGUIController that = this;
        SimpleBooleanProperty startTimer = new SimpleBooleanProperty(false);
        Platform.runLater(() -> {
            that.WINNER_DISPLAY.setHandWinner(player);
            that.CENTER_PANE.getChildren().add(that.WINNER_DISPLAY);
            startTimer.set(true);
        });

        this.waitThenRemoveWinnerDisplay(startTimer);
    }

    public void showGameWinner(BlackjackPlayer player) {
        this.clearCenterArea();

        BlackjackGUIController that = this;
        SimpleBooleanProperty startTimer = new SimpleBooleanProperty(false);
        Platform.runLater(() -> {
            that.WINNER_DISPLAY.setGameWinner(player);
            that.CENTER_PANE.getChildren().add(that.WINNER_DISPLAY);
            startTimer.set(true);
        });

        this.waitThenRemoveWinnerDisplay(startTimer);
    }

    private void waitThenRemoveWinnerDisplay(SimpleBooleanProperty shouldStart) {
        final long UPDATE_TIME = 100; // millis
        final long SHOW_WINNER_DISPLAY_TIME = 2000; // millis

        // Wait until winner display is updated and shown.
        while (!shouldStart.get()) {
            try {
                Thread.sleep(UPDATE_TIME);
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

        this.removePaneOnPlatformThresh(this.CENTER_PANE, this.WINNER_DISPLAY);
    }

    private void addPaneOnPlatformThread(Pane parent, Pane child) {
        Platform.runLater(() -> parent.getChildren().add(child));
    }

    private void removePaneOnPlatformThresh(Pane parent, Node child) {
        Platform.runLater(() -> parent.getChildren().remove(child));
    }

    private void clearCenterArea() {
        BlackjackGUIController that = this;
        Platform.runLater(() -> that.CENTER_PANE.getChildren().clear());
    }
}
