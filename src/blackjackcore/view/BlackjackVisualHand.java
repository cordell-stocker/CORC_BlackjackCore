package blackjackcore.view;

import corc.javafxextend.standard.VisualHand;
import corc.structure.IChangeListener;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

class BlackjackVisualHand extends VisualHand {

    private final GridPane GRID = new GridPane();
    private final Label SCORE = new Label("0");
    private final Label TOKENS = new Label("0");
    private IChangeListener<Integer> scoreListener = new IChangeListener<>() {
        @Override
        public synchronized void valueChanged(Integer oldVal, Integer newVal) {
            Platform.runLater(() -> SCORE.setText("" + newVal));
        }
    };
    private IChangeListener<Integer> tokenListener =new IChangeListener<>() {
        @Override
        public synchronized void valueChanged(Integer oldVal, Integer newVal) {
            Platform.runLater(() -> TOKENS.setText("" + newVal));
        }
    };

    BlackjackVisualHand(Pane handPane) {
        super(handPane);
        this.setRight(this.GRID);
        this.setupGrid();
    }

    private void setupGrid() {
        Label score = new Label("Score: ");
        Label tokens = new Label("Tokens: ");

        double height = score.getHeight() + tokens.getHeight();

        this.GRID.add(score, 0, 0);
        this.GRID.add(tokens, 0, 1);
        this.GRID.add(this.SCORE, 1, 0);
        this.GRID.add(this.TOKENS, 1, 1);
        this.GRID.setAlignment(Pos.CENTER);
        this.GRID.setStyle(SharedValues.GRID_PANE_CSS);
        this.GRID.setMaxHeight(height);
    }

    IChangeListener<Integer> getScoreListener() {
        return this.scoreListener;
    }

    IChangeListener<Integer> getTokenListener() {
        return this.tokenListener;
    }

    void setScoreVisibility(boolean visibility) {
        this.SCORE.setVisible(visibility);
    }

}
