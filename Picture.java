import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.*;
import java.io.*;
import java.awt.geom.*;

public class Picture {

    // Fields

    /**
     * the file name associated with the picture
     */
    private String fileName;

    /**
     * the title of the picture
     */
    private String title;

    /**
     * buffered image to hold pixels for the picture
     */
    private BufferedImage bufferedImage;

    /**
     * frame used to display the picture
     */
    private PictureFrame pictureFrame;

    /**
     * extension for this file (jpg or bmp)
     */
    private String extension;


    // Constructors
    
    public Picture(String fileName) {
        
    }


    /**
     * Method to return the pixel value as an int for the given x and y location
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return the pixel value as an integer (alpha, red, green, blue)
     */
    public int getPixel(int x, int y) {
        return bufferedImage.getRGB(x, y);
    }

    /**
     * Method to set the value of a pixel in the picture from an int
     * 
     * @param x   the x coordinate of the pixel
     * @param y   the y coordinate of the pixel
     * @param rgb the new rgb value of the pixel (alpha, red, green, blue)
     */
    public void setPixel(int x, int y, int rgb) {
        bufferedImage.setRGB(x, y, rgb);
    }

}
