package model;

import standard.Cardset;
import structure.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerWithScore extends AbstractPlayer{

	private final List<IChangeListener<Integer>> SCORE_LISTENERS = new ArrayList<>();

	private int SCORE = 0;

	public PlayerWithScore(String name, Cardset cardset) {
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

	public void addScoreListener(IChangeListener<Integer> listener) {
		this.SCORE_LISTENERS.add(listener);
		this.setScore(this.SCORE);
	}

	public void removeScoreListener(IChangeListener<Integer> listener) {
		this.SCORE_LISTENERS.remove(listener);
	}

}
