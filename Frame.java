import javax.swing.*;
import java.awt.*;

public class Frame {
    JFrame frame = new JFrame();

    ImageIcon imageIcon = new ImageIcon();

    JLabel label = new JLabel(imageIcon);

    JSlider slider = new JSlider();

    private Picture picture;

    public Frame() {
      frame.getContentPane().add(label);
      frame.pack();
      frame.setVisible(true);
    }

    public Frame(Picture pic) {
      this.picture = pic;
      if(pic == null){
        throw new NullPointerException();
      }
      imageIcon.setImage(picture.getImage());
      frame.setTitle(picture.getTitle());

      frame.getContentPane().add(label);
      frame.pack();
      frame.setVisible(true);
    }
}
