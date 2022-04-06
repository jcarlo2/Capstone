package retail.view.main.panel.rightpanel.print;

import javax.swing.*;

public class ImportExport extends JPanel {

    public ImportExport() {
        JTextArea text = new JTextArea(35,50);
        text.setText("Import Export");
        add(text);

        setFocusable(false);
        setVisible(true);
    }
}
