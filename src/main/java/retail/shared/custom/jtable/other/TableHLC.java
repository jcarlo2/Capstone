package retail.shared.custom.jtable.other;

import org.jetbrains.annotations.Nullable;

public interface TableHLC {
    void removeRow(int row);
    void fixNumberColumn();
    boolean isDuplicate(String id);
    String @Nullable [] rowGrabber();
    String[][] tableGrabber();
    void removeSelectedRow();
}
