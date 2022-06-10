package retail.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class TransactionItemDetail {
    private String productId;
    private String price;
    private String sold;
    private String soldTotal;
    private String discountPercentage;
    private String discountAmount;
    private String totalAmount;
}
