package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public enum Direction implements KeyListener {
    RIGHT, DOWN, LEFT, UP;

    public Direction getDirection(KeyEvent keyEvent){

        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            return RIGHT;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            return LEFT;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP){
            return UP;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
            return DOWN;
        } else {
            throw new IllegalArgumentException("Key not usable.");
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
