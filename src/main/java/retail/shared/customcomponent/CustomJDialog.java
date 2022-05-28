package retail.shared.customcomponent;

import lombok.Getter;
import retail.shared.customcomponent.jcombobox.JComboBoxProduct;
import retail.shared.customcomponent.jtextfield.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Getter
public class CustomJDialog extends JDialog{
    private final CustomJTextField count = new CustomJTextField("Count",Color.BLACK,10);
    private final CustomJTextField productId = new CustomJTextField("Product",Color.BLACK,false);
    private final JPanel wrapper1 = new JPanel();

    private final CustomJTextField productCount = new CustomJTextField("How many Dam/Exp?",Color.BLACK);

    private final JPanel wrapper3 = new JPanel();
    private final JComboBoxProduct product = new JComboBoxProduct("Product",false);
    private final CustomJTextField price = new CustomJTextField("Price", Color.BLACK,10);

    private final JPanel wrapper4 = new JPanel();
    private final CustomJTextField sold = new CustomJTextField("Sold", Color.BLACK);
    private final CustomJTextField soldTotal = new CustomJTextField("Sold Total", Color.BLACK,false);

    private final JPanel wrapper5 = new JPanel();
    private final CustomJTextField discountPercentage = new CustomJTextField("Discount %%", Color.BLACK,false);
    private final CustomJTextField discountTotal = new CustomJTextField("Discount Total", Color.BLACK,false);
    private final CustomJTextField totalAmount = new CustomJTextField("Total Amount", Color.BLACK,false);

    private final JPanel wrapper6 = new JPanel();
    private final JButton save = new JButton("Save");
    private final JButton cancel = new JButton("Cancel");
    private final JButton clear = new JButton("Clear");

    public CustomJDialog() {
        setSize(400,400);
        setLayout(new GridLayout(8,1));
        setWrapper();

        add(wrapper1);
        add(productCount);
        add(wrapper3);
        add(wrapper4);
        add(wrapper5);
        add(totalAmount);
        add(wrapper6);
        setJDialog();
    }

    private void setWrapper() {
        wrapper1.setLayout(new BorderLayout(10,0));
        wrapper1.add(productId,BorderLayout.CENTER);
        wrapper1.add(count,BorderLayout.EAST);

        wrapper3.setLayout(new BorderLayout());
        wrapper3.add(product,BorderLayout.CENTER);
        wrapper3.add(price,BorderLayout.EAST);

        wrapper4.setLayout(new GridLayout(1,2));
        wrapper4.add(sold);
        wrapper4.add(soldTotal);

        wrapper5.setLayout(new GridLayout(1,2));
        wrapper5.add(discountPercentage);
        wrapper5.add(discountTotal);

        wrapper6.setLayout(new GridLayout(1,3));
        wrapper6.add(save);
        wrapper6.add(cancel);
        wrapper6.add(clear);
    }

    private void setJDialog() {
        ImageIcon img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/rmlogo.png")));
        setIconImage(img.getImage());
        setTitle("Returned Transaction");
        setLocationRelativeTo(null);
        setModal(true);
        setAlwaysOnTop(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }
}
