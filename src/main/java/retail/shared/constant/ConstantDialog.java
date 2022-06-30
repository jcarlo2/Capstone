package retail.shared.constant;

import javax.swing.*;

public interface    ConstantDialog extends ImageDirectory{
    static void INVALID_INPUT() {
        JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!!");
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

    static void EMPTY_REPORT_TABLE() {
        JOptionPane.showMessageDialog(new JFrame(), "Empty Report Table!!");
    }

    static void SAVE_FAILED() {
        JOptionPane.showMessageDialog(new JFrame(), "Saving failed ... check item count");
    }

    static void GENERATE_NEW_REPORT_ID() {
        JOptionPane.showMessageDialog(new JFrame(), "Generate New Report ID!!");
    }

    static void REPORT_NOT_DELETABLE() {
        JOptionPane.showMessageDialog(new JFrame(), "Cannot delete report!!");
    }

    static void SAVED_REPORT() {
        JOptionPane.showMessageDialog(new JFrame(), "Report saved");
    }

    static void INCORRECT_ID_PASSWORD() {
        JOptionPane.showMessageDialog(new JFrame(), "Error! Incorrect ID or Password");
    }

    static int ADD_ALL() {
        return JOptionPane.showConfirmDialog(null, "Confirm Add All","Alert!!", JOptionPane.OK_CANCEL_OPTION);
    }

    static int DELETE_ALL() {
        return JOptionPane.showConfirmDialog(null, "Confirm Delete All","Alert!!", JOptionPane.OK_CANCEL_OPTION);
    }
}
