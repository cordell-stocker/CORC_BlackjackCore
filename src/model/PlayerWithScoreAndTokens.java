package model;

import standard.Cardset;
import structure.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerWithScoreAndTokens extends PlayerWithScore {

	private final List<IChangeListener<Integer>> TOKEN_LISTENERS = new ArrayList<>();

	private int TOKENS = 0;

	public PlayerWithScoreAndTokens(String name, Cardset cardset) {
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

	public void addTokenListener(IChangeListener<Integer> listener) {
		this.TOKEN_LISTENERS.add(listener);
		this.setTokens(this.TOKENS);
	}

	public void removeTokenListener(IChangeListener<Integer> listener) {
		this.TOKEN_LISTENERS.remove(listener);
	}

}
