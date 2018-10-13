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
      if(picture != null){
        imageIcon.setImage(picture.getImage());
        frame.setTitle(picture.getTitle());
      }

      frame.getContentPane().add(label);
      frame.pack();
      frame.setVisible(true);
    }

    public void setPicture(Picture pic){
      this.picture = pic;
      imageIcon.setImage(picture.getImage());
      frame.pack();
      frame.repaint();
    }

    public void redraw(){
      frame.setVisible(true);
      if(picture != null){
        imageIcon.setImage(picture.getImage());
        frame.setTitle(picture.getTitle());
      }
      frame.repaint();
    }

    public void show(){
      if(picture != null){
        imageIcon.setImage(picture.getImage());
        frame.setTitle(picture.getTitle());
      }
      frame.setVisible(true);
    }

    public void setTitle(String title){
      frame.setTitle(title);
    }

    public void close(){
      frame.setVisible(false);
      frame.dispose();
    }


}
