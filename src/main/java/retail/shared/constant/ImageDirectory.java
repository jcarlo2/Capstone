package retail.shared.constant;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public interface ImageDirectory {
    default Image SYSTEM_LOGO() {
        return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/rm_logo.png"))).getImage();
    }
}
