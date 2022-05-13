package retail.customcomponent.jtable.transaction;

import javax.swing.*;

public class TableJComboBox extends JComboBox<String>  {
    private final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    private final boolean isAction;

    public TableJComboBox(boolean isAction) {
        setModel(comboBoxModel);
        this.isAction = isAction;
        setOpaque(true);
        centerText();
        addElementToModel();
    }

    private void addElementToModel() {
        if(isAction) {
            comboBoxModel.addElement("None");
            comboBoxModel.addElement("Same");
            comboBoxModel.addElement("Change");
        } else {
            comboBoxModel.addElement("Exp/Dam");
            comboBoxModel.addElement("Change");
            comboBoxModel.addElement("Other");
        }
    }

    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
}
