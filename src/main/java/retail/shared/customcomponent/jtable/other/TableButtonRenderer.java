package retail.shared.customcomponent.jtable.other;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Objects;

public class TableButtonRenderer extends JComboBox<String> implements TableCellRenderer {
    private final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

    public TableButtonRenderer() {
        setModel(model);
        setOpaque(true);
        centerText();
        model.addElement("--");
    }

    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        model.removeAllElements();
        if(Objects.nonNull(value)) model.addElement(value.toString());
        return this;
    }
}