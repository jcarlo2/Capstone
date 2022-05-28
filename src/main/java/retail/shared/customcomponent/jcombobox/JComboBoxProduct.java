package retail.shared.customcomponent.jcombobox;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

@Getter
public class JComboBoxProduct extends JComboBox<String> {
    private final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

    public JComboBoxProduct(String title) {
        setCustomBorder(title);
        centerText();
        setModel(comboBoxModel);
        setPrototypeDisplayValue("");
    }

    public JComboBoxProduct(String title, boolean isEnabled) {
        setEnabled(isEnabled);
        setCustomBorder(title);
        centerText();
        setModel(comboBoxModel);
        setPrototypeDisplayValue("");
    }

    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void setCustomBorder(String title) {
        setBorder(BorderFactory.createTitledBorder
            (null,title,
                    TitledBorder.CENTER,TitledBorder.CENTER,
                    new Font("SansSerif",Font.BOLD,15)));
    }
}
