package view;

import javafx.scene.control.Label;
import model.BlackjackPlayer;

@SuppressWarnings({"FieldCanBeLocal", "SpellCheckingInspection"})
public class WinnerDisplay extends Label {

    private final double FONT_SIZE = 80;
    private final String FONT = "Tohma";

    WinnerDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("-fx-font: ").append(this.FONT_SIZE).append("px ").append(this.FONT);
        this.setStyle(sb.toString());
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
