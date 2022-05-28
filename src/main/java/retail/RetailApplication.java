package retail;

import com.formdev.flatlaf.FlatDarculaLaf;
import retail.controller.MainController;
import retail.model.User;
import retail.shared.util.tablecreator.TableCreatorImpl;
import retail.view.BuildGUI;

import javax.swing.*;

public class RetailApplication {
    public static void main(String[] args) {
        try {
            new TableCreatorImpl();
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            new MainController(new BuildGUI(),new User());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
