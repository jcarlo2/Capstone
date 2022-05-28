package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductReportItem {
    private String productId;
    private BigDecimal price;
    private Double quantityByPieces;
    private Double quantityByBox;
    private Double piecesPerBox;

    @Override
    public String toString() {
        return "ProductReportItem{" +
                "productId='" + productId + '\'' +
                ", price=" + price +
                ", quantityByPieces=" + quantityByPieces +
                ", quantityByBox=" + quantityByBox +
                ", piecesPerBox=" + piecesPerBox +
                '}';
    }
}
