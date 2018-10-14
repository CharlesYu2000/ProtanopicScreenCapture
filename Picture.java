/* ProtanopicScreenCapture: Protanopic screenshot taker for people with
 * protanomaly.
 * Copyright (C) 2018-2018 Charles Yu, Brandon Phan, Andrew Tang.  All Rights
 * Received.
 * https://github.com/CharlesYu2000/ProtanopicScreenCapture
 *
 * ProtanopicScreenCapture is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ProtanopicScreenCapture is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/** Methods required for modifying image in Gui.java
 *  Used to generate and modify a BufferedImage from a picture that
 *               will address red green color blindness
 */
public class Picture {

    private String fileName;

    protected String title;

    private BufferedImage bufferedImage;

    private String extension = "png";


    /** One arg constructor to generate a BufferedImage of the picture
     *  @param fileName name of the file of the screenshot taken
     */
    public Picture(String fileName) {
        load(fileName);
    }


    /** returns RGB value of the buffered image at a specific x,y coordinate
     *  @param x x coordinate to consider
     *  @param y y coordinate to consider
     *  @return the RGB value at that coordinate
     */
    public int getPixel(int x, int y) {
        return bufferedImage.getRGB(x, y);
    }


    /** changes the rgb value of the buffered image at that coordinate
     *  @param x x coordinate to consider
     *  @param y y coordinate to consider
     *  @param rgb the initial rgb value
     */
    public void setPixel(int x, int y, int rgb) {
        bufferedImage.setRGB(x, y, rgb);
    }

    /** 
     *  writes the modified buffered image to a file in MyScreenshots
     */
    public void reSavePic(){
        try{
            File imgFile = new File(fileName);
            //avoids confusion between writing the original and modified image
            //to a file
            ImageIO.write(bufferedImage, extension, imgFile);
        }catch(Exception exc){
            System.err.println("big sp00k in Picture.java");
            System.err.println(exc);
            System.exit(-1);
        }
    }

    /** method checks that the file exists
     *  @param fileName name of the file
     *  @return true if the file exists, false otherwise
     */
    public boolean load(String fileName)
    {
        try {
            this.loadOrFail(fileName);
            return true;
        }
        catch(Exception e) {
            return false;
        }

    }

    /** method generates a buffered image of the screenshot
     *  @param fileName name of the file
     */
    public void loadOrFail(String fileName) throws IOException
    {
        this.fileName = fileName;


        if (title == null)
            title = fileName;

        File file = new File(this.fileName);

        bufferedImage = ImageIO.read(file);
    }

    /** getter for the buffered image object
     *  @return the buffered image
     */
    public BufferedImage getImage(){
        return bufferedImage;
    }

    /** gives name of the file
     *  @return the name of the file
     */
    public String getTitle(){
        return title;
    }

}
