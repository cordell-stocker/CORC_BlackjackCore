package view;

import javafx.scene.control.Label;
import model.BlackjackPlayer;

@SuppressWarnings({"FieldCanBeLocal"})
class WinnerDisplay extends Label {

    private final double FONT_SIZE = 60;
    private final String FONT = "Tohma";

    WinnerDisplay() {
        this.setStyle("-fx-font: " + this.FONT_SIZE + "px " + this.FONT);
    }

    void setHandWinner(BlackjackPlayer player) {
        String text = player.getName() + " Won!";
        this.setText(text);
    }

    void setGameWinner(BlackjackPlayer player) {
        String text = player.getName() + " Won The Game!";
        this.setText(text);
    }
}
