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
        load(fileName);
    }

    public Picture(int width, int height) {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        title = "None";
        fileName = "None";
        extension = "jpg";
        setAllPixelsToAColor(Color.white);
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

    /**
     * Method to write the contents of the picture to a file with 
     * the passed name without throwing errors
     * @param fileName the name of the file to write the picture to
     * @return true if success else false
     */
    public boolean load(String fileName)
    {
        try {
            this.loadOrFail(fileName);
            return true;

        } catch (Exception ex) {
            System.out.println("There was an error trying to open " + fileName);
            bufferedImage = new BufferedImage(600,200,
                    BufferedImage.TYPE_INT_RGB);
            addMessage("Couldn't load " + fileName,5,100);
            return false;
        }

    }
    /**
     * Method to load the picture from the passed file name
     * @param fileName the file name to use to load the picture from
     */
    public void loadOrFail(String fileName) throws IOException
    {
        // set the current picture's file name
        this.fileName = fileName;

        // set the extension
        int posDot = fileName.indexOf('.');
        if (posDot >= 0)
            this.extension = fileName.substring(posDot + 1);

        // if the current title is null use the file name
        if (title == null)
            title = fileName;

        File file = new File(this.fileName);

        if (!file.canRead()) 
        {
            // try adding the media path 
            file = new File(FileChooser.getMediaPath(this.fileName));
            if (!file.canRead())
            {
                throw new IOException(this.fileName +
                        " could not be opened. Check that you specified the path");
            }
        }

        bufferedImage = ImageIO.read(file);
    }

    public void addMessage(String message, int xPos, int yPos)
    {
        // get a graphics context to use to draw on the buffered image
        Graphics2D graphics2d = bufferedImage.createGraphics();

        // set the color to white
        graphics2d.setPaint(Color.white);

        // set the font to Helvetica bold style and size 16
        graphics2d.setFont(new Font("Helvetica",Font.BOLD,16));

        // draw the message
        graphics2d.drawString(message,xPos,yPos);

    }

    public void setAllPixelsToAColor(Color color) {
        for(int x = 0; x < this.getWidth(); x++) {
            for(int y = 0; y < this.getHeight(); y++) {
                getPixel(x,y).setColor(color);
            }
        }
    }
}
