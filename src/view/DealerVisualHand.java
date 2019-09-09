package view;

import javafx.scene.layout.Pane;
import javafxextend.standard.CardImageView;

class DealerVisualHand extends BlackjackVisualHand {

    private boolean showAllCards = false;

    DealerVisualHand(Pane handPane) {
        super(handPane);
        this.setScoreVisibility(false);
    }

    protected void addCardImageView(CardImageView cardImageView) {
        if (this.getSavedCardImageViews().size() > 0) {
            cardImageView.setIsFaceUp(showAllCards);
        }
        super.addCardImageView(cardImageView);
    }

    void showEverything() {
        for (CardImageView cardImageView : this.getSavedCardImageViews()) {
            cardImageView.setIsFaceUp(true);
        }
        this.setScoreVisibility(true);
        this.showAllCards = true;
    }

    void hideStatus() {
        this.showAllCards = false;
        this.setScoreVisibility(false);
    }
}
