package dialogs;
import java.awt.Color;
public interface DialogShape {
    void configureCoordinates(int x, int y);
    void setModal(boolean modal);
    void setEditable(boolean editable);
    void setInnerColor(Color color);
    void setOuterColor(Color color);
    void setVisible(boolean visible);
}