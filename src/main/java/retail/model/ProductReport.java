package retail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class ProductReport {
    private String id;
    private String user;
    private Date date;
}
