package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryItem {
    private String id;
    private String price;
    private String quantity;
}
