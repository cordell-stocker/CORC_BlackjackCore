package highcard;

import model.AbstractBlackjackController;
import model.BlackjackPlayer;
import standard.Card;
import standard.Deck;
import standard.Face;

public class HighCardController extends AbstractBlackjackController {

    private final Deck DECK;
    private final BlackjackPlayer HUMAN;
    private final BlackjackPlayer DEALER;

    public HighCardController(BlackjackPlayer human, BlackjackPlayer dealer, Deck deck) {
        this.HUMAN = human;
        this.DEALER = dealer;
        this.DECK = deck;
    }

    @Override
    public void play() {
        this.playGame();
    }

    @Override
    public void playGame() {
        HighCardController controller = this;
        BlackjackPlayer human = this.HUMAN;
        BlackjackPlayer dealer = this.DEALER;
        Deck deck = this.DECK;

        boolean playing = true;

        while (playing) {
            deck.reset();
            deck.shuffle();

            Card humanCard = deck.drawCard();
            human.addCard(humanCard);
            int humanScore = controller.getCardValue(humanCard);
            human.setScore(humanScore);

            Card dealerCard = deck.drawCard();
            dealer.addCard(dealerCard);
            int dealerScore = controller.getCardValue(dealerCard);
            dealer.setScore(dealerScore);

            BlackjackPlayer winner;
            if (humanScore > dealerScore) {
                winner = human;
            } else {
                winner = dealer;
            }

            controller.setWinner(winner);

            playing = controller.getPlayAgain();
            human.clearCards();
            dealer.clearCards();
        }

        controller.exitGame();
    }

    public int getCardValue(Card card) {
        Face face = card.getFace();
        String faceName = face.getName();
        int value = 0;
        if (faceName.equals("ACE")) {
            value = 11;
        }
        else if (faceName.equals("KING") || faceName.equals("QUEEN") || faceName.equals("JACK")) {
            value = 10;
        }
        else {
            value = face.getValue();
        }
        return value;
    }

    public boolean getPlayAgain() {
        return super.getPlayOptionClicked().equals("PLAY GAME");
    }

    public void setWinner(BlackjackPlayer player) {
        super.setHandWinner(player);
    }

    @Override
    public void exitGame() {
        super.exitGame();
    }

    @Override
    public BlackjackPlayer getHumanPlayer() {
        return this.HUMAN;
    }

    @Override
    public BlackjackPlayer getDealerPlayer() {
        return this.DEALER;
    }
}
