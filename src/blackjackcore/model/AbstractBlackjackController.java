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

import blackjackcore.model.player.PlayerWithScoreAndTokens;
import blackjackcore.view.GUIController;

public abstract class AbstractBlackjackController<P extends PlayerWithScoreAndTokens> extends AbstractGameController {

    private GUIController gui;

    public abstract void playGame();

    /**
     * @return either "HIT" or "STAY"
     */
    public String getActionClicked() {
        return this.gui.getActionClicked();
    }

    /**
     * Displays the hand winner.
     *
     * @param player the player who won the hand.
     */
    public void setHandWinner(P player) {
        this.gui.showHandWinner(player);
    }

    /**
     * Displays the game winner.
     *
     * @param player the player who won the game.
     */
    public void setGameWinner(P player) {
        this.gui.showGameWinner(player);
    }

    /**
     * @return
     */
    public boolean getPlayGame() {
        return this.gui.getPlayGame();
    }

    /**
     * Will not allow player to bid more tokens than they have.
     *
     * @return the amount clicked.
     */
    public int getBidClicked() {
        return this.gui.getBidClicked();
    }

    /**
     * Waits for the "Continue" button to be pressed.
     */
    public void getContinue() {
        this.gui.getContinue();
    }

    public abstract P getHumanPlayer();

    public abstract P getDealerPlayer();

    public void setGuiController(GUIController gui) {
        this.gui = gui;
    }

}
