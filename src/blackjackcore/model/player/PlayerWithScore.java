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
 * A player with a Score as well as everything
 * that an {@link AbstractPlayer} does.
 */
public abstract class PlayerWithScore extends AbstractPlayer {

	private final List<IChangeListener<Integer>> SCORE_LISTENERS = new ArrayList<>();
	private int SCORE = 0;

	/**
	 * Creates a player with a Score as well
	 * as everything from {@link AbstractPlayer}.
	 *
	 * @param name    the name of this player.
	 * @param cardset the {@link Cardset} this player uses.
	 */
	PlayerWithScore(String name, Cardset cardset) {
		super(name, cardset);
	}

	/**
	 * @return this player's score.
	 */
	public int getScore() {
		return this.SCORE;
	}

	/**
	 * @param value the new score for this player.
	 */
	public void setScore(int value) {
		this.fireScoreListeners(this.SCORE, value);
		this.SCORE = value;
	}

	private void fireScoreListeners(int oldScore, int newScore) {
		for (IChangeListener<Integer> listener : this.SCORE_LISTENERS) {
			listener.valueChanged(oldScore, newScore);
		}
	}

	/**
	 * Adds an {@link IChangeListener<Integer>} to this player's Score.
	 *
	 * @param listener the IChangeListener to be called whenever there
	 *                 is a change to the Score.
	 */
	public void addScoreListener(IChangeListener<Integer> listener) {
		this.SCORE_LISTENERS.add(listener);
		this.setScore(this.SCORE);
	}

	/**
	 * Removes an {@link IChangeListener<Integer>} from this player's Score.
	 *
	 * @param listener the IChangeListener to remove from the Score.
	 */
	public void removeScoreListener(IChangeListener<Integer> listener) {
		this.SCORE_LISTENERS.remove(listener);
	}

}
