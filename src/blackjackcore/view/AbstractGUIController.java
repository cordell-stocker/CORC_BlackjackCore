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

import corc.javafxextend.standard.CardImageView;
import corc.javafxextend.standard.VisualHand;
import corc.javafxextend.structure.GUIController;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import blackjackcore.model.player.AbstractPlayer;

public abstract class AbstractGUIController extends GUIController {

    private static String title = "";

    public static void setTitle(String title) {
        AbstractGUIController.title = title;
    }

    final StackPane CENTER_PANE = new StackPane();
    private final PlayPanel PLAY_PANEL = new PlayPanel();
    private final WinnerDisplay WINNER_DISPLAY = new WinnerDisplay();
    private final ContinuePanel CONTINUE_PANEL = new ContinuePanel();
    final CardImageView DECK_CIV = new CardImageView();
    private volatile boolean displayingWinner;

    abstract VisualHand setupDealer();
    abstract VisualHand setupHuman();

    void constructGUIAndStart(Stage stage, BorderPane pane) {
        VisualHand dealerVH = this.setupDealer();
        VisualHand humanVH = this.setupHuman();
        this.updateDeckLocation(pane);
        this.updateWinnerDisplayLocation(pane);
        this.updateMainPaneAndCenterPane(pane, dealerVH, humanVH);

        Scene scene = new Scene(
                pane,
                SharedValues.SCREEN_WIDTH,
                SharedValues.SCREEN_HEIGHT,
                SharedValues.color
        );
        stage.setTitle(AbstractGUIController.title);
        stage.setScene(scene);
        stage.show();
    }

    private void updateWinnerDisplayLocation(Pane pane) {
        ReadOnlyDoubleProperty screenHeight = pane.heightProperty();
        this.WINNER_DISPLAY.translateYProperty().bind(screenHeight.divide(-5));
    }

    private void updateDeckLocation(Pane pane) {
        ReadOnlyDoubleProperty screenWidth = pane.widthProperty();
        this.DECK_CIV.translateXProperty().bind(screenWidth.divide(-6));
    }

    private void updateMainPaneAndCenterPane(BorderPane pane, VisualHand dealerVH, VisualHand humanVH) {
        this.CENTER_PANE.setAlignment(Pos.CENTER);
        this.CENTER_PANE.getChildren().add(this.DECK_CIV);

        pane.setBackground(Background.EMPTY);
        pane.setCenter(this.CENTER_PANE);
        pane.setTop(dealerVH);
        pane.setBottom(humanVH);
    }

    void addStartButton(ModelStarter starter) {
        Button start = new Button("Start Game");
        this.addNodeToCenter(start);
        start.setOnAction(e -> {
            Thread gameThread = starter.makeGameThread();
            gameThread.setName(title + " Model Thread");
            gameThread.setDaemon(true);
            gameThread.start();
            this.removeNodeFromCenter(start);
        });
    }

    public Boolean getPlayGame(CodeWrapper code) {
        Platform.runLater(code::run);
        this.addNodeToCenter(this.PLAY_PANEL);
        PlayPanel.Result option = this.PLAY_PANEL.getSelectedOption();
        this.removeNodeFromCenter(this.PLAY_PANEL);

        return option.equals(PlayPanel.Result.PLAY_GAME);
    }

    public void getContinue(CodeWrapper code) {
        this.addNodeToCenter(this.CONTINUE_PANEL);
        this.CONTINUE_PANEL.getContinue();
        this.removeNodeFromCenter(this.CONTINUE_PANEL);
        Platform.runLater(code::run);
    }

    void showHandWinner(AbstractPlayer player, CodeWrapper code) {
        GUIController.waitOnGUI();
        displayingWinner = true;
        Platform.runLater(() -> {
            WINNER_DISPLAY.setHandWinner(player);
            code.run();
            addNodeToCenter(WINNER_DISPLAY);
            displayingWinner = false;
        });

        this.waitThenRemoveWinnerDisplayFromCenter();
    }

    void showGameWinner(AbstractPlayer player, CodeWrapper code) {
        GUIController.waitOnGUI();
        displayingWinner = true;
        Platform.runLater(() -> {
            WINNER_DISPLAY.setGameWinner(player);
            code.run();
            addNodeToCenter(WINNER_DISPLAY);
            displayingWinner = false;
        });

        this.waitThenRemoveWinnerDisplayFromCenter();
    }

    private void waitThenRemoveWinnerDisplayFromCenter() {
        final long SLEEP_TIME = 100; // millis
        final long SHOW_WINNER_DISPLAY_TIME = 2000; // millis

        // Wait until winner display is updated and shown.
        while (displayingWinner) {
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

    void addNodeToCenter(Node child) {
        this.addNodeOnPlatformThread(this.CENTER_PANE, child);
    }

    void removeNodeFromCenter(Node child) {
        this.removeNodeOnPlatformThread(this.CENTER_PANE, child);
    }

}
