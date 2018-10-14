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

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javax.imageio.*;

/**
 * The Gui class displays a blank window and will exit if the user presses "Q".
 * The Gui will take a screenshot if the user presses "F6". Then, an algorithm
 * will be applied to change the colors of the screenshot. The program will
 * display the modified picture in the window and save a copy of the modified
 * picture to the "./MyScreenShots" directory.
 */
public class Gui extends Application {

    public Stage stage;

    public GridPane pane;

    public Text help;

    public javafx.scene.control.Label label;

    public String saveLocation = null;

    private boolean screenshotTaken = false;

    private boolean screenshotVis = true;

    @Override
    public void start(Stage primaryStage) {
        pane = new GridPane();
        pane.setMinSize(400, 400);
        label = new javafx.scene.control.Label(" Press \"F6\" to take a screenshot. \n Press \"Q\" to quit"+
            "\n Press \"P\" for Protanopia mode. \n Press \"D\" for Deuteranopia "
            + "mode."+"\n Press \"N\" for non-colorblind mode."
            + " \n"
            + " \n Screenshots are saved to \"MyScreenshots\" in"
            + " \n this folder (YYYY-MM-DD_Hr-Min-Sec-Mode)");
        label.setStyle( "-fx-font: 24 helvetica;");
        pane.add(label, 1, 1);

        // Adds the pane to the scene, which gets added to the stage
        this.stage = primaryStage;
        Scene scene = new Scene(pane);
        this.stage.setTitle("RG Color Blind Screenshot");
        this.stage.setScene(scene);
        this.stage.show();

        scene.setOnKeyPressed(new KeyHandler());

    }

    /**
     * EventHandler that listens to keys pressed. Used by scene.setOnKeyPressed
     */
    class KeyHandler implements EventHandler<KeyEvent> {

        /**
         * Takes a screenshot if "F6" is pressed. Exits the program if "Q" is pressed.
         *
         * @param e The KeyEvent that indicates a keystroke occured
         */
        @Override
        public void handle(KeyEvent e) {

            if (e.getCode().equals(KeyCode.F6)) {
                System.out.println("F6 pressed! Taking screenshot here.");
                stage.setIconified(true);
                long startTime = System.currentTimeMillis();
                try {
                    File myScs = new File("MyScreenshots");
                    if (!(myScs.exists())) {
                        myScs.mkdir();
                    }

                    // Creates the Robot that will take the screenshot
                    Robot capture = new Robot();

                    // The picture that the Robot takes
                    BufferedImage curr;

                    // Will be used as the original pixels from the image
                    Pixel pix;

                    // Will be used as the changed pixels from the image
                    ProtPixel newPix;

                    // Gets the width and height of the screen
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = (int) screenSize.getWidth();
                    int height = (int) screenSize.getHeight();
                    Rectangle screenRect = new Rectangle(width, height);

                    // Saves the current time and date
                    String date = java.time.LocalDate.now().toString();
                    String time = java.time.LocalTime.now().toString();

                    String mode = ""; //D mode vs P mode vs S mode

                    if(ProtPixel.protanopia==0){
                        mode = "P";
                    }else if(ProtPixel.protanopia==1){
                        mode = "D";
                    }

                    // The file name of the saved screenshot
                    saveLocation = "./MyScreenshots/" + date + "_" +
                        time.substring(0, 8).replace(':', '-') + mode +".png";
                    File imgFile = new File(saveLocation);

                    while (System.currentTimeMillis() - startTime < 350) {}

                    curr = capture.createScreenCapture(screenRect);

                    ImageIO.write(curr, "png", imgFile);

                    Picture pic = new Picture(saveLocation);

                    for (int j = 0; j < width; j++) {
                        for (int k = 0; k < height; k++) {
                            pix = new Pixel(pic, j, k);

                            newPix = new ProtPixel(pix);
                            newPix.setColor(newPix.getColor());

                        }
                    }

                    pic.reSavePic();
                    screenshotTaken = true;
                    screenshotVis = true;
                    javafx.scene.image.Image newImage = new javafx.scene.image.Image(saveLocation);
                    ImageView updatedView = new ImageView(newImage);
                    updatedView.setFitWidth(screenSize.getWidth());
                    updatedView.setFitHeight(screenSize.getHeight());

                    pane.add(updatedView, 0, 0);
                    pane.setPrefSize(screenSize.getWidth(), screenSize.getHeight());

                } catch (Exception exc) {
                    System.err.println("Exception caught ruh roh");
                    System.err.println(exc);
                    System.exit(-1);
                }
                // Show and maximize the window
                stage.setIconified(false);
                stage.sizeToScene();

            } else if (e.getCode().equals(KeyCode.Q)) {
                System.out.println("Q pressed! Closing. Bye <3");
                System.exit(0);
            } else if (e.getCode().equals(KeyCode.P)) {
                if(ProtPixel.protanopia==0){
                    System.out.println("P pressed! Already in Protanopia " +
                       "mode!");
                }else{
                    System.out.println("P pressed! Changing to Protanopia mode!");  
                    ProtPixel.protanopia = 0;                 
                }

            } else if (e.getCode().equals(KeyCode.D)) {
                if(ProtPixel.protanopia==1){
                    System.out.println("D pressed! Already in Deuteranopia " +
                       "mode!");
                }else{
                    System.out.println("D pressed! Changing to Deuteranopia "+
                       "mode!");
                    ProtPixel.protanopia = 1;                 
                }

            } else if (e.getCode().equals(KeyCode.N)) {
                if(ProtPixel.protanopia==-1){
                    System.out.println("N pressed! Already in non-colorblind " +
                       "mode!");
                }else{
                    System.out.println("N pressed! Changing to non-colorblind "+
                       "mode!");
                    ProtPixel.protanopia = -1;                 
                }

            } else if (e.getCode().equals(KeyCode.SPACE)){
                if(screenshotTaken){
                    if(screenshotVis){

                    }else{

                    }

                }
            }

        }

    }

}
