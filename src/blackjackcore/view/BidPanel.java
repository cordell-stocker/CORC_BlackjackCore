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

import corc.javafxextend.structure.OptionPanel;

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
