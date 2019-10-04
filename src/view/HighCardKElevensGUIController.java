package view;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafxextend.standard.VisualHand;
import model.AbstractHighCardController;
import model.HighCardKElevensPlayer;
import standard.Card;
import structure.ICardsetListener;
import structure.IChangeListener;

public class HighCardKElevensGUIController extends AbstractGUIController {

    private static AbstractHighCardController controller;

    public static void setGameController(AbstractHighCardController controller) {
        HighCardKElevensGUIController.controller = controller;
    }

    public static void launchGUI() {
        BlackjackGUIController.launch();
    }

    private final BorderPane MAIN_PANE = new BorderPane();
    private Image deckImage = this.DECK_CIV.getImage();

    @Override
    public void start(Stage stage) {
        HighCardKElevensGUIController.controller.setGuiController(this);

        this.addStartButton(() -> new Thread(() -> HighCardKElevensGUIController.controller.play()));

        this.constructGUIAndStart(stage, MAIN_PANE);
    }

    @Override
    VisualHand setupDealer() {
        HBox dealerCardPane = createPlayerHBox();
        VisualHandWithScore dealerVH = new VisualHandWithScore(dealerCardPane);
        HighCardKElevensPlayer dealerPlayer = HighCardKElevensGUIController.controller.getDealerPlayer();
        this.setupPlayer(dealerPlayer, dealerVH);
        return dealerVH;
    }

    @Override
    VisualHand setupHuman() {
        HBox humanCardPane = createPlayerHBox();
        VisualHandWithScore humanVH = new VisualHandWithScore(humanCardPane);
        HighCardKElevensPlayer humanPlayer = HighCardKElevensGUIController.controller.getHumanPlayer();
        this.setupPlayer(humanPlayer, humanVH);
        return humanVH;
    }

    private HBox createPlayerHBox() {
        double spacing = this.deckImage.getWidth() * -0.33;
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        return hbox;
    }

    private void setupPlayer(HighCardKElevensPlayer player, VisualHandWithScore visualHand) {
        visualHand.setMinHeight(deckImage.getHeight());
        ICardsetListener<Card> cardListener = visualHand.getListener();
        IChangeListener<Integer> scoreListener = visualHand.getScoreListener();

        player.addCardsetListener(cardListener);
        player.addScoreListener(scoreListener);
    }

    public void showHandWinner(HighCardKElevensPlayer player) {
        super.showHandWinner(player, () -> {
            // do nothing
        });
    }
}
