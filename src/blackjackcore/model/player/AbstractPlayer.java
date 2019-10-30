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
import corc.structure.ICardsetListener;

/**
 * A general player that has a name and a {@link Cardset}.
 * <p>
 * A subclass will need to store a local reference of the Cardset,
 * given to the constructor, in order to modify the Cardset.
 */
public abstract class AbstractPlayer {

    private final String NAME;
    private final Cardset CARDSET;

    /**
     * Creates a player object that has a name and a {@link Cardset}.
     *
     * @param name    the name for this player.
     * @param cardset the Cardset this player uses.
     */
    AbstractPlayer(String name, Cardset cardset) {
        this.NAME = name;
        this.CARDSET = cardset;
    }

    /**
     * @return the name of this player.
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Adds an {@link ICardsetListener<Card>} to this player's stored {@link Cardset}.
     *
     * @param listener the ICardsetListener to be called whenever there
     *                 is a change to the stored Cardset.
     */
    public void addCardsetListener(ICardsetListener<Card> listener) {
        this.CARDSET.addCardsetListener(listener);
    }

    /**
     * Removes an {@link ICardsetListener<Card>} from this player's stored {@link Cardset}.
     *
     * @param listener the ICardsetListener to remove from the stored Cardset.
     */
    public void removeCardsetListener(ICardsetListener<Card> listener) {
        this.CARDSET.removeCardsetListener(listener);
    }
}
