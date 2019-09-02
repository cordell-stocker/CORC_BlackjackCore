package view;

import javafxextend.OptionPanel;

@SuppressWarnings("FieldCanBeLocal")
class ActionPanel extends OptionPanel<String> {

    private final static String[] ACTIONS = new String[]{"Hit", "Stay"};

    ActionPanel() {
        super("Click an action", ActionPanel.ACTIONS);
    }

    /**
     * @return either "HIT" or "STAY"
     */
    String getActionClicked() {
        return super.getOptionClicked().toUpperCase();
    }
}
