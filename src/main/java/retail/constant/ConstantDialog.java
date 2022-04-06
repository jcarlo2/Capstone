package retail.constant;

import javax.swing.*;

public interface ConstantDialog {
    static void createInvalidInputDialog() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.INVALID_INPUT);
    }

    static void createIdDoesNotExistDialog() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.ID_DOES_NOT_EXIST);
    }

    static void createEmptyDialog() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.CHECK_EMPTY_FIELD);
    }

    static void createProductDuplicateDialog() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.DUPLICATE_ID);
    }
}
