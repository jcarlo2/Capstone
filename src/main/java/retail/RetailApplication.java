package retail;

import com.formdev.flatlaf.FlatDarculaLaf;
import retail.controller.MainController;
import retail.shared.util.TableCreatorImpl;
import retail.view.BuildGUI;

import javax.swing.*;

public class RetailApplication {
    public static void main(String[] args) {
        try {
            new TableCreatorImpl();
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            BuildGUI buildGUI = new BuildGUI();
            new MainController(buildGUI);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
