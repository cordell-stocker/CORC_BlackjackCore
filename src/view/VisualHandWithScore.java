package view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafxextend.standard.VisualHand;
import structure.IChangeListener;

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
