package highcard;

import corc.standard.Card;
import corc.standard.Deck;
import corc.standard.Face;
import blackjackcore.model.AbstractHighCardController;
import blackjackcore.model.player.HighCardPlayer;

public class HighCardController extends AbstractHighCardController {

	private final Deck DECK;
	private final HighCardPlayer HUMAN;
	private final HighCardPlayer DEALER;

	public HighCardController(HighCardPlayer human, HighCardPlayer dealer, Deck deck) {
		this.HUMAN = human;
		this.DEALER = dealer;
		this.DECK = deck;
	}

	@Override
	public void playGame() {
		// Given. Do not touch ===========================
		HighCardController controller = this;           //
		HighCardPlayer human = this.HUMAN;      //
		HighCardPlayer dealer = this.DEALER;    //
		Deck deck = this.DECK;                          //
		// ===============================================
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

			HighCardPlayer winner;
			if (humanScore > dealerScore) {
				winner = human;
			} else {
				winner = dealer;
			}

			controller.setWinner(winner);

			playing = controller.getPlayGame();
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

	@Override
	public boolean getPlayGame() {
		return super.getPlayGame();
	}

	@Override
	public void setWinner(HighCardPlayer player) {
		super.setWinner(player);
	}

	@Override
	public void exitGame() {
		super.exitGame();
	}

	@Override
	public HighCardPlayer getHumanPlayer() {
		return this.HUMAN;
	}

	@Override
	public HighCardPlayer getDealerPlayer() {
		return this.DEALER;
	}
}
