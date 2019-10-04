package model;

import standard.Card;
import standard.Cardset;

public class KElevensPlayer extends PlayerWithScoreAndTokens {

	private final Cardset CARDSET;

	public KElevensPlayer(String name, Cardset cardset) {
		super(name, cardset);
		this.CARDSET = cardset;
	}

	@Override
	public void addCard(Card card) {
		this.CARDSET.addCard(card);
	}

	@Override
	public void clearCards() {
		this.CARDSET.clear();
	}

	@Override
	public int getCardCount() {
		return this.CARDSET.size();
	}
}
