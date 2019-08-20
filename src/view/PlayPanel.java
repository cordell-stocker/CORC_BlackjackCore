package view;

import javafx.OptionPanel;

public class PlayPanel extends OptionPanel<String> {

    private final static String[] OPTIONS = new String[]{"Play Game", "Exit"};

    PlayPanel() {
        super("Select an option", PlayPanel.OPTIONS);
    }

    String getSelectedOption() {
        return super.getOptionClicked();
    }
}
