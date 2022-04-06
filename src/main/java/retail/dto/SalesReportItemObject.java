package retail.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalesReportItemObject {
    private String productId;
    private BigDecimal price;
    private BigDecimal sold;
    private BigDecimal soldTotal;
    private BigDecimal expDamaged;
    private BigDecimal expDamagedTotal;
    private BigDecimal totalAmount;
}
