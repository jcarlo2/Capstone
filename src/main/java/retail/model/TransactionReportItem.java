package retail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TransactionReportItem {
    private String productId;
    private BigDecimal price;
    private BigDecimal sold;
    private BigDecimal soldTotal;
    private BigDecimal discountPercentage;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
}
