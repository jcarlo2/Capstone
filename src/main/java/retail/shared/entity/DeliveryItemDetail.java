package retail.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryItemDetail {
    private String productId;
    private String price;
    private String quantityPerPieces;
    private String quantityPerBox;
    private String piecesPerBox;
}
