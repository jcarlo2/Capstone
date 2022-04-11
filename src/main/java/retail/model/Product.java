package retail.model;

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
    private BigDecimal quantityPerPieces;
    private BigDecimal piecesPerBox;
    private BigDecimal quantityPerBox;
}
