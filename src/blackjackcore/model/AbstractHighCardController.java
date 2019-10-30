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
package blackjackcore.model;

import blackjackcore.model.player.HighCardPlayer;
import blackjackcore.view.HighCardGUIController;

public abstract class AbstractHighCardController extends AbstractGameController{

    private HighCardGUIController gui;

    public void play() {
        this.playGame();
    }

    public abstract void playGame();

    public boolean getPlayGame() {
        return gui.getPlayGame();
    }

    public void setWinner(HighCardPlayer player) {
        gui.showHandWinner(player);
    }

    public abstract HighCardPlayer getHumanPlayer();

    public abstract HighCardPlayer getDealerPlayer();

    public void setGuiController(HighCardGUIController gui) {
        this.gui = gui;
    }
}
