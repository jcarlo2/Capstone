package retail.shared.custom.jcombobox;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.entity.Merchandise;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class JComboBoxProduct extends JComboBox<String> {
    private final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

    public JComboBoxProduct(String title) {
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

    public void setProductIdList(@NotNull ArrayList<Merchandise> merchandiseList) {
        removeAllItems();
        for(Merchandise merchandise : merchandiseList) {
            comboBoxModel.addElement(merchandise.getId());
        }
    }

    public boolean isNotSameData(@NotNull ArrayList<Merchandise> merchandiseList) {
        int count = getItemCount();
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<count;i++) {
            list.add(comboBoxModel.getElementAt(i));
        }
        for(Merchandise merchandise : merchandiseList) {
            if(!list.contains(merchandise.getId())) return true;
        }
        return list.size() != merchandiseList.size();
    }
}
