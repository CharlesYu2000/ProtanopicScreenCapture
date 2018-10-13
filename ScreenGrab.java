import java.awt.*;
import java.awt.image.*;

public class ScreenGrab {

    public static void main(String[] args) throws AWTException{
        Robot robot = new Robot();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        //Rectangle screenRect = new Rectangle(size.width, size.height);
        //BufferedImage image = robot.createScreenCapture(screenRect);
        int[][] rgb = new int[size.width][size.height];
        Color color;
        System.out.println(size.width+ ", " +size.height);
        for(int x = 0; x < size.width; x++){
            for(int y = 0; y < size.height; y++){
                color = robot.getPixelColor(x, y);
                rgb[x][y] = color.getRGB();
            }
        }
        //System.out.println(rgb);
    }

}