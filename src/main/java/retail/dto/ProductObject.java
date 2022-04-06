package retail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductObject {
    private String id;
    private String description;
    private BigDecimal price;
    private BigDecimal quantityPerPieces;
    private BigDecimal piecesPerBox;
    private BigDecimal quantityPerBox;

    @Override
    public String toString() {
        return "ProductObject{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityPerPieces=" + quantityPerPieces +
                ", piecesPerBox=" + piecesPerBox +
                ", quantityPerBox=" + quantityPerBox +
                '}';
    }
}
