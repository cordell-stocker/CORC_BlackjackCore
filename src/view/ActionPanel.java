package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

@SuppressWarnings("FieldCanBeLocal")
class ActionPanel extends VBox{

    private final String INVALID_ACTION = "INVALID";
    private final long SLEEP_TIME = 100;
    private String actionClicked;

    ActionPanel() {
        String[] actions = new String[]{"Hit", "Stay"};

        Label header = new Label("Click an action");
        this.getChildren().add(header);

        for (String action : actions) {
            Button button = new Button(action);
            button.setOnAction(e -> this.actionClicked = action);
            this.getChildren().add(button);
        }
    }

    String getActionClicked() {
        this.actionClicked = this.INVALID_ACTION;
        while (this.actionClicked.equals(this.INVALID_ACTION)) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.actionClicked;
    }
}
