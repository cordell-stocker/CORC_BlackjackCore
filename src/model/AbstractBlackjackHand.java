package model;

import standard.Card;

/**
 * Represents a player's hand.
 * <p>
 * For this project, there is only ever the need to:
 * 1) add a Card to the hand.
 * 2) clear (remove) all Cards from the hand.
 * 3) calculate the score of the hand.
 * 4) get the number of Cards in the hand.
 * 5) any functionality that aids in the above items.
 */
public abstract class AbstractBlackjackHand {

    /**
     * For student to implement.
     * <p>
     * SHOULD add the given card into the next empty index.
     * <p>
     * Another solution is to keep track of the next empty
     * slot as a class variable.
     *
     * @param card Card to be added.
     */
    public abstract void addCard(Card card);

    /**
     * For student to implement.
     * <p>
     * MUST set all indexes in the hand to null.
     */
    public abstract void clearCards();

    /**
     * For student to implement.
     * <p>
     * MUST return the number of Cards in the hand.
     *
     * @return number of Cards in the hand.
     */
    public abstract int getCardCount();

    /**
     * For student to implement.
     * <p>
     * Calculates the score of the hand, and
     * returns that value.
     * <p>
     * This is a stateless implementation.
     * Students are allowed to add attributes to
     * this class to be stateful if desired.
     *
     * @return the score of this hand.
     */
    public abstract int getHandScore();

    /**
     * For student to implement.
     * <p>
     * Calculates the value of a single Card, and returns
     * that value.
     * <p>
     * There are many, many ways to implement this method.
     * The students' go-to will most likely be a chain of
     * if-elseif statements. Some may use a switch statement.
     * If they read javadoc for how standard.Face is
     * implemented, they may manage this way.
     *
     * @param card the Card to get the value of.
     * @return The value of the Card.
     */
    public abstract int getCardValue(Card card);

    /**
     * For student to implement.
     *
     * @return the stored Cards.
     */
    public abstract Card[] getCards();
}
