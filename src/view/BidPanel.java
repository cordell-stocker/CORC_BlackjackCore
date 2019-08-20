package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

@SuppressWarnings("FieldCanBeLocal")
class BidPanel extends VBox {

    private final int INVALID_AMOUNT = -1;
    private final long SLEEP_TIME = 100;
    private int amountClicked;

    BidPanel(int[] amounts) {
        Label header = new Label("Click Amount To Bid");
        this.getChildren().add(header);

        for (int amount: amounts) {
            if (amount < 0) {
                throw new RuntimeException("Bid amount cannot be negative.");
            }
            Button button = new Button(Integer.toString(amount));
            button.setOnAction(e -> this.amountClicked = amount);
            this.getChildren().add(button);
        }
    }

    int getBidClicked(int availableTokens) {
        this.amountClicked = this.INVALID_AMOUNT;
        while (this.amountClicked == this.INVALID_AMOUNT || this.amountClicked > availableTokens) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.amountClicked;
    }
}
