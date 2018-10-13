import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Toolkit;

public class Test {

    public static void main(String[] args) throws AWTException {
        Robot capture = new Robot();
        BufferedImage curr;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        //System.out.println(width);
        //System.out.println(height);
        Rectangle screenRect = new Rectangle(width, height);

        for(int i = 0; i < 24; i++) {
            curr = capture.createScreenCapture(screenRect);
            for(int j = 0; j < width; j++) {
                for(int k = 0; k < height; k++) {
                    curr.getRGB(j, k);
                }
            }
        }

    }














}
