package view;

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
import model.AbstractHighCardController;
import model.HighCardKElevensPlayer;
import standard.Card;
import structure.ICardsetListener;
import structure.IChangeListener;

public class HighCardKElvensGUIController extends AbstractGUIController {

    private static AbstractHighCardController controller;

    public static void setGameController(AbstractHighCardController controller) {
        HighCardKElvensGUIController.controller = controller;
    }

    public static void launchGUI() {
        BlackjackGUIController.launch();
    }

    private final CardImageView DECK_CIV = new CardImageView();
    private Image deckImage = this.DECK_CIV.getImage();
    private VisualHandWithScore dealerVH;
    private VisualHandWithScore humanVH;
    private final BorderPane MAIN_PANE = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {
        HighCardKElvensGUIController.controller.setGuiController(this);

        Button start = new Button("Start Game");
        this.addNodeToCenter(start);
        start.setOnAction(e -> {
            Thread gameThread = new Thread(() -> HighCardKElvensGUIController.controller.play());
            gameThread.setDaemon(true);
            gameThread.start();
            this.removeNodeFromCenter(start);
        });

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
        stage.setScene(scene);
        stage.show();
    }

    private void setupDealer() {
        HBox dealerCardPane = createPlayerHBox();
        this.dealerVH = new VisualHandWithScore(dealerCardPane);
        HighCardKElevensPlayer dealerPlayer = HighCardKElvensGUIController.controller.getDealerPlayer();
        this.setupPlayer(dealerPlayer, this.dealerVH);
    }

    private void setupHuman() {
        HBox humanCardPane = createPlayerHBox();
        this.humanVH = new VisualHandWithScore(humanCardPane);
        HighCardKElevensPlayer humanPlayer = HighCardKElvensGUIController.controller.getHumanPlayer();
        this.setupPlayer(humanPlayer, this.humanVH);
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
