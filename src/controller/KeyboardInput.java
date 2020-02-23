package controller;

import model.Board;
import model.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static model.Direction.*;

/**
 * Manages the keyboard.
 */
public class KeyboardInput implements KeyListener {

    Controller controller;

    public KeyboardInput(Controller controller){

        this.controller = controller;
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        System.out.println(keyEvent.getKeyCode());
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

    }
}
