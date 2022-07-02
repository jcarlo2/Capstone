package retail.shared.custom.jcombobox;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public interface ComboBox {

    default void centerText(@NotNull JComboBox<String> comboBox) {
        ((JTextField) comboBox.getEditor().getEditorComponent()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    default void setCustomBorder(String title, @NotNull JComboBox<String> comboBox) {
        comboBox.setBorder(BorderFactory.createTitledBorder
                (null,title,
                        TitledBorder.CENTER,TitledBorder.CENTER,
                        new Font("SansSerif",Font.BOLD,15)));
    }
}
