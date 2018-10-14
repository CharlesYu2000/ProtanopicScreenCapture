import java.awt.Color;


/**
 * This object represents a pixel in relation to a picture. Needs to be used
 * with Picture.java.
 * 
 * @author Charles Yu, charlesyu@ucsd.edu
 */
public class Pixel{

    protected Picture pic; //the picture that this Pixel is from

    protected int x; //the x-coordinate of this Pixel in the picture (0-indexing)

    protected int y; //the y-coordinate of this Pixel in the picture (0-indexing)

    /**
     * This constructor links this Pixel with the picture.
     * 
     * @param pic   - The picture that the Pixel is from
     * @param x     - The x-coordinate
     * @param y     - The y-coordinate
     */
    public Pixel(Picture pic, int x, int y){
        this.pic = pic;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x-coordinate
     *
     * @return the x-coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Getter for the y-coordinate
     * 
     * @return the y-coordinate
     */
    public int getY(){
        return y;
    }

    /**
     * Getter for the alpha value of the pixel
     * 
     * @return the alpha value
     */
    public int getAlpha(){
        return ( (pic.getPixel(x, y) >> 24) & 0xff);
    }

    /**
     * Getter for the red value of the pixel
     * 
     * @return the red value
     */
    public int getRed(){
        return ( (pic.getPixel(x, y) >> 16) & 0xff );
    }

    /**
     * Getter for the green value of the pixel
     * 
     * @return the green value
     */
    public int getGreen(){
        return ( (pic.getPixel(x, y) >> 8) & 0xff);
    }

    /**
     * Getter for the blue value of the pixel
     * 
     * @return the blue value
     */
    public int getBlue(){
        return (pic.getPixel(x, y) & 0xff);
    }

    /**
     * Makes sure that all values within bounds of RGB DAC value  (i.e.
     * 0<=value<=255).
     * 
     * @param value     - the value to be bounded
     * @return the in-range value
     */
    public static int rgbBoundsCheck(int value){
        if(value<0){
            value = 0;
        }else if(value>255){
            value = 255;
        }

        return value;
    }

    /**
     * Setter for the alpha channel of the pixel.
     * 
     * @param newAlpha    - the alpha value to be changed to 
     */
    public void setAlpha(int newAlpha){
        int alpha = rgbBoundsCheck(newAlpha);
        int rgb = (alpha << 24) + (getRed() << 16) 
                    + (getGreen() << 8) + getBlue();
        pic.setPixel(x, y, rgb);
    }

    /**
     * Setter for the red channel of the pixel.
     * 
     * @param newRed    - the red value to be changed to 
     */
    public void setRed(int newRed){
        int red = rgbBoundsCheck(newRed);
        int rgb = (getAlpha() << 24) + (red << 16) 
                    + (getGreen() << 8) + getBlue();
        pic.setPixel(x, y, rgb);
    }

    /**
     * Setter for the green channel of the pixel.
     * 
     * @param newGreen    - the green value to be changed to 
     */
    public void setGreen(int newGreen){
        int green = rgbBoundsCheck(newGreen);
        int rgb = (getAlpha() << 24) + (getRed() << 16) 
                    + (green << 8) + getBlue();
        pic.setPixel(x, y, rgb);
    }

    /**
     * Setter for the blue channel of the pixel.
     * 
     * @param newBlue    - the blue value to be changed to 
     */
    public void setBlue(int newBlue){
        int blue = rgbBoundsCheck(newBlue);
        int rgb = (getAlpha() << 24) + (getRed() << 16) 
                    + (getGreen() << 8) + blue;
        pic.setPixel(x, y, rgb);
    }

    /**
     * Getter for the color of the pixel.
     * 
     * @return the color of the pixel
     */
    public Color getColor(){
        return new Color(getRed(), getGreen(), getBlue());
    }

    /**
     * Sets the color of the pixel. Call Picture.reSavePic() to see changes in
     * the saved pic
     * 
     * @param color     - the color that the pixel should be changed to
     */
    public void setColor(Color color){
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int rgb = (getAlpha() << 24) + (red << 16) 
                    + (green << 8) + blue;
        pic.setPixel(x, y, rgb);
    }

    /**
     * toString function for Pixel. Prints where the pixel is in the picture and
     * its ARGB values.
     *  
     * @return the String representation of this object
     */
    @Override
    public String toString(){
        return "Pixel at " + pic.title + "'s (" + x + ", " + y + "); " + 
                "ARGB is (" + getAlpha() + ", " + getRed() + ", " +
                getGreen() + ", " + getBlue() + ").";
    }
}