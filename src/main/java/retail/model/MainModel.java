package retail.model;

import lombok.Getter;
import retail.model.inventory.InventoryTable;
import retail.model.login.LogInModel;

import javax.swing.table.DefaultTableModel;

@Getter
public class MainModel {
    private final LogInModel logInModel = new LogInModel();

    public MainModel() {

    }
}
