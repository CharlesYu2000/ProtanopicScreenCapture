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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Toolkit;

import java.io.*;

public class Gui extends Application
{

    private Stage stage;

    private GridPane pane;


    @Override
        public void start(Stage primaryStage)
        {
            pane = new GridPane();



            //Adds the pane to the scene, which gets added to the stage
            this.stage = primaryStage;
            Scene scene = new Scene(pane);
            this.stage.setTitle("Gui");
            this.stage.setScene(scene);
            this.stage.show();
            scene.setOnKeyPressed(new KeyHandler());

           

        }
    }



    /**
     * EventHandler that listens to keys pressed. Used by scene.setOnKeyPressed
     */
    class KeyHandler implements EventHandler<KeyEvent>{

        /**
         * Moves the board in the direction according to the key pressed.
         *
         * @param e The KeyEvent that indicates a keystroke occured
         */
        @Override
            public void handle(KeyEvent e){
                

                if(e.getCode().equals(KeyCode.PRINTSCREEN)) {
                    System.out.println("PRINTSCREEN pressed! Taking screenshot here.");

                    stage.setIconified(true);
                    Robot capture = new Robot();
                    BufferedImage curr;
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = (int)screenSize.getWidth();
                    int height = (int)screenSize.getHeight();
                    System.out.println(width);
                    System.out.println(height);
                    Rectangle screenRect = new Rectangle(width, height);

                    curr = capture.createScreenCapture(screenRect);
                    for(int j = 0; j < width; j++) {
                        for(int k = 0; k < height; k++) {
                            curr.getRGB(j, k);
                        }
                    }

                } else if(e.getCode().equals(KeyCode.Q)){
                    System.out.println("Q pressed! closing.");
                    System.exit(0);
                }

            }






    

   
}
