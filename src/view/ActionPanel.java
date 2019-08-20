package view;

import javafx.OptionPanel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

@SuppressWarnings("FieldCanBeLocal")
class ActionPanel extends OptionPanel<String> {

    private final static String[] ACTIONS = new String[]{"Hit", "Stay"};

    ActionPanel() {
        super("Click an action", ActionPanel.ACTIONS);
    }

    String getActionClicked() {
        return super.getOptionClicked();
    }
}
