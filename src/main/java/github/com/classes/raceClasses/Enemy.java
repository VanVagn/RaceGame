package github.com.classes.raceClasses;

import javax.swing.*;
import java.awt.*;

import static github.com.classes.constants.Consts.*;

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
        return new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);
    }

    public Rectangle getRectForTest() {
        return new Rectangle(x, y, RECT_ENEMY_WIDTH, RECT_ENEMY_HEIGHT);
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