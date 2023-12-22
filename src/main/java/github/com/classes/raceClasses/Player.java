package github.com.classes.raceClasses;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static github.com.classes.constants.Consts.*;

public class Player {
    Image image = new ImageIcon("src/main/resources/images/Audi.png").getImage();
    private int v = MIN_SPEED;
    private int dv = 0;
    private int dy = 0;
    private int step = 1;
    private int stepSide = 8;
    private int x = 0;
    private int y = 40;
    private int firstLayer = 0;
    private int secondLayer = SECOND_LAYER_START;

    public void move() {

        v += dv;
        y -= dy;

        if (v <= MIN_SPEED) {
            v = MIN_SPEED;
        }
        if (v >= MAX_V) {
            v = MAX_V;
        }
        if (y <= MAX_TOP_COORDINATE) {
            y = MAX_TOP_COORDINATE;
        }
        if (y >= MAX_BOTTOM_COORDINATE) {
            y = MAX_BOTTOM_COORDINATE;
        }
        if (secondLayer - v <= 0) {
            firstLayer = 0;
            secondLayer = SECOND_LAYER_START;
        } else {
            firstLayer -= v;
            secondLayer -= v;
        }

    }

    public Rectangle getRect() {
        return new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            dv = step;
        }
        if (key == KeyEvent.VK_LEFT) {
            dv = -step;
        }
        if (key == KeyEvent.VK_UP) {
            dy = stepSide;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -stepSide;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_RIGHT) || (key == KeyEvent.VK_LEFT)) {
            dv = 0;
        }
        if ((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
            dy = 0;
        }
    }

    public int getRealSpeed() {
        return KOEF_SPEED * v;
    }

    public int getV() {
        return v;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFirstLayer() {
        return firstLayer;
    }

    public int getSecondLayer() {
        return secondLayer;
    }
}
