package retail.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Merchandise {
    private String id;
    private String description;
    private String price;
    private String quantityPerPieces;
    private String piecesPerBox;
    private String quantityPerBox;
}
