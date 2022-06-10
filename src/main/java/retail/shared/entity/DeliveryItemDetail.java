package retail.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryItemDetail {
    private String productId;
    private String price;
    private String quantityByPieces;
    private String quantityByBox;
    private String piecesPerBox;
}
