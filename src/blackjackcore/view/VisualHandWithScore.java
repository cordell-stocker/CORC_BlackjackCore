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
import corc.structure.IChangeListener;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class VisualHandWithScore extends VisualHand {

    private final GridPane GRID = new GridPane();
    private final Label SCORE_VALUE_LABEL = new Label("<no value>");
    private final IChangeListener<Integer> SCORE_LISTENER =
            (oldVal, newVal) -> Platform.runLater(() -> this.SCORE_VALUE_LABEL.setText(newVal.toString()));

    public VisualHandWithScore(Pane handPane) {
        super(handPane);
        this.setRight(this.GRID);
        this.setupGrid();
    }

    private void setupGrid() {
        Label scoreLabel = new Label("Score:");
        double height = scoreLabel.getHeight();

        this.GRID.add(scoreLabel, 0, 0);
        this.GRID.add(this.SCORE_VALUE_LABEL, 1, 0);
        this.GRID.setAlignment(Pos.CENTER);
        this.GRID.setStyle(SharedValues.GRID_PANE_CSS);
        this.GRID.setMaxHeight(height);
    }

    IChangeListener<Integer> getScoreListener() {
        return this.SCORE_LISTENER;
    }
}
