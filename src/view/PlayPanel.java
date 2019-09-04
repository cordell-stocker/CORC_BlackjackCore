package view;

import javafxextend.structure.OptionPanel;

class PlayPanel extends OptionPanel<String> {

    private final static String[] OPTIONS = new String[]{"Play Game", "Exit"};

    PlayPanel() {
        super("Select an option", PlayPanel.OPTIONS);
    }

    /**
     * @return either "PLAY GAME" or "EXIT"
     */
    String getSelectedOption() {
        return super.getOptionClicked().toUpperCase();
    }
}
