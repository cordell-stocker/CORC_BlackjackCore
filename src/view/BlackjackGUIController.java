package view;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafxextend.standard.CardImageView;
import model.AbstractBlackjackController;
import model.BlackjackPlayer;
import standard.Card;
import structure.ICardsetListener;
import structure.IChangeListener;

public class BlackjackGUIController extends AbstractGUIController {

    private static AbstractBlackjackController controller;
    private final BidPanel BID_PANEL = new BidPanel();
    private final ActionPanel ACTION_PANEL = new ActionPanel();
    private final CardImageView DECK_CIV = new CardImageView();

    private DealerVisualHand dealerVH;
    private BlackjackVisualHand humanVH;

    private final BorderPane MAIN_PANE = new BorderPane();

    private Image deckImage = this.DECK_CIV.getImage();

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

        ReadOnlyDoubleProperty screenWidth = this.MAIN_PANE.widthProperty();
        this.DECK_CIV.translateXProperty().bind(screenWidth.divide(-6));
        this.CENTER_PANE.setAlignment(Pos.CENTER);
        this.CENTER_PANE.getChildren().add(this.DECK_CIV);

        this.MAIN_PANE.setCenter(this.CENTER_PANE);
        this.MAIN_PANE.setTop(dealerVH);
        this.MAIN_PANE.setBottom(humanVH);

        this.MAIN_PANE.setBackground(Background.EMPTY);
        Scene scene = new Scene(
                this.MAIN_PANE,
                SharedValues.SCREEN_WIDTH,
                SharedValues.SCREEN_HEIGHT,
                SharedValues.color
        );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupDealer() {
        HBox dealerCardPane = createPlayerHBox();
        this.dealerVH = new DealerVisualHand(dealerCardPane);
        BlackjackPlayer dealerPlayer = BlackjackGUIController.controller.getDealerPlayer();
        this.setupPlayer(dealerPlayer, this.dealerVH);
    }

    private void setupHuman() {
        HBox humanCardPane = createPlayerHBox();
        this.humanVH = new BlackjackVisualHand(humanCardPane);
        BlackjackPlayer humanPlayer = BlackjackGUIController.controller.getHumanPlayer();
        this.setupPlayer(humanPlayer, this.humanVH);
    }

    private HBox createPlayerHBox() {
        double spacing = this.deckImage.getWidth() * -0.33;
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        return hbox;
    }

    private void setupPlayer(BlackjackPlayer player, BlackjackVisualHand visualHand) {
        visualHand.setMinHeight(deckImage.getHeight());
        ICardsetListener<Card> cardListener = visualHand.getListener();
        IChangeListener<Integer> scoreListener = visualHand.getScoreListener();
        IChangeListener<Integer> tokenListener = visualHand.getTokenListener();

        player.addCardsetListener(cardListener);
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

    public int getBidClicked(int availableTokens) {
        this.addNodeToCenter(this.BID_PANEL);
        int bid = this.BID_PANEL.getBidClicked(availableTokens);
        this.removeNodeFromCenter(this.BID_PANEL);

        return bid;
    }

    public void getContinue() {
        super.getContinue(() -> dealerVH.hideStatus());
    }

    public void showHandWinner(BlackjackPlayer player) {
        super.showHandWinner(player, () -> dealerVH.showEverything());
    }

    public void showGameWinner(BlackjackPlayer player) {
        super.showGameWinner(player, () -> dealerVH.showEverything());
    }

}
