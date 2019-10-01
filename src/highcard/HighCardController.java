package highcard;

import model.AbstractHighCardController;
import model.HighCardKElevensPlayer;
import standard.Card;
import standard.Deck;
import standard.Face;

public class HighCardController extends AbstractHighCardController {

    private final Deck DECK;
    private final HighCardKElevensPlayer HUMAN;
    private final HighCardKElevensPlayer DEALER;

    public HighCardController(HighCardKElevensPlayer human, HighCardKElevensPlayer dealer, Deck deck) {
        this.HUMAN = human;
        this.DEALER = dealer;
        this.DECK = deck;
    }

    @Override
    public void playGame() {
        HighCardController controller = this;
        HighCardKElevensPlayer human = this.HUMAN;
        HighCardKElevensPlayer dealer = this.DEALER;
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

            HighCardKElevensPlayer winner;
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
        int value;
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
        return super.getPlayAgain();
    }

    public void setWinner(HighCardKElevensPlayer player) {
        super.setWinner(player);
    }

    @Override
    public void exitGame() {
        super.exitGame();
    }

    @Override
    public HighCardKElevensPlayer getHumanPlayer() {
        return this.HUMAN;
    }

    @Override
    public HighCardKElevensPlayer getDealerPlayer() {
        return this.DEALER;
    }
}
