package retail.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NullProductReport {
    private String id;
    private String user;
    private String total;
    private String link;
    private String date;
}
