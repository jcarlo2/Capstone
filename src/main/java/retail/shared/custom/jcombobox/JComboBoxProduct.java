package retail.shared.custom.jcombobox;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import retail.shared.entity.Merchandise;

import javax.swing.*;
import java.util.ArrayList;

@Getter
public class JComboBoxProduct extends JComboBox<String> implements ComboBox{
    private final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

    public JComboBoxProduct(String title) {
        setCustomBorder(title,this);
        centerText(this);
        setModel(comboBoxModel);
        setPrototypeDisplayValue("");
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
