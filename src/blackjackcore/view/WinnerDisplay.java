package blackjackcore.view;

import javafx.scene.control.Label;
import blackjackcore.model.player.AbstractPlayer;

@SuppressWarnings({"FieldCanBeLocal"})
class WinnerDisplay extends Label {

    private final double FONT_SIZE = 50;
    private final String FONT = "Tohma";

    WinnerDisplay() {
        this.setStyle("-fx-font: " + this.FONT_SIZE + "px " + this.FONT + ";" +
                "-fx-font-weight: BOLD;" +
                "-fx-background-color: WHITE;"
        );
    }

    void setHandWinner(AbstractPlayer player) {
        String text = player.getName() + " Won!";
        this.setText(text);
    }

    void setGameWinner(AbstractPlayer player) {
        String text = player.getName() + " Won The Game!";
        this.setText(text);
    }
}
