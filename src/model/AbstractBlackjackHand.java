package model;

import standard.Card;

public abstract class AbstractBlackjackHand {

    /**
     * For student to implement.
     *
     * @param card the card to be added.
     */
    public abstract void addCard(Card card);

    /**
     * For student to implement.
     *
     * Needs to set all indexes in this.hand to null.
     */
    public abstract void clearCards();

    /**
     * For student to implement.
     *
     * @return number of non-null Cards in this.hand.
     */
    public abstract int getCardCount();

    /**
     * For student to implement.
     *
     * Should be called whenever a Card is added.
     *
     * This is a stateless implementation.
     * Students are allowed to add attributes to
     * this class to be stateful if desired.
     */
    public abstract int getHandScore();

    /**
     * For student to implement.
     *
     * There are many, many ways to implement this method.
     * The students' go-to will most likely be a chain of if-elseif statements.
     * Some may use a switch statement.
     * If they read javadoc for how standard.Face is implemented, they may manage this way.
     *
     * @param card Card to get the value from.
     * @return The value of the card parameter.
     */
    public abstract int getCardValue(Card card);

    /**
     * For student to implement.
     *
     * @return the stored Cards.
     */
    public abstract Card[] getCards();
}
