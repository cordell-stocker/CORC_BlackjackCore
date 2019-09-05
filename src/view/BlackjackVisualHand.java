package view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafxextend.standard.VisualHand;

class BlackjackVisualHand extends VisualHand {

    private final GridPane GRID = new GridPane();
    private final Label SCORE = new Label("0");
    private final Label TOKENS = new Label("0");
    private ChangeListener<Number> scoreListener =
            ((observableValue, oldVal, newVal) -> Platform.runLater(() -> this.SCORE.setText("" + newVal)));
    private ChangeListener<Number> tokenListener =
            ((observableValue, oldVal, newVal) -> Platform.runLater(() -> this.TOKENS.setText("" + newVal)));

    BlackjackVisualHand(Pane handPane) {
        super(handPane);
        this.setRight(this.GRID);

        Label score = new Label("Score: ");
        Label tokens = new Label("Tokens: ");

        double height = score.getHeight() + tokens.getHeight();

        this.GRID.add(score, 0, 0);
        this.GRID.add(tokens, 0, 1);
        this.GRID.add(this.SCORE, 1, 0);
        this.GRID.add(this.TOKENS, 1, 1);
        this.GRID.setAlignment(Pos.CENTER);
        this.GRID.setStyle("-fx-background-color: WHITE; -fx-padding: 3px");
        this.GRID.setMaxHeight(height);
    }

    ChangeListener<Number> getScoreListener() {
        return this.scoreListener;
    }

    ChangeListener<Number> getTokenListener() {
        return this.tokenListener;
    }

    protected void setScoreVisibility(boolean visibility) {
        this.SCORE.setVisible(visibility);
    }

}
