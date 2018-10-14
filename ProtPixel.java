/* ProtanopicScreenCapture: Protanopic screenshot taker for people with
 * protanomaly.
 * Copyright (C) 2018-2018 Charles Yu, Brandon Phan, Andrew Tang.  All Rights
 * Received.
 * https://github.com/CharlesYu2000/ProtanopicScreenCapture
 *
 * ColorCorrector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ColorCorrector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import java.awt.Color;
import java.lang.Math;


/**
 * RGB to LMS, LMS to LMS_prot, LMS_prot to RGB_prot. Changes a Pixel to a pixel
 * that is easy for a person with protonopia to see.
 *
 * @author Charles Yu, charlesyu@ucsd.edu
 */
public class ProtPixel extends Pixel{


    //CHANGE THIS TO "private static boolean protanopia = false;" without the
    // quotes if you want this for deuteranopia
    private static boolean protanopia = true;  



    private double origR; //the original red value of the pixel
    private double origG; //the original green value of the pixel
    private double origB; //the original blue value of the pixel

    private Color origColor; //the original color of the pixel

    //LMS transpose
    private double[] origLMST = new double[2];

    //LMS_prot transpose
    private double[] protLMST = new double[3];

    private double protR; //the new red value
    private double protG; //the new green value
    private double protB; //the new blue value


    /**
     * For RGB to LMS conversion. column matrix LMS = toLMS X RGB column matrix
     *                                  3x1            3x3      3x1
     * From http://vision.psychol.cam.ac.uk/jdmollon/papers/colourmaps.pdf
     * Don't need L for this
    public static final double[][] toLMS = new double[][]{
        {17.8824, 43.5161, 4.1193},
        {3.4557, 27.1554, 3.8671},
        {0.02996, 0.18431, 1.4670}
    };*/

    /**
     * Adjusted from actual matrix, which is above.
     */
    public static final double[][] toLMS = new double[][]{
        {3.4557, 27.1554, 3.8671},
        {0.02996, 0.18431, 1.4670}
    };

    /**
     * For LMS to LMS_prot. LMS_prot = toLMSProt x LMS
     * Don't need first column
    public static final double[][] toLMSProt = new double[][]{
        {0, 2.02344, -2.52581},
        {0, 1, 0},
        {0, 0, 1}
    };*/

    /**
     * Adjusted from actual matrix, which is above.
     */
    public static final double[] toLMSProt = new double[]{2.02344, -2.52581};
    

    /**
     * For LMS_prot to RGB_prot. RGB_prot = toRGBProt x LMS_prot
     */
    public static final double[][] toRGBProt = new double[][]{
        {0.0809, -0.1305, 0.1167},
        {-0.0102, 0.0540, -0.1136},
        {-0.0003, -0.0041, 0.6935}
    };

    /**
     * This constructor does all the converting and stuffs. :)
     * 
     * @param origPixel     - the Pixel to be changed
     */
    public ProtPixel(Pixel origPixel){
        super(origPixel.pic, origPixel.x, origPixel.y); //cus polymorphism X)
        
        origColor = new 
            Color(origPixel.getRed(), origPixel.getGreen(), origPixel.getBlue());

        //downscale these a bit so that changed values 
        //still within bounds for better accuracy
        origR = Math.pow((origPixel.getRed()/255.0),2.2);
        origG = Math.pow((origPixel.getGreen()/255.0),2.2);
        origB = Math.pow((origPixel.getBlue()/255.0),2.2);

        if(protanopia){
            //this downscaling for protonopes
            origR = (0.992052*origR+0.003974);
            origG = (0.992052*origG+0.003974);
            origB = (0.992052*origB+0.003974);           
        }else{
            //this downscaling for deuteranopes
            origR = 0.957237*origPixel.getRed()+0.0213814;
            origG = 0.957237*origPixel.getGreen()+0.0213814;
            origB = 0.957237*origPixel.getBlue()+0.0213814;            
        }

        //does stuff HAHA
        rgbToLMS();
        lmsToLMSProt();
        lmsProtToRGBProt();

        //reverts these back to the actual original values IDK why I made it
        //like this but I did, sorry :\
        origR = origPixel.getRed();
        origG = origPixel.getGreen();
        origB = origPixel.getBlue();
    }

    /**
     * Changes RGB to LMS (long-medium-short wavelength)
     */
    private void rgbToLMS(){
        /** L is not needed, save memory
        origLMST[0] = origR*toLMS[0][0]+origG*toLMS[0][1]+origB*toLMS[0][2];//Long wavelength is 564-580 nm
        origLMST[1] = origR*toLMS[1][0]+origG*toLMS[1][1]+origB*toLMS[1][2];//medium wavelength is 534-545 nm
        origLMST[2] = origR*toLMS[2][0]+origG*toLMS[2][1]+origB*toLMS[2][2];//short wavelength is 420-440 nm
        */
        origLMST[0] = origR*toLMS[0][0]+origG*toLMS[0][1]+origB*toLMS[0][2];//medium wavelength is 534-545 nm
        origLMST[1] = origR*toLMS[1][0]+origG*toLMS[1][1]+origB*toLMS[1][2];//short wavelength is 420-440 nm
    } 

    /**
     * Converts LMS to protan LMS values
     */
    private void lmsToLMSProt(){
        protLMST[0] = origLMST[0]*toLMSProt[0]+origLMST[1]*toLMSProt[1];
        protLMST[1] = origLMST[0];
        protLMST[2] = origLMST[1];
    }

    /**
     * Converts protan LMS values to the protan RGB values
     */
    private void lmsProtToRGBProt(){
        protR = (protLMST[0]*toRGBProt[0][0] + 
            protLMST[1]*toRGBProt[0][1] + protLMST[2]*toRGBProt[0][2]);
        protG = (protLMST[0]*toRGBProt[1][0] + 
            protLMST[1]*toRGBProt[1][1] + protLMST[2]*toRGBProt[1][2]);
        protB = (protLMST[0]*toRGBProt[2][0] + 
            protLMST[1]*toRGBProt[2][1] + protLMST[2]*toRGBProt[2][2]);

        protR = 255*Math.pow(protR, 1.0/2.2);
        protG = 255*Math.pow(protG, 1.0/2.2);
        protB = 255*Math.pow(protB, 1.0/2.2);

    }

    /**
     * Getter for the red channel of the protan pixel
     * 
     * @return the red value of the pixel
     */
    @Override
    public int getRed(){
        return (int)protR;
    }

    /**
     * Getter for the green channel of the protan pixel
     * 
     * @return the green value of the pixel
     */
    @Override
    public int getGreen(){
        return (int)protG;
    }

    /**
     * Getter for the blue channel of the protan pixel
     * 
     * @return the blue value of the pixel
     */
    @Override
    public int getBlue(){
        return (int)protB;
    }

    /**
     * Getter for the red channel of the non-protan pixel
     * 
     * @return the red value of the non-protan pixel
     */
    public int getOrigRed(){
        return origColor.getRed();
    }

    /**
     * Getter for the green channel of the non-protan pixel
     * 
     * @return the green value of the non-protan pixel
     */
    public int getOrigGreen(){
        return origColor.getGreen();
    }

    /**
     * Getter for the blue channel of the non-protan pixel
     * 
     * @return the blue value of the non-protan pixel
     */
    public int getOrigBlue(){
        return origColor.getBlue();
    }

    /**
     * Getter for the Color the non-protan pixel
     * 
     * @return the Color of the non-protan pixel
     */
    public Color getOrigColor(){
        return origColor;
    }

}