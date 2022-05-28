package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
public class TransactionReportItem {
    private String productId;
    private Double price;
    private Double sold;
    private Double soldTotal;
    private Double discountPercentage;
    private Double discountAmount;
    private BigDecimal totalAmount;
}
