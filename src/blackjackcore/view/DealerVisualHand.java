/*
Copyright 2019, Cordell Stocker (cordellstocker@gmail.com)
All rights reserved.

This file is part of CORC BlackjackCore.

    CORC BlackjackCore is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    CORC BlackjackCore is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CORC BlackjackCore.  If not, see <https://www.gnu.org/licenses/>.
*/
package blackjackcore.view;

import corc.javafxextend.standard.CardImageView;
import javafx.scene.layout.Pane;

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
