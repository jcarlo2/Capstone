package retail.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NullReportItem {
    private String id;
    private String price;
    private String quantity;
    private String box;
    private String pieces;
    private String total;
    private String reportId;
}
