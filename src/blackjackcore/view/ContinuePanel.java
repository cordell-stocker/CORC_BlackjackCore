package blackjackcore.view;


import corc.javafxextend.structure.OptionPanel;

class ContinuePanel extends OptionPanel<String> {

    private static String[] options = new String[]{"Continue"};

    ContinuePanel() {
        super("", ContinuePanel.options);
    }

    void getContinue() {
        super.getOptionClicked();
    }
}
