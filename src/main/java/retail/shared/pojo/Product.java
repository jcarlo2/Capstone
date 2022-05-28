package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private String id;
    private String description;
    private BigDecimal price;
    private Double quantityPerPieces;
    private Double piecesPerBox;
    private Double quantityPerBox;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityPerPieces=" + quantityPerPieces +
                ", piecesPerBox=" + piecesPerBox +
                ", quantityPerBox=" + quantityPerBox +
                '}';
    }
}
