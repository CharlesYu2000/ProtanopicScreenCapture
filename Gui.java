import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Toolkit;

import java.io.*;

public class Gui extends Application {

    public Stage stage;

    public GridPane pane;

    public String saveLocation = "./MyScreenshots/";

    @Override
    public void start(Stage primaryStage) {
        pane = new GridPane();

        // Adds the pane to the scene, which gets added to the stage
        this.stage = primaryStage;
        Scene scene = new Scene(pane);
        this.stage.setTitle("Gui");
        this.stage.setScene(scene);
        this.stage.show();

        scene.setOnKeyPressed(new KeyHandler());

    }


/**
 * EventHandler that listens to keys pressed. Used by scene.setOnKeyPressed
 */
class KeyHandler implements EventHandler<KeyEvent> {

    /**
     * Moves the board in the direction according to the key pressed.
     *
     * @param e The KeyEvent that indicates a keystroke occured
     */
    @Override
    public void handle(KeyEvent e) {

        if (e.getCode().equals(KeyCode.PRINTSCREEN)) {
            System.out.println("PRINTSCREEN pressed! Taking screenshot here.");
            stage.setIconified(true);

            try {
                Robot capture = new Robot();
                BufferedImage curr;
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) screenSize.getWidth();
                int height = (int) screenSize.getHeight();
                java.awt.Rectangle screenRect = new java.awt.Rectangle(width, height);
                curr = capture.createScreenCapture(screenRect);
                int rgb;
                Pixel pix;
                String date = java.time.LocalDate.now().toString();
                String time = java.time.LocalTime.now().toString();
                File myScs = new File("MyScreenshots");
                if(!(myScs.exists())){
                    myScs.mkdir();
                }
                String fileSaveLoc = saveLocation + date + time.substring(0, 8).replace(':','_')+".jpg";
                File imgFile = new File(fileSaveLoc);
                ImageIO.write(curr, "jpg", imgFile);
                
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < height; k++) {
                        rgb = curr.getRGB(j, k);
                        
                    }
                }

            } catch (Exception exc) {
                System.out.println("Exception caught ruh roh");
                System.out.println(exc);
                System.exit(-1);
            }
            stage.setIconified(false);

            

        } else if (e.getCode().equals(KeyCode.Q)) {
            System.out.println("Q pressed! closing.");
            System.exit(0);
        }

    }

}

}
