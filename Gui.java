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
import javafx.scene.image.*;

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

        if (e.getCode().equals(KeyCode.F6)) {
            System.out.println("F6 pressed! Taking screenshot here.");
            stage.setIconified(true);
            long startTime = System.currentTimeMillis();
            try {
                File myScs = new File("MyScreenshots");
                if(!(myScs.exists())){
                    myScs.mkdir();
                }


                Robot capture = new Robot();
                BufferedImage curr;
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) screenSize.getWidth();
                int height = (int) screenSize.getHeight();
                java.awt.Rectangle screenRect = new java.awt.Rectangle(width, height);
                int rgb;
                Pixel pix;
                ProtPixel newPix;
                String date = java.time.LocalDate.now().toString();
                String time = java.time.LocalTime.now().toString();
                
                String fileSaveLoc = saveLocation + date + "_" + time.substring(0, 8).replace(':','-')+".png";
                File imgFile = new File(fileSaveLoc);


                while(System.currentTimeMillis() - startTime < 200){}


                curr = capture.createScreenCapture(screenRect);

                ImageIO.write(curr, "png", imgFile);

                Picture pic = new Picture(fileSaveLoc);

                
                for (int j = 0; j < 2/*width*/; j++) {
                    for (int k = 0; k < 2/*height*/; k++) {
                        pix = new Pixel(pic, j, k);

            System.out.println(pix);
                        newPix = new ProtPixel(pix);
                        newPix.setColor(newPix.getColor());
            System.out.println(newPix);
                        
                    }
                }



                imgFile = new File(fileSaveLoc);
                ImageIO.write(curr, "png", imgFile);
                javafx.scene.image.Image newImage =  new javafx.scene.image.Image(fileSaveLoc);
                ImageView updatedView = new ImageView(newImage);
                updatedView.setFitWidth(screenSize.getWidth());
                updatedView.setFitHeight(screenSize.getHeight());

                pane.add(updatedView, 0, 0);
                pane.setPrefSize(screenSize.getWidth(), screenSize.getHeight());
               
            } catch (Exception exc) {
                System.out.println("Exception caught ruh roh");
                System.out.println(exc);
                System.exit(-1);
            }
            stage.setIconified(false);
            stage.sizeToScene();

            

        } else if (e.getCode().equals(KeyCode.Q)) {
            System.out.println("Q pressed! closing.");
            System.exit(0);
        }

    }

}

}
