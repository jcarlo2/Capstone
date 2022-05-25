package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ReturnedReport {
    private String id;
    private String reason;
    private String action;
    private Integer piecesSold;
    private Double total;
}
