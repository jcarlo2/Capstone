package retail.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NullProductReport {
    private String id;
    private String user;
    private String totalAmount;
    private String transactionLink;
    private String date;
}
