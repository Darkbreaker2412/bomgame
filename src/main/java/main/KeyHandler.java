package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public KeyHandler() {

    }

    public boolean movePressed;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean enterPressed;
    public boolean upTyped, downTyped, leftTyped, rightTyped;

    @Override
    public void keyTyped(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_UP:
                upTyped = true;
                break;
            case KeyEvent.VK_DOWN:
                downTyped = true;
                break;
            case KeyEvent.VK_LEFT:
                leftTyped = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightTyped = true;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = true;
                break;
        }

        movePressed = upPressed || downPressed || leftPressed || rightPressed;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = false;
                break;
            case KeyEvent.VK_UP:
                upTyped = false;
                break;
            case KeyEvent.VK_DOWN:
                downTyped = false;
                break;
            case KeyEvent.VK_LEFT:
                leftTyped = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightTyped = false;
                break;
        }

        movePressed = upPressed || downPressed || leftPressed || rightPressed;
    }

}