package retail.shared.custom.jcombobox;

import javax.swing.*;

public class CustomComboBox extends JComboBox<String> implements ComboBox{
    public CustomComboBox(String title, String...strList) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        setModel(model);
        for(String str : strList) model.addElement(str);
        centerText(this);
        setCustomBorder(title,this);
    }
}
