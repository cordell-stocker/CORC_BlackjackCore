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
