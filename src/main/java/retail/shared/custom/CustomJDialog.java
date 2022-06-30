package retail.shared.custom;

import lombok.Getter;
import retail.shared.custom.jtextfield.CustomJTextField;
import retail.shared.pojo.ProductReturnedDetail;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Getter
public class CustomJDialog extends JDialog{
    private final CustomJTextField productId = new CustomJTextField("Product",Color.BLACK,false);
    private final CustomJTextField price = new CustomJTextField("Price", Color.BLACK,10);
    private final JPanel wrapper1 = new JPanel(new BorderLayout(10,0));

    private final CustomJTextField productCount = new CustomJTextField("How many Dam/Exp?",Color.BLACK);
    private final CustomJTextField count = new CustomJTextField("Count",Color.BLACK,10);
    private final JPanel wrapper3 = new JPanel(new BorderLayout());

    private final JPanel wrapper4 = new JPanel(new GridLayout(1,2));
    private final CustomJTextField sold = new CustomJTextField("Sold", Color.BLACK);
    private final CustomJTextField soldTotal = new CustomJTextField("Sold Total", Color.BLACK,false);

    private final JPanel wrapper5 = new JPanel(new GridLayout(1,2));
    private final CustomJTextField discountPercentage = new CustomJTextField("Discount %%", Color.BLACK,false);
    private final CustomJTextField discountTotal = new CustomJTextField("Discount Total", Color.BLACK,false);
    private final CustomJTextField totalAmount = new CustomJTextField("Total Amount", Color.BLACK,false);

    private final JPanel wrapper6 = new JPanel(new GridLayout(1,2));
    private final JButton save = new JButton("Save");
    private final JButton cancel = new JButton("Cancel");

    public CustomJDialog() {
        setSize(400,400);
        setLayout(new GridLayout(7,1));
        setWrapper();

        add(wrapper1);
        add(wrapper3);
        add(wrapper4);
        add(wrapper5);
        add(totalAmount);
        add(wrapper6);
        setJDialog();
    }

    private void setWrapper() {
        wrapper1.add(productId,BorderLayout.CENTER);
        wrapper1.add(price,BorderLayout.EAST);

        wrapper3.add(productCount,BorderLayout.CENTER);
        wrapper3.add(count,BorderLayout.EAST);

        wrapper4.add(sold);
        wrapper4.add(soldTotal);

        wrapper5.add(discountPercentage);
        wrapper5.add(discountTotal);

        wrapper6.add(save);
        wrapper6.add(cancel);
    }

    private void setJDialog() {
        ImageIcon img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/rm_logo.png")));
        setIconImage(img.getImage());
        setTitle("Returned Transaction");
        setLocationRelativeTo(null);
        setModal(true);
        setAlwaysOnTop(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    public void clear() {
        productId.setText("0");
        count.setText("0");
        productCount.setText("0");
        price.setText("0");
        sold.setText("0");
        soldTotal.setText("0");
        discountPercentage.setText("0");
        discountTotal.setText("0");
    }

    public ProductReturnedDetail getProductData() {
        return new ProductReturnedDetail(sold.getText(),productCount.getText(),count.getText());
    }
}
