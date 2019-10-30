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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import blackjackcore.model.AbstractHighCardController;
import blackjackcore.model.player.HighCardPlayer;

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
        ICardsetListener<Card> cardListener = visualHand.getCardsetListener();
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
