package retail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SalesReportItem {
    private String productId;
    private BigDecimal price;
    private BigDecimal sold;
    private BigDecimal soldTotal;
    private BigDecimal expDamaged;
    private BigDecimal expDamagedTotal;
    private BigDecimal totalAmount;
}
