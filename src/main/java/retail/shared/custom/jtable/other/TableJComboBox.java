package retail.shared.custom.jtable.other;

import javax.swing.*;

public class TableJComboBox extends JComboBox<String>  {
    private final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

    public TableJComboBox() {
        setModel(model);
        addElementToModel();
        setOpaque(true);
        centerText();
    }

    private void addElementToModel() {
        model.addElement("--");
        model.addElement("Exp/Dam");
    }

    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
}
