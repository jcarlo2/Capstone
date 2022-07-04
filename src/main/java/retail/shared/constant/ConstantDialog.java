package retail.shared.constant;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public interface    ConstantDialog extends ImageDirectory{
    static void INVALID_INPUT() {
        JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!!");
    }

    static void EMPTY_TABLE() {
        JOptionPane.showMessageDialog(new JFrame(), "Empty table ...");
    }

    static void ID_DOES_NOT_EXIST() {
        JOptionPane.showMessageDialog(new JFrame(), "Id does not exist! Check id ...");
    }

    static void EMPTY_FIELD() {
        JOptionPane.showMessageDialog(new JFrame(), "Check Empty Field!!");
    }

    static void DUPLICATE_ID() {
        JOptionPane.showMessageDialog(new JFrame(), "Duplicate product id");
    }

    static void SAVE_FAILED() {
        JOptionPane.showMessageDialog(new JFrame(), "Saving failed ... check item count");
    }

    static void SAVED_REPORT() {
        JOptionPane.showMessageDialog(new JFrame(), "Report saved");
    }

    static void PRODUCT_UPDATE_SUCCESS() {
        JOptionPane.showMessageDialog(new JFrame(), "Product update success!");
    }

    static void INCORRECT_ID_PASSWORD() {
        JOptionPane.showMessageDialog(new JFrame(), "Error! Incorrect ID or Password");
    }

    static void ADD_PRODUCT() {
        JOptionPane.showMessageDialog(new JFrame(), "Product successfully added!");
    }

    static int DELETE_ALL_OPTION(String @NotNull ...id) {
        String[] options = new String[] {"Delete", "Delete All", "Cancel"};
        return JOptionPane.showOptionDialog(null, "Delete " + id[0]  + " Or All?", "Alert!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
    }

    static int ADD_ALL() {
        return JOptionPane.showConfirmDialog(null, "Confirm Add All","Alert!!", JOptionPane.OK_CANCEL_OPTION);
    }

    static int DELETE_ALL() {
        return JOptionPane.showConfirmDialog(null, "Confirm Delete All","Alert!!", JOptionPane.OK_CANCEL_OPTION);
    }

    static int DELETE(String id) {
        return JOptionPane.showConfirmDialog(null, "Delete " + id,"Alert!!", JOptionPane.OK_CANCEL_OPTION);
    }

}
