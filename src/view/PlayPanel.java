package view;

import javafxextend.structure.OptionPanel;

public class PlayPanel extends OptionPanel<String> {

    public enum Result {
        PLAY_GAME, EXIT
    }

    private final static String[] OPTIONS = new String[]{"Play Game", "Exit"};

    PlayPanel() {
        super("Select an option", PlayPanel.OPTIONS);
    }

    /**
     * @return either "PLAY GAME" or "EXIT"
     */
    Result getSelectedOption() {
        String answer = super.getOptionClicked().toUpperCase();
        if (answer.equals("PLAY GAME")) {
            return Result.PLAY_GAME;
        } else {
            return Result.EXIT;
        }
    }
}
