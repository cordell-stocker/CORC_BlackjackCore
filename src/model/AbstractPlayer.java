package model;

public class AbstractPlayer {

    private final String NAME;

    public AbstractPlayer(String name) {
        this.NAME = name;
    }

    public String getName() {
        return this.NAME;
    }
}
