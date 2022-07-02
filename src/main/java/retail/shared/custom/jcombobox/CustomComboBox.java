package retail.shared.custom.jcombobox;

import javax.swing.*;

public class CustomComboBox extends JComboBox<String> implements ComboBox{
    public CustomComboBox(String a, String b, String title) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        setModel(model);
        model.addElement(a);
        model.addElement(b);
        centerText(this);
        setCustomBorder(title,this);
    }
}
