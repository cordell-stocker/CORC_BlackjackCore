package view;

import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafxextend.structure.GUIController;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafxextend.standard.CardImageView;
import model.AbstractBlackjackController;
import model.BlackjackPlayer;
import standard.Card;

@SuppressWarnings("FieldCanBeLocal")
public class BlackjackGUIController extends GUIController {

    private final double SCREEN_WIDTH = 800;
    private final double SCREEN_HEIGHT = 500;

    private static AbstractBlackjackController controller;
    private final BidPanel BID_PANEL = new BidPanel();
    private final ActionPanel ACTION_PANEL = new ActionPanel();
    private final PlayPanel PLAY_PANEL = new PlayPanel();
    private final ContinuePanel CONTINUE_PANEL = new ContinuePanel();
    private final WinnerDisplay WINNER_DISPLAY = new WinnerDisplay();
    private final CardImageView DECK_CIV = new CardImageView();

    private DealerVisualHand dealerVH;
    private BlackjackVisualHand humanVH;

    private final BorderPane MAIN_PANE = new BorderPane();
    private final StackPane CENTER_PANE = new StackPane();

    private Image deckImage = this.DECK_CIV.getImage();
    private double spacing = this.deckImage.getWidth() * -0.33;

    public static void setGameController(AbstractBlackjackController controller) {
        BlackjackGUIController.controller = controller;
    }

    public static void launchGUI() {
        BlackjackGUIController.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BlackjackGUIController.controller.setGuiController(this);

        // Start Student Code When Clicked
        Button start = new Button("Start Game");
        this.addNodeToCenter(start);
        start.setOnAction(e -> {
            Thread gameThread = new Thread(() -> BlackjackGUIController.controller.play());
            gameThread.setDaemon(true);
            gameThread.start();
            this.removeNodeFromCenter(start);
        });

        // GUI Building
        this.setupDealer();
        this.setupHuman();

        this.CENTER_PANE.setAlignment(Pos.CENTER);

        this.MAIN_PANE.setCenter(this.CENTER_PANE);
        this.MAIN_PANE.setTop(dealerVH);
        this.MAIN_PANE.setBottom(humanVH);

        // Debug::
//        this.MAIN_PANE.getTop().setStyle("-fx-border-color: RED");
//        this.MAIN_PANE.getBottom().setStyle("-fx-border-color: RED");
//        dealerCardPane.setStyle("-fx-border-color: BLUE");
//        humanCardPane.setStyle("-fx-border-color: BLUE");
//        this.PLAY_PANEL.setStyle("-fx-background-color: BLUE");

        this.MAIN_PANE.setBackground(Background.EMPTY);
        Color color = new Color(0.027, 0.386, 0.14, 1); // Card Table Green
        Scene scene = new Scene(this.MAIN_PANE, this.SCREEN_WIDTH, this.SCREEN_HEIGHT, color);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupDealer() {
        HBox dealerCardPane = new HBox();
        dealerCardPane.setSpacing(spacing);
        this.dealerVH = new DealerVisualHand(dealerCardPane);
        BlackjackPlayer dealerPlayer = BlackjackGUIController.controller.getDealerPlayer();
        this.setupPlayer(dealerPlayer, this.dealerVH);
    }

    private void setupHuman() {
        HBox humanCardPane = new HBox();
        humanCardPane.setSpacing(spacing);
        this.humanVH = new BlackjackVisualHand(humanCardPane);
        BlackjackPlayer humanPlayer = BlackjackGUIController.controller.getHumanPlayer();
        this.setupPlayer(humanPlayer, this.humanVH);
    }

    private void setupPlayer(BlackjackPlayer player, BlackjackVisualHand visualHand) {
        visualHand.setMinHeight(deckImage.getHeight());
        ListChangeListener<Card> cardListener = visualHand.getListener();
        ChangeListener<Number> scoreListener = visualHand.getScoreListener();
        ChangeListener<Number> tokenListener = visualHand.getTokenListener();
        player.addCardsetListener(cardListener);
        player.setVisualHand(visualHand);
        player.addScoreListener(scoreListener);
        player.addTokenListener(tokenListener);
    }

    public String getActionClicked() {
        this.addNodeOnPlatformThread(this.CENTER_PANE, this.ACTION_PANEL);
        String action = this.ACTION_PANEL.getActionClicked();
        this.removeNodeOnPlatformThread(this.CENTER_PANE, this.ACTION_PANEL);
        Platform.runLater(() -> this.dealerVH.hideStatus());

        return action;
    }

    public String getPlayOptionClicked() {
        this.addNodeToCenter(this.PLAY_PANEL);
        String option = this.PLAY_PANEL.getSelectedOption();
        this.removeNodeFromCenter(this.PLAY_PANEL);

        return option;
    }

    public void getContinue() {
        this.addNodeToCenter(this.CONTINUE_PANEL);
        this.CONTINUE_PANEL.getContinue();
        this.removeNodeFromCenter(this.CONTINUE_PANEL);
        Platform.runLater(() -> this.dealerVH.hideStatus());
    }

    public int getBidClicked(int availableTokens) {
        this.addNodeToCenter(this.BID_PANEL);
        int bid = this.BID_PANEL.getBidClicked(availableTokens);
        this.removeNodeFromCenter(this.BID_PANEL);

        return bid;
    }

    public void showHandWinner(BlackjackPlayer player) {
        GUIController.waitOnGUI();
        BlackjackGUIController that = this;
        SimpleBooleanProperty startTimer = new SimpleBooleanProperty(false);
        Platform.runLater(() -> {
            that.WINNER_DISPLAY.setHandWinner(player);
            that.dealerVH.showEverything();
            that.addNodeToCenter(that.WINNER_DISPLAY);
            startTimer.set(true);
        });

        this.waitThenRemoveWinnerDisplayFromCenter(startTimer);
    }

    public void showGameWinner(BlackjackPlayer player) {
        GUIController.waitOnGUI();
        BlackjackGUIController that = this;
        SimpleBooleanProperty startTimer = new SimpleBooleanProperty(false);
        Platform.runLater(() -> {
            that.WINNER_DISPLAY.setGameWinner(player);
            that.dealerVH.showEverything();
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

}
