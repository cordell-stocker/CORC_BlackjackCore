package blackjackcore.model;

import blackjackcore.model.player.PlayerWithScoreAndTokens;

public abstract class AbstractKElevensController<P extends PlayerWithScoreAndTokens> extends AbstractBlackjackController<P> {

	public void play() {
		this.playGame();
	}
}
