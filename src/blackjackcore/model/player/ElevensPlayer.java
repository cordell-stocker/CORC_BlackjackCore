/*
 * Copyright YEAR, AUTHOR (EMAIL)
 * All rights reserved.
 *
 * This file is part of PROJECT_NAME.
 *
 *     PROJECT_NAME is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     PROJECT_NAME is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with PROJECT_NAME.  If not, see <https://www.gnu.org/licenses/>.
 */

package blackjackcore.model.player;

import blackjackcore.model.AbstractElevensController;
import corc.standard.Card;
import corc.standard.Cardset;
import corc.standard.Deck;

public abstract class ElevensPlayer<C extends AbstractElevensController> extends PlayerWithScoreAndTokens {

	/**
	 * Creates a player with Tokens as well as
	 * everything from {@link PlayerWithScore}.
	 *
	 * @param name    the name of this player.
	 * @param cardset the {@link Cardset} this player uses.
	 */
	public ElevensPlayer(String name, Cardset cardset) {
		super(name, cardset);
	}

	public abstract void takeTurn(C controller, Deck deck);

	public abstract void addCard(Card card);

	public abstract void clearCards();

	public abstract int getHandScore();
}
