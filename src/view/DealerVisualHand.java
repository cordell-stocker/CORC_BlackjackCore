package view;

import javafx.scene.layout.Pane;
import javafxextend.standard.CardImageView;

class DealerVisualHand extends BlackjackVisualHand {

    DealerVisualHand(Pane handPane) {
        super(handPane);
        this.setScoreVisibility(false);
    }

    protected void addCardImageView(CardImageView cardImageView) {
        if (this.getSavedCardImageViews().size() > 0) {
            cardImageView.setIsFaceUp(false);
        }
        super.addCardImageView(cardImageView);
    }

    void showAllCards() {
        for (CardImageView cardImageView : this.getSavedCardImageViews()) {
            cardImageView.setIsFaceUp(true);
        }
        this.setScoreVisibility(true);
    }
}
