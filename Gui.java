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
            this.stage.setTitle("Gui2048");
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
                //Shows the lose pane if the game is over
                

                //Checks if the key pressed is an arrow key or s
                if(e.getCode().equals(KeyCode.A)) {
                    System.out.println("A pressed! Taking screenshot here.");
                } else if(e.getCode().equals(KeyCode.B)){
                    System.out.println("B pressed! closing.");
                }

            }






    

   
}
