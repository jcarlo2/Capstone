package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDisplay {
    private String id;
    private String description;
    private String price;
    private String piecesPerBox;
    private String newId;
}
