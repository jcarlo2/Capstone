package retail.model.facade.inventory.add;

import org.jetbrains.annotations.NotNull;
import retail.model.service.Calculate;
import retail.model.service.Creator;
import retail.model.service.inventory.add.AddInventoryService;
import retail.model.service.inventory.product.ProductViewService;
import retail.shared.entity.*;
import retail.shared.pojo.DeliveryAdd;

import java.util.ArrayList;

public class AddInventoryFacade {
    private final AddInventoryService service = new AddInventoryService();
    private final ProductViewService productService = new ProductViewService();
    private final Creator creator = new Creator();
    private final Calculate calculate = new Calculate();

    public ArrayList<Merchandise> getAllProduct() {
        return productService.getAllProduct();
    }

    public String findPriceById(String id) {
        return productService.findPriceById(id);
    }

    public String findBoxPiecesById(String id) {
        return productService.findBoxPiecesById(id);
    }

    public boolean isValidNumber(String quantity) {
        return calculate.isValidNumber(quantity);
    }

    public String division(String quantity, String pieces) {
        return calculate.division(quantity,pieces);
    }

    public String multiplication(String quantity, String pieces) {
        return calculate.multiplication(quantity,pieces);
    }

    public boolean isDeliveryReportIdExist(String id) {
        return service.isDeliveryReportIdExist(id);
    }

    public String generateId(boolean check) {
        return service.generateId(check);
    }

    public DeliveryAdd createItemDetail(String @NotNull [] data) {
        String oldStock = productService.findQuantityById(data[0]);
        return creator.createItemDetail(data,oldStock);
    }

    public void saveDelivery(String[][] dataList, String[] data) {
        ArrayList<DeliveryItemDetail> itemList = creator.convertAllToDeliveryItem(dataList);
        DeliveryDetail report = creator.createDeliveryDetail(data);
        service.saveDelivery(report,itemList);
    }

    public void saveNull(String[][] dataList, String @NotNull [] data) {
        ArrayList<NullReportItem> itemList = creator.createAllNullItem(dataList,data[0]);
        data[2] = service.getNullTotalAmount(itemList);
        NullProductReport report = creator.createNullReport(data);
        service.saveNull(report,itemList);
    }


    public boolean isNullReportExist(String id) {
        return service.isNullReportExist(id);
    }
}
