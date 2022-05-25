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
    private Integer quantityPerPieces;
    private Integer piecesPerBox;
    private Double quantityPerBox;
}
