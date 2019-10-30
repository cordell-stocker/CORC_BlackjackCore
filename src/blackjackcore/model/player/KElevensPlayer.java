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

import corc.standard.Card;
import corc.standard.Cardset;

/**
 * A player with basic functionality needed
 * for playing a game of KElevens.
 */
public class KElevensPlayer extends PlayerWithScoreAndTokens {

    private final Cardset CARDSET;

    /**
     * Creates a player with basic functionality needed
     * for playing a game of KElevens.
     *
     * @param name    the name of this player.
     * @param cardset the {@link Cardset} this player uses.
     */
    public KElevensPlayer(String name, Cardset cardset) {
        super(name, cardset);
        this.CARDSET = cardset;
    }

    /**
     * Adds the given {@link Card} to this player's {@link Cardset}.
     *
     * @param card the Card to be added to this player's Cardset.
     */
    public void addCard(Card card) {
        this.CARDSET.addCard(card);
    }

    /**
     * Removes all {@link Card}s from this player's {@link Cardset}.
     */
    public void clearCards() {
        this.CARDSET.clear();
    }

    /**
     * Returns the total number of {@link Card}s in this player's {@link Cardset}.
     *
     * @return number of Cards in the Cardset.
     */
    public int getCardCount() {
        return this.CARDSET.size();
    }
}
