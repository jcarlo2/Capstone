package retail.view.option;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class Option extends JPanel {
    private final JPanel radio = new JPanel();
    private final JPanel create = new JPanel();
    private final JRadioButton light = new JRadioButton("Light Theme");
    private final JRadioButton dark = new JRadioButton("Dark Theme");
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public Option() {
        setLayout(new BorderLayout());
        buttonGroup.add(light);
        buttonGroup.add(dark);
        buttonGroup.setSelected(dark.getModel(),true);

        radio.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        radio.add(light,constraints);
        radio.add(dark,constraints);

        create.setBackground(Color.ORANGE);

        add(radio,BorderLayout.NORTH);
        add(create,BorderLayout.CENTER);
    }

}
