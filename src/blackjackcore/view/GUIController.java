/*
Copyright 2019, Cordell Stocker (cordellstocker@gmail.com)
All rights reserved.

This file is part of CORC BlackjackCore.

    CORC BlackjackCore is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    CORC BlackjackCore is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CORC BlackjackCore.  If not, see <https://www.gnu.org/licenses/>.
*/
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

public class GUIController extends AbstractGUIController {

    private static AbstractBlackjackController controller;

    public static void setGameController(AbstractBlackjackController controller) {
        GUIController.controller = controller;
    }

    public static void launchGUI() {
        GUIController.launch();
    }

    private final BorderPane MAIN_PANE = new BorderPane();
    private final BidPanel BID_PANEL = new BidPanel();
    private final ActionPanel ACTION_PANEL = new ActionPanel();
    private Image deckImage = this.DECK_CIV.getImage();
    private DealerVisualHand dealerVH;

    @Override
    public void start(Stage primaryStage) {
        GUIController.controller.setGuiController(this);

        this.addStartButton(() -> new Thread(() -> GUIController.controller.play()));

        constructGUIAndStart(primaryStage, MAIN_PANE);
    }

    @Override
    VisualHand setupDealer() {
        HBox dealerCardPane = createPlayerHBox();
        this.dealerVH = new DealerVisualHand(dealerCardPane);
        PlayerWithScoreAndTokens dealerPlayer = GUIController.controller.getDealerPlayer();
        this.setupPlayer(dealerPlayer, this.dealerVH);
        return this.dealerVH;
    }

    @Override
    VisualHand setupHuman() {
        HBox humanCardPane = createPlayerHBox();
        BlackjackVisualHand humanVH = new BlackjackVisualHand(humanCardPane);
        PlayerWithScoreAndTokens humanPlayer = GUIController.controller.getHumanPlayer();
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
        Platform.runLater(dealerVH::hideStatus);
        this.addNodeToCenter(this.BID_PANEL);
        int bid = this.BID_PANEL.getBidClicked();
        this.removeNodeFromCenter(this.BID_PANEL);

        return bid;
    }

    public boolean getPlayGame() {
        return super.getPlayGame(() -> dealerVH.hideStatus());
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
