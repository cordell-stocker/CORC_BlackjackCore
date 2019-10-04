package view;

import javafxextend.structure.OptionPanel;

@SuppressWarnings("FieldCanBeLocal")
class BidPanel extends OptionPanel<Integer> {

    private static final Integer[] POSSIBLE_BIDS = new Integer[]{5, 3, 1};

    BidPanel() {
        super("Click amount to bid", BidPanel.POSSIBLE_BIDS);
    }

    Integer getBidClicked() {
        return super.getOptionClicked();
    }
}
