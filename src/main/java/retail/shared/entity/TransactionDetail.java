package retail.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionDetail {
    private String id;
    private String date;
    private String timestamp;
    private String user;
    private String totalAmount;
    private String oldId;
    private String credit;
    private String isValid;
}
