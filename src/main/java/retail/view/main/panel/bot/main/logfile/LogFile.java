package retail.view.main.panel.bot.main.logfile;

import javax.swing.*;

public class LogFile extends JPanel {

    public LogFile() {
        JTextArea text = new JTextArea(35,50);
        text.setText("Log File");
        add(text);

        setFocusable(false);
        setVisible(true);
    }
}
