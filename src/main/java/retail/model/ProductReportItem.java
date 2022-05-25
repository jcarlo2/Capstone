package retail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductReportItem {
    private String productId;
    private BigDecimal price;
    private Integer quantityByPieces;
    private Double quantityByBox;
    private Integer piecesPerBox;
}
