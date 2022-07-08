package retail.model.facade.inventory.add;

import retail.model.service.Calculate;
import retail.model.service.Creator;
import retail.model.service.inventory.add.NullInventoryService;
import retail.model.service.inventory.product.ProductViewService;
import retail.shared.entity.*;

import java.util.ArrayList;

public class NullInventoryFacade {
    private final NullInventoryService service = new NullInventoryService();
    private final ProductViewService productService = new ProductViewService();
    private final Calculate calculate = new Calculate();
    private final Creator creator = new Creator();

    public boolean isReportIdExist(String id) {
        return service.isReportIdExist(id);
    }

    public String generateId() {
        return service.generateId();
    }

    public ArrayList<Merchandise> getAllProduct() {
        return productService.getAllProduct();
    }

    public String findPriceById(String id) {
        return productService.findPriceById(id);
    }

    public String findPiecesPerBoxById(String id) {
        return productService.findBoxPiecesById(id);
    }

    public boolean isValidNumber(String...quantity) {
        return calculate.isValidNumber(quantity);
    }

    public String division(String a, String b) {
        return calculate.divide(a,b);
    }

    public String multiplication(String a, String b) {
        return calculate.multiply(a,b);
    }

    public String multiply(String a, String b) {
        return calculate.multiply(a,b);
    }

    public boolean isWholeNumber(String a) {
        return calculate.isWholeNumber(a);
    }

    public void save(String[][] dataList, String total, String id, String lastName) {
        ArrayList<NullReportItem> itemList = creator.createAllNullItem(dataList,id);
        NullProductReport report = creator.createNullReport(id,lastName,total,"");
        service.save(report,itemList);
    }

    public String getTotal(String[][] dataList) {
        return calculate.getTotal(dataList,5);
    }
}
