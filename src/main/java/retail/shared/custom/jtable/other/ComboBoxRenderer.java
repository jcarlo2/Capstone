package retail.shared.custom.jtable.other;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Objects;

public class ComboBoxRenderer extends JComboBox<String> implements TableCellRenderer {
    private final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

    public ComboBoxRenderer() {
        setModel(model);
        setOpaque(true);
        centerText();
        model.addElement("ASD");
    }

    private void centerText() {
        ((JTextField) getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(Objects.nonNull(value)) model.addElement(value.toString());
        setBackground(Color.RED);
        return this;
    }
}