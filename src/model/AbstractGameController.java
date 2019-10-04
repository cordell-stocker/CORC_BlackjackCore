package model;

public abstract class AbstractGameController {

    public abstract void play();

    /**
     * Closes the game.
     */
    public void exitGame() {
        System.exit(0);
    }

}
