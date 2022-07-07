package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryAdd {
    private String productId;
    private String price;
    private String quantityPerPieces;
    private String quantityPerBox;
    private String piecesPerBox;
    private String oldStock;
}
