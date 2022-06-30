package retail;

import com.formdev.flatlaf.FlatDarculaLaf;
import retail.controller.MainController;
import retail.model.facade.MainFacade;
import retail.shared.tablecreator.TableCreatorImpl;
import retail.view.BuildGUI;

import javax.swing.*;

public class RetailApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TableCreatorImpl();
                UIManager.setLookAndFeel(new FlatDarculaLaf());
                new MainController(new MainFacade(),new BuildGUI());
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
