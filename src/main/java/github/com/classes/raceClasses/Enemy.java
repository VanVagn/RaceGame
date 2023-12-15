package github.com.classes.raceClasses;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    private int x;
    private int y;
    private int v;
    private Image image = new ImageIcon("src/main/resources/images/taxi.png").getImage();
    private Road road;

    public Enemy(int x, int y, int v, Road road) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;

    }

    public void move() {
        x = x - road.getPlayer().getV() + v;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 190, 87);
    }

    public Rectangle getRectForTest() {
        return new Rectangle(x, y, 240, 105);
    }
    public void setSpeed(int v) {
        this.v = v;
    }

    public int getX() {
        return x;
    }

    public int getV() {
        return v;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
}