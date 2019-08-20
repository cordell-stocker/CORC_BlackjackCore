package view;

import javafx.OptionPanel;

@SuppressWarnings("FieldCanBeLocal")
class BidPanel extends OptionPanel<Integer> {

    BidPanel(Integer[] amounts) {
        super("Click amount to bid", amounts);
    }

    Integer getBidClicked(int availableTokens) {
        int bid = super.getOptionClicked();
        while (bid > availableTokens) {
            bid = super.getOptionClicked();
        }
        return bid;
    }
}
