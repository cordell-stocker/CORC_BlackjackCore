package blackjackcore.view;

import corc.javafxextend.standard.VisualHand;
import corc.standard.Card;
import corc.structure.ICardsetListener;
import corc.structure.IChangeListener;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import blackjackcore.model.AbstractBlackjackController;
import blackjackcore.model.player.PlayerWithScoreAndTokens;

public class BlackjackKElevensGUIController extends AbstractGUIController {

    private static AbstractBlackjackController controller;

    public static void setGameController(AbstractBlackjackController controller) {
        BlackjackKElevensGUIController.controller = controller;
    }

    public static void launchGUI() {
        BlackjackKElevensGUIController.launch();
    }

    private final BorderPane MAIN_PANE = new BorderPane();
    private final BidPanel BID_PANEL = new BidPanel();
    private final ActionPanel ACTION_PANEL = new ActionPanel();
    private Image deckImage = this.DECK_CIV.getImage();
    private DealerVisualHand dealerVH;

    @Override
    public void start(Stage primaryStage) {
        BlackjackKElevensGUIController.controller.setGuiController(this);

        this.addStartButton(() -> new Thread(() -> BlackjackKElevensGUIController.controller.play()));

        constructGUIAndStart(primaryStage, MAIN_PANE);
    }

    @Override
    VisualHand setupDealer() {
        HBox dealerCardPane = createPlayerHBox();
        this.dealerVH = new DealerVisualHand(dealerCardPane);
        PlayerWithScoreAndTokens dealerPlayer = BlackjackKElevensGUIController.controller.getDealerPlayer();
        this.setupPlayer(dealerPlayer, this.dealerVH);
        return this.dealerVH;
    }

    @Override
    VisualHand setupHuman() {
        HBox humanCardPane = createPlayerHBox();
        BlackjackVisualHand humanVH = new BlackjackVisualHand(humanCardPane);
        PlayerWithScoreAndTokens humanPlayer = BlackjackKElevensGUIController.controller.getHumanPlayer();
        this.setupPlayer(humanPlayer, humanVH);
        return humanVH;
    }

    private HBox createPlayerHBox() {
        double spacing = this.deckImage.getWidth() * -0.33;
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        return hbox;
    }

    private void setupPlayer(PlayerWithScoreAndTokens player, BlackjackVisualHand visualHand) {
        visualHand.setMinHeight(deckImage.getHeight());
        ICardsetListener<Card> cardListener = visualHand.getCardsetListener();
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

    public int getBidClicked() {
        this.addNodeToCenter(this.BID_PANEL);
        int bid = this.BID_PANEL.getBidClicked();
        this.removeNodeFromCenter(this.BID_PANEL);

        return bid;
    }

    public void getContinue() {
        super.getContinue(() -> dealerVH.hideStatus());
    }

    public void showHandWinner(PlayerWithScoreAndTokens player) {
        super.showHandWinner(player, () -> dealerVH.showEverything());
    }

    public void showGameWinner(PlayerWithScoreAndTokens player) {
        super.showGameWinner(player, () -> dealerVH.showEverything());
    }

}
