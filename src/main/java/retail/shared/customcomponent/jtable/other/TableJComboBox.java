package retail.shared.customcomponent.jtable.other;

import javax.swing.*;

public class TableJComboBox extends JComboBox<String>  {
    private final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

    public TableJComboBox(boolean isAction) {
        setModel(model);
        addElementToModel(isAction);
        setOpaque(true);
        centerText();
    }

    private void addElementToModel(boolean isAction) {
        if(isAction) {
            model.addElement("--");
            model.addElement("None");
            model.addElement("Same");
            model.addElement("Change");
        } else {
            model.addElement("--");
            model.addElement("Exp/Dam");
            model.addElement("Change");
        }
    }

    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
}
