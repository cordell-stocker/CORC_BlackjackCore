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
import corc.standard.Deck;
import blackjackcore.model.AbstractBlackjackController;
import blackjackcore.model.AbstractBlackjackHand;

/**
 * A player for playing a game of Blackjack.
 */
public abstract class BlackjackPlayer<C extends AbstractBlackjackController> extends PlayerWithScoreAndTokens {

    private final Cardset CARDSET;

    /**
     * Creates a player for playing a game of Blackjack.
     */
    public BlackjackPlayer(String name, Cardset cardset) {
        super(name, cardset);
        this.CARDSET = cardset;
    }

    protected void bindBlackjackHand(AbstractBlackjackHand hand) {
        this.CARDSET.bind(hand.getCards());
    }

    /**
     * For student to implement.
     * <p>
     * SHOULD prevent the player from playing once
     * their score goes over 21 (end turn).
     * <p>
     * MUST contain all logic needed for hitting or staying.
     *
     * @param controller the game controller.
     * @param deck       the deck used in the game.
     */
    public abstract void takeTurn(C controller, Deck deck);

    /**
     * For the student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#addCard(Card)} method.
     * <p>
     * SHOULD update this player's score.
     *
     * @param card the Card to be added to this player's hand.
     */
    public abstract void addCard(Card card);

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#clearCards()} method.
     * <p>
     * SHOULD set this player's score back to 0.
     */
    public abstract void clearCards();

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#getCardCount()} method.
     *
     * @return number of Cards in the hand.
     */
    public abstract int getCardCount();

    /**
     * For student to implement.
     * <p>
     * MUST remove tokens, and return the amount removed.
     *
     * @return the amount this player is bidding.
     */
    public abstract int bid(C controller);

}
