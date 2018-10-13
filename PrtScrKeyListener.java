import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class PrtScrKeyListener{
    public static void main(String[] args){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_PRINTSCREEN){
                    //TODO
                    System.out.println("omg hello");
                }
            }
        }     
    }
}


/**
public class PrtScrKeyListener implements KeyListener{

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_PRINTSCREEN){
            //TODO
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        //nothing
    }

    @Override
    public void keyTyped(KeyEvent e){
        //nothing
    }
}
*/