package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class TransactionReport {
    private String id;
    private Date date;
    private Timestamp timestamp;
    private String user;
    private BigDecimal totalAmount;
}
