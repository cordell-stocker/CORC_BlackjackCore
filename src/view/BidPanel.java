package view;

import javafxextend.OptionPanel;

@SuppressWarnings("FieldCanBeLocal")
class BidPanel extends OptionPanel<Integer> {

    private static final Integer[] POSSIBLE_BIDS = new Integer[]{1, 3, 5};

    BidPanel() {
        super("Click amount to bid", BidPanel.POSSIBLE_BIDS);
    }

    Integer getBidClicked(int availableTokens) {
        int bid = super.getOptionClicked();
        while (bid > availableTokens) {
            bid = super.getOptionClicked();
        }
        return bid;
    }
}
