package retail.shared.custom.jdialog;

import lombok.Getter;
import retail.shared.custom.jtextfield.CustomJTextField;
import retail.shared.custom.textarea.CustomTextArea;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Getter
public class ProductJDialog extends JDialog {
    private final JPanel wrapper1 = new JPanel(new GridLayout(3,1));
    private final CustomJTextField id = new CustomJTextField("Product Id", Color.BLACK,true);
    private final CustomJTextField price = new CustomJTextField("Price", Color.BLACK,true);
    private final CustomJTextField pieces = new CustomJTextField("Pieces Per Box", Color.BLACK,true);

    private final JPanel wrapper2 = new JPanel(new BorderLayout());
    private final CustomTextArea description = new CustomTextArea(5,15,true);
    private final JScrollPane scroll = new JScrollPane(description);
    private final JPanel wrapper3 = new JPanel(new GridLayout(1,2));
    private final JButton save = new JButton("Save");
    private final JButton cancel = new JButton("Cancel");

    public ProductJDialog() {
        setSize(400,400);
        setLayout(new GridLayout(2,1));
        wrapper1.add(id);
        wrapper1.add(price);
        wrapper1.add(pieces);

        wrapper3.add(save);
        wrapper3.add(cancel);

        wrapper2.add(scroll,BorderLayout.CENTER);
        wrapper2.add(wrapper3,BorderLayout.SOUTH);

        add(wrapper1);
        add(wrapper2);
        setJDialog();
    }

    public void clear() {
        id.setText("");
        price.setText("");
        pieces.setText("");
        description.setText("");
    }

    public String[] getData() {
        String[] data = new String[6];
        data[0] = id.getText();
        data[1] = description.getText();
        data[2] = price.getText();
        data[3] = "";
        data[4] = pieces.getText();
        data[5] = "";
        return data;
    }

    private void setJDialog() {
        ImageIcon img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/rm_logo.png")));
        setIconImage(img.getImage());
        setTitle("Add Product");
        setLocationRelativeTo(null);
        setModal(true);
        setAlwaysOnTop(true);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setVisible(false);
    }
}
