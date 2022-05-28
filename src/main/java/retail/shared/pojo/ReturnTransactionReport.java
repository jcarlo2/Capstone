package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class ReturnTransactionReport {
    private String id;
    private String oldId;
    private Date date;
    private Timestamp timestamp;
    private String user;
    private BigDecimal totalAmount;
    private BigDecimal credit;

}
