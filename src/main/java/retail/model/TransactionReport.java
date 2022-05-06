package retail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@AllArgsConstructor
public class TransactionReport {
    private String id;
    private Date date;
    private String user;
    private BigDecimal totalAmount;
}
