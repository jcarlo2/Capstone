package retail.shared.custom.jspinner;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class JSpinnerDate extends JSpinner {
    public JSpinnerDate() {
        Date dateNow = Calendar.getInstance().getTime();
        SpinnerDateModel model = new SpinnerDateModel(dateNow, null, null, Calendar.HOUR_OF_DAY);
        setModel(model);
        DateEditor editor = new DateEditor(this, "yyyy-MM-dd HH:mm");
        setEditor(editor);
//        ((DefaultEditor)getEditor()).getTextField().setEditable(false);
        ((DefaultEditor)getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultEditor)getEditor()).getTextField().setFont(new Font("SansSerif", Font.PLAIN,15));
    }
}
