package blackjackcore.model;

import corc.core.Control;

public abstract class AbstractGameController {

    public abstract void play();

    /**
     * Closes the game.
     */
    public void exitGame() {
        Control.exitProgram();
    }

}
