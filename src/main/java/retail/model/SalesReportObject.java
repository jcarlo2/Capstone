package retail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class SalesReportObject {
    private String id;
    private Date date;
    private String user;
}
