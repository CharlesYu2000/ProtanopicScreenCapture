import javax.swing.*;
import java.awt.*;

public class Frame {
    JFrame frame = new JFrame();

    ImageIcon imageIcon = new ImageIcon();

    JLabel label = new JLabel(imageIcon);

    private Picture picture;

    public Picture(){
        // only do this if there is a picture
    if (picture != null)
    {
      // set the image for the image icon from the picture
      imageIcon.setImage(picture.getImage());
      
      // set the title of the frame to the title of the picture
      frame.setTitle(picture.getTitle());
            // add the label to the frame
    frame.getContentPane().add(label);
    
    // pack the frame (set the size to as big as it needs to be)
    frame.pack();
    
    // make the frame visible
    frame.setVisible(true);
    }
}