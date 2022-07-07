package retail.shared.custom.jtable;

import org.jetbrains.annotations.NotNull;
import retail.shared.custom.jtable.other.TableAbstract;
import retail.shared.entity.Merchandise;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ProductTable extends TableAbstract {
    public ProductTable(DefaultTableModel model) {
        super(model,new String[]{"No.","ID","Description","Price","Quantity By Pieces","Pieces Per Box","Quantity By Box"});
        setModel(model);
    }

    public void populateProductTable(@NotNull ArrayList<Merchandise> merchandiseList) {
        model.setRowCount(0);
        int count = 0;
        for (Merchandise merchandise : merchandiseList) {
            String[] listData = new String[7];
            listData[0] = String.valueOf(++count);
            listData[1] = merchandise.getId();
            listData[2] = merchandise.getDescription();
            listData[3] = String.valueOf(merchandise.getPrice());
            listData[4] = String.valueOf(merchandise.getQuantityPerPieces());
            listData[5] = String.valueOf(merchandise.getPiecesPerBox());
            listData[6] = String.valueOf(merchandise.getQuantityPerBox());
            model.addRow(listData);
        }
    }

    public boolean isSameData(@NotNull ArrayList<Merchandise> merchandiseList) {
        String[][] dataList = tableGrabber();
        if(dataList.length != merchandiseList.size()) return false;
        int i = 0;
        for (Merchandise merchandise : merchandiseList) {
            if (!dataList[i][0].equals(merchandise.getId())) return false;
            else if(!dataList[i][1].equals(merchandise.getDescription())) return false;
            else if(!dataList[i][2].equals(merchandise.getPrice())) return false;
            else if(!dataList[i][3].equals(merchandise.getQuantityPerPieces())) return false;
            else if(!dataList[i][4].equals(merchandise.getPiecesPerBox())) return false;
            else if(!dataList[i][5].equals(merchandise.getQuantityPerBox())) return false;
            i++;
        }
        return true;
    }
}
