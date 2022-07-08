package retail.model.facade.inventory.add;

import retail.model.service.Calculate;
import retail.model.service.Creator;
import retail.model.service.inventory.add.AddInventoryService;
import retail.model.service.inventory.product.ProductViewService;
import retail.shared.entity.DeliveryDetail;
import retail.shared.entity.DeliveryItemDetail;
import retail.shared.entity.Merchandise;

import java.util.ArrayList;

public class AddInventoryFacade {
    private final AddInventoryService service = new AddInventoryService();
    private final ProductViewService productService = new ProductViewService();
    private final Creator creator = new Creator();
    private final Calculate calculate = new Calculate();

    public ArrayList<Merchandise> getAllProduct() {
        return productService.getAllProduct();
    }

    public String getPiecesPerBoxById(String id) {
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

    public String subtract(String a, String b) {
        return calculate.subtract(a,b);
    }

    public String calculateDiscountAmount(String price, String percentage) {
        return calculate.calculateDiscountAmount(price,percentage);
    }

    public String calculateDiscountPercentage(String price, String total) {
        return calculate.calculateDiscountPercentage(price,total);
    }

    public boolean isReportIdExist(String id) {
        return service.isReportIdExist(id);
    }

    public String generateId() {
        return service.generateId();
    }

    public String subtraction(String a, String b) {
        return calculate.subtract(a,b);
    }

    public String getTotal(String[][] dataList) {
        return calculate.getTotal(dataList,6);
    }

    public void save(String[][] dataList, String total, String id, String lastName) {
        ArrayList<DeliveryItemDetail> itemList = creator.deliveryItem(dataList);
        DeliveryDetail report = creator.createDeliveryReport(id,lastName,total);
        service.save(report,itemList);
    }

    public boolean isWholeNumber(String a) {
        return calculate.isWholeNumber(a);
    }
}
