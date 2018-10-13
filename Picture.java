import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.*;
import java.io.*;
import java.awt.geom.*;

public class Picture {

    private String fileName;


    protected String title;


    private BufferedImage bufferedImage;

    private Frame frame;

    /**
     * File extension for the pic (jpg)
     */
    private String extension;


    public Picture(String fileName) {
        load(fileName);
    }



    public int getPixel(int x, int y) {
        return bufferedImage.getRGB(x, y);
    }


    public void setPixel(int x, int y, int rgb) {
        bufferedImage.setRGB(x, y, rgb);
    }


    public boolean load(String fileName)
    {
        try {
            this.loadOrFail(fileName);
            return true;

        } catch (Exception ex) {
            System.out.println("Error when trying to open " + fileName);
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
            // try adding the media path 
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


    public void redraw(){
        if (frame != null){
            frame.redraw();
        } else {
            frame = new Frame(this);
        }

    }

    public void show(){
        if (frame != null){
            frame.show();
        } else {
            frame = new Frame(this);
        }
    }

    public Image getImage(){
        return bufferedImage;
    }

    public String getTitle(){
        return title;
    }

}
