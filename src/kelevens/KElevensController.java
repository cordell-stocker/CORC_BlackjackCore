package kelevens;

import corc.standard.*;
import blackjackcore.model.*;
import blackjackcore.model.player.KElevensPlayer;

public class KElevensController extends AbstractKElevensController<KElevensPlayer> {

	private final Deck DECK;
	private final KElevensPlayer HUMAN;
	private final KElevensPlayer DEALER;

	public KElevensController(KElevensPlayer human, KElevensPlayer dealer, Deck deck) {
		this.HUMAN = human;
		this.DEALER = dealer;
		this.DECK = deck;
	}

	@Override
	public void playGame() {
		// Given. Do not touch ===================
		KElevensController controller = this;   //
		Deck deck = this.DECK;					//
		KElevensPlayer human = this.HUMAN;		//
		KElevensPlayer dealer = this.DEALER;	//
		// =======================================

		human.setTokens(10);

		boolean playing = controller.getPlayGame();
		while (playing) {
			human.setTokens(human.getTokens() - 1);

			KElevensPlayer handWinner = controller.playHand(controller, deck, human, dealer);

			if (handWinner == human) {
				human.setTokens(human.getTokens() + 2);
			}

			controller.setHandWinner(handWinner);
			controller.getContinue();

			human.clearCards();
			dealer.clearCards();
			human.setScore(0);
			dealer.setScore(0);

			playing = human.getTokens() % 20 != 0;
			if (playing) {
				playing = controller.getPlayGame();
			}
		}
		controller.setGameWinner(human.getTokens() == 20 ? human : dealer);
		controller.getContinue();
		controller.exitGame();
	}

	private KElevensPlayer playHand(KElevensController controller, Deck deck, KElevensPlayer human, KElevensPlayer computer) {
		deck.reset();
		deck.shuffle();

		final int MAX_HAND_SIZE = 3;

		controller.doHumanTurn(controller, deck, human);

		if (human.getScore() > 11) {
			return computer;
		} else if (human.getCardCount() == MAX_HAND_SIZE) {
			return human;
		}

		controller.doComputerTurn(deck, computer);

		if (computer.getScore() > 11) {
			return human;
		} else if (computer.getCardCount() == MAX_HAND_SIZE) {
			return computer;
		} else if (computer.getScore() >= human.getScore()) {
			return computer;
		} else {
			return human;
		}
	}

	private void doHumanTurn(KElevensController controller, Deck deck, KElevensPlayer human) {
		int cardCount = 0;
		final int MAX_HAND_SIZE = 3;
		final int MAX_SCORE = 11;

		Card card = deck.drawCard();
		human.addCard(card);
		human.setScore(human.getScore() + getCardValue(card));
		cardCount++;

		boolean playing = true;
		while (playing && human.getScore() < MAX_SCORE && cardCount < MAX_HAND_SIZE) {
			String action = controller.getActionClicked();
			if (action.equals("HIT")) {
				card = deck.drawCard();
				human.addCard(card);
				human.setScore(human.getScore() + getCardValue(card));
				cardCount++;
			} else {
				playing = false;
			}
		}
	}

	private void doComputerTurn(Deck deck, KElevensPlayer dealer) {
		int cardCount = 0;
		final int MAX_HAND_SIZE = 3;

		while (dealer.getScore() < 5 && cardCount < MAX_HAND_SIZE) {
			Card card = deck.drawCard();
			dealer.addCard(card);
			dealer.setScore(dealer.getScore() + getCardValue(card));
			cardCount++;
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
