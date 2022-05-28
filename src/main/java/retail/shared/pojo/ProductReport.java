package retail.shared.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class ProductReport {
    private String id;
    private String user;
    private Date date;

    @Override
    public String toString() {
        return "ProductReport{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", date=" + date +
                '}';
    }
}
