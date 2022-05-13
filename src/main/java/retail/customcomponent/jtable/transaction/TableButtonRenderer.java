package retail.customcomponent.jtable.transaction;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Objects;

public class TableButtonRenderer extends JComboBox<String> implements TableCellRenderer {
    private final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

    public TableButtonRenderer() {
        setModel(comboBoxModel);
        setOpaque(true);
        centerText();
    }
    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        comboBoxModel.removeAllElements();
        if(Objects.nonNull(value)) comboBoxModel.addElement(value.toString());
        return this;
    }
}