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
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
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

    public String saveLocation = "./MyScreenshots/";

    @Override
    public void start(Stage primaryStage) {
        pane = new GridPane();
        pane.setMinSize(400, 400);
        help = new Text("Press \"F6\" to take a screenshot. Press \"Q\" to quit");
        pane.add(help, 1, 1);

        // Adds the pane to the scene, which gets added to the stage
        this.stage = primaryStage;
        Scene scene = new Scene(pane);
        this.stage.setTitle("Color Corrector");
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

                    // The file name of the saved screenshot
                    String fileSaveLoc = saveLocation + date + "_" + time.substring(0, 8).replace(':', '-') + ".png";
                    File imgFile = new File(fileSaveLoc);

                    while (System.currentTimeMillis() - startTime < 350) {
                    }

                    curr = capture.createScreenCapture(screenRect);

                    ImageIO.write(curr, "png", imgFile);

                    Picture pic = new Picture(fileSaveLoc);

                    for (int j = 0; j < width; j++) {
                        for (int k = 0; k < height; k++) {
                            pix = new Pixel(pic, j, k);

                            newPix = new ProtPixel(pix);
                            newPix.setColor(newPix.getColor());

                        }
                    }

                    pic.reSavePic();
                    javafx.scene.image.Image newImage = new javafx.scene.image.Image(fileSaveLoc);
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
                System.out.println("Q pressed! closing.");
                System.exit(0);
            }

        }

    }

}
