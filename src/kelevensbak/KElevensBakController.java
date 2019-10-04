package kelevensbak;

import model.AbstractKElevensController;
import model.KElevensPlayer;
import standard.Card;
import standard.Deck;
import standard.Face;

public class KElevensBakController extends AbstractKElevensController<KElevensPlayer> {

	private final Deck DECK;
	private final KElevensPlayer HUMAN;
	private final KElevensPlayer DEALER;

	public KElevensBakController(KElevensPlayer human, KElevensPlayer dealer, Deck deck) {
		this.DECK = deck;
		this.HUMAN = human;
		this.DEALER = dealer;
	}

	@Override
	public void playGame() {
		// Given. Do not touch =========================================
		KElevensBakController controller = this;                       // =
		Deck deck = this.DECK;                  // =
		KElevensPlayer human = this.HUMAN;        // =
		KElevensPlayer dealer = this.DEALER;  // =
		// =============================================================

		boolean playing = true;

		while (playing) {
			deck.reset();
			deck.shuffle();

			controller.doHumanTurn(controller, deck, human);

			controller.doDealerTurn(controller, deck, dealer);

			KElevensPlayer winner;
			if (human.getScore() > dealer.getScore()) {
				winner = human;
			} else {
				winner = dealer;
			}
			controller.setHandWinner(winner);
			controller.getContinue();

			human.clearCards();
			dealer.clearCards();
			human.setScore(0);
			dealer.setScore(0);

			playing = controller.getPlayGame();
		}
		controller.exitGame();
	}

	private void doHumanTurn(KElevensBakController controller, Deck deck, KElevensPlayer human) {
		Card humanCard = deck.drawCard();
		human.addCard(humanCard);
		human.setScore(human.getScore() + controller.getCardValue(humanCard));

		String action = controller.getActionClicked();
		if (action.equals("HIT")) {
			Card humanCard2 = deck.drawCard();
			human.addCard(humanCard2);
			human.setScore(human.getScore() + controller.getCardValue(humanCard2));

			if (human.getScore() > 11) {
				human.setScore(human.getScore() - 11);
			}
		}
	}

	private void doDealerTurn(KElevensBakController controller, Deck deck, KElevensPlayer dealer) {
		Card dealerCard = deck.drawCard();
		dealer.addCard(dealerCard);
		dealer.setScore(dealer.getScore() + controller.getCardValue(dealerCard));

		if (dealer.getScore() < 3) {
			Card dealerCard2 = deck.drawCard();
			dealer.addCard(dealerCard2);
			dealer.setScore(dealer.getScore() + controller.getCardValue(dealerCard2));

			if (dealer.getScore() > 11) {
				dealer.setScore(dealer.getScore() - 11);
			}
		}
	}

	private int getCardValue(Card card) {
		Face face = card.getFace();
		switch (face.getName()) {
			case "ACE":
				return 1;
			case "TWO":
				return 2;
			case "THREE":
				return 3;
			case "FOUR":
				return 4;
			case "FIVE":
				return 5;
			case "SIX":
				return 6;
			case "SEVEN":
				return 7;
			case "EIGHT":
				return 8;
			case "NINE":
				return 9;
			default:
				return 10;
		}
	}

	@Override
	public KElevensPlayer getHumanPlayer() {
		return this.HUMAN;
	}

	@Override
	public KElevensPlayer getDealerPlayer() {
		return this.DEALER;
	}
}
