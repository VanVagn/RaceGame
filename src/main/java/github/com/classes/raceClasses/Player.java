package github.com.classes.raceClasses;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int MAX_V = 50;
    public static final int MAX_TOP_COORDINATE = 40;
    public static final int MAX_BOTTOM_COORDINATE = 410;
    Image image = new ImageIcon("src/main/resources/images/Audi.png").getImage();

    private int v = 5;
    private int dv = 0;
    private int dy = 0;
    private int step = 1;
    private int stepSide = 8;

    private int x = 0;
    private int y = 40;
    private int firstLayer = 0;
    private int secondLayer = 1100;

    public void move() {

        v += dv;
        y -= dy;

        if (v <= 5) {
            v = 5;
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
            secondLayer = 1100;
        } else {
            firstLayer -= v;
            secondLayer -= v;
        }

    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 195, 80);
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
        return 5 * v;
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
