import javax.swing.*;
import java.awt.*;

public class Frame {
    JFrame frame = new JFrame();

    ImageIcon imageIcon = new ImageIcon();

    JLabel label = new JLabel(imageIcon);

    private Picture picture;

    public Frame() {
    if (picture != null) {
      imageIcon.setImage(picture.getImage());
      frame.setTitle(picture.getTitle());}
      frame.getContentPane().add(label);
      frame.pack();
      frame.setVisible(true);
    }
}
