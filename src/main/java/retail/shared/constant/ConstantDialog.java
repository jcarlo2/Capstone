package retail.shared.constant;

import javax.swing.*;

public interface ConstantDialog extends ImageDirectory{
    static void INVALID_INPUT() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.INVALID_INPUT);
    }

    static void ID_DOES_NOT_EXIST() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.ID_DOES_NOT_EXIST);
    }

    static void EMPTY_FIELD() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.CHECK_EMPTY_FIELD);
    }

    static void DUPLICATE_ID() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.DUPLICATE_ID);
    }

    static void EMPTY_REPORT_TABLE() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.EMPTY_REPORT_TABLE);
    }

    static void SAVE_FAILED() {
        JOptionPane.showMessageDialog(new JFrame(), "Saving failed ... check row count");
    }

    static void GENERATE_NEW_REPORT_ID() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.GENERATE_NEW_REPORT_ID);
    }

    static void REPORT_NOT_DELETABLE() {
        JOptionPane.showMessageDialog(new JFrame(), "Cannot delete report!!");
    }

    static void SAVED_REPORT() {
        JOptionPane.showMessageDialog(new JFrame(), "Report saved");
    }

    static void INCORRECT_ID_PASSWORD() {
        JOptionPane.showMessageDialog(new JFrame(), Constant.INCORRECT_ID_PASSWORD);
    }

    static int ADD_ALL() {
        return JOptionPane.showConfirmDialog(null, "Confirm Add All","Alert!!", JOptionPane.OK_CANCEL_OPTION);
    }

    static int DELETE_ALL() {
        return JOptionPane.showConfirmDialog(null, "Confirm Delete All","Alert!!", JOptionPane.OK_CANCEL_OPTION);
    }
}
