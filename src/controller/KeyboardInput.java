package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages the keyboard.
 */
public class KeyboardInput implements KeyListener {

    Controller controller;

    /**
     * Constructor.
     * @param controller the keyboard is used for.
     */
    public KeyboardInput(Controller controller){

        this.controller = controller;
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // not used
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        // Checking if the keys are working
        System.out.println(keyEvent.getKeyCode());

        // used keys:
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                controller.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                controller.moveLeft();
                break;
            case KeyEvent.VK_DOWN:
                controller.placeChip();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // not used
    }
}
