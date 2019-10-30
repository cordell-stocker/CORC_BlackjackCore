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
package blackjackcore.model.player;

import corc.standard.Cardset;
import corc.structure.IChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A player with Tokens as well as everything
 * that a {@link PlayerWithScore} does.
 */
public abstract class PlayerWithScoreAndTokens extends PlayerWithScore {

    private final List<IChangeListener<Integer>> TOKEN_LISTENERS = new ArrayList<>();
    private int TOKENS = 0;

    /**
     * Creates a player with Tokens as well as
     * everything from {@link PlayerWithScore}.
     *
     * @param name    the name of this player.
     * @param cardset the {@link Cardset} this player uses.
     */
    PlayerWithScoreAndTokens(String name, Cardset cardset) {
        super(name, cardset);
    }

    /**
     * @return this player's tokens.
     */
    public int getTokens() {
        return this.TOKENS;
    }

    /**
     * @param value the new tokens for this player.
     */
    public void setTokens(int value) {
        this.fireTokenListeners(this.TOKENS, value);
        this.TOKENS = value;
    }

    private void fireTokenListeners(int oldTokens, int newTokens) {
        for (IChangeListener<Integer> listener : this.TOKEN_LISTENERS) {
            listener.valueChanged(oldTokens, newTokens);
        }
    }

    /**
     * Adds an {@link IChangeListener<Integer>} to this player's Tokens.
     *
     * @param listener the IChangeListener to be called whenever there
     *                 is a change to the Score.
     */
    public void addTokenListener(IChangeListener<Integer> listener) {
        this.TOKEN_LISTENERS.add(listener);
        this.setTokens(this.TOKENS);
    }

    /**
     * Removes an {@link IChangeListener<Integer>} from this player's Tokens.
     *
     * @param listener the IChangeListener to remove from the Tokens.
     */
    public void removeTokenListener(IChangeListener<Integer> listener) {
        this.TOKEN_LISTENERS.remove(listener);
    }

}
