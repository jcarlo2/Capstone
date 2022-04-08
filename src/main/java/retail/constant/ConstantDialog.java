package retail.constant;

import javax.swing.*;

public interface ConstantDialog {
    static void INVALID_INPUT_DIALOG() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.INVALID_INPUT);
    }

    static void ID_DOES_NOT_EXIST_DIALOG() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.ID_DOES_NOT_EXIST);
    }

    static void EMPTY_FIELD_DIALOG() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.CHECK_EMPTY_FIELD);
    }

    static void DUPLICATE_ID() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.DUPLICATE_ID);
    }

    static void EMPTY_REPORT_TABLE() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.EMPTY_REPORT_TABLE);
    }

    static void GENERATE_NEW_REPORT_ID() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.GENERATE_NEW_REPORT_ID);
    }


    static void INCORRECT_ID_PASSWORD() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.INCORRECT_ID_PASSWORD);
    }
}
