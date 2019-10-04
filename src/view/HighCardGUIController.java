package view;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafxextend.standard.VisualHand;
import model.AbstractHighCardController;
import model.HighCardPlayer;
import standard.Card;
import structure.ICardsetListener;
import structure.IChangeListener;

public class HighCardGUIController extends AbstractGUIController {

    private static AbstractHighCardController controller;

    public static void setGameController(AbstractHighCardController controller) {
        HighCardGUIController.controller = controller;
    }

    public static void launchGUI() {
        BlackjackKElevensGUIController.launch();
    }

    private final BorderPane MAIN_PANE = new BorderPane();
    private Image deckImage = this.DECK_CIV.getImage();

    @Override
    public void start(Stage stage) {
        HighCardGUIController.controller.setGuiController(this);

        this.addStartButton(() -> new Thread(() -> HighCardGUIController.controller.play()));

        this.constructGUIAndStart(stage, MAIN_PANE);
    }

    @Override
    VisualHand setupDealer() {
        HBox dealerCardPane = createPlayerHBox();
        VisualHandWithScore dealerVH = new VisualHandWithScore(dealerCardPane);
        HighCardPlayer dealerPlayer = HighCardGUIController.controller.getDealerPlayer();
        this.setupPlayer(dealerPlayer, dealerVH);
        return dealerVH;
    }

    @Override
    VisualHand setupHuman() {
        HBox humanCardPane = createPlayerHBox();
        VisualHandWithScore humanVH = new VisualHandWithScore(humanCardPane);
        HighCardPlayer humanPlayer = HighCardGUIController.controller.getHumanPlayer();
        this.setupPlayer(humanPlayer, humanVH);
        return humanVH;
    }

    private HBox createPlayerHBox() {
        double spacing = this.deckImage.getWidth() * -0.33;
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        return hbox;
    }

    private void setupPlayer(HighCardPlayer player, VisualHandWithScore visualHand) {
        visualHand.setMinHeight(deckImage.getHeight());
        ICardsetListener<Card> cardListener = visualHand.getListener();
        IChangeListener<Integer> scoreListener = visualHand.getScoreListener();

        player.addCardsetListener(cardListener);
        player.addScoreListener(scoreListener);
    }

    public void showHandWinner(HighCardPlayer player) {
        super.showHandWinner(player, () -> {
            // do nothing
        });
    }
}
