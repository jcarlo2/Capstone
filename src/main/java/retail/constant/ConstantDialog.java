package retail.constant;

import javax.swing.*;

public interface ConstantDialog {
    static void INVALID_INPUT_DIALOG() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.INVALID_INPUT);
    }

    static void ID_DOES_NOT_EXIST_DIALOG() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.ID_DOES_NOT_EXIST);
    }

    static void EMPTY_FIELD_DIALOG() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.CHECK_EMPTY_FIELD);
    }

    static void DUPLICATE_ID() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.DUPLICATE_ID);
    }

    static void CHECK_ID_OR_PASSWORD() {
        JOptionPane.showMessageDialog(new JFrame(), ConstantString.CHECK_ID_OR_PASSWORD);
    }
}
