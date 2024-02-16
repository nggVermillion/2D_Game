import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {


    Keys currentKey = Keys.LEFT_KEY_RELEASED;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if(keyChar == 'a') {
            this.currentKey = Keys.LEFT_KEY;
        }else if(keyChar == 'd'){
            this.currentKey = Keys.RIGHT_KEY;
        }else if(keyChar == 'w'){
            this.currentKey = Keys.UP_KEY;
        }else if(keyChar == 's'){
            this.currentKey = Keys.DOWN_KEY;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if(keyChar == 'a') {
            this.currentKey = Keys.LEFT_KEY_RELEASED;
        }else if(keyChar == 'd'){
            this.currentKey = Keys.RIGHT_KEY_RELEASED;
        }else if(keyChar == 'w'){
            this.currentKey = Keys.UP_KEY_RELEASED;
        }else if(keyChar == 's'){
            this.currentKey = Keys.DOWN_KEY_RELEASED;
        }
    }

    public Keys getCurrentKey(){
        return this.currentKey;
    }

}
