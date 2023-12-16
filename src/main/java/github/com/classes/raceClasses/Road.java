package github.com.classes.raceClasses;

import github.com.classes.adapter.MyKeyAdapter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable{

    public static final int TWO_WIDTH_OF_FRAME = 2200;

    private Timer mainTimer = new Timer(10, this);
    private Image image = new ImageIcon("src/main/resources/images/road.png").getImage().getScaledInstance(1100,700,Image.SCALE_DEFAULT);
    private Player player = new Player();
    private Thread enemiesFactory = new Thread(this);
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private TimeCount timeCount = new TimeCount();

    public Road() {
        timeCount.startTime();
        mainTimer.start();
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter(player));
        setFocusable(true);

    }

    @Override
    public void run() {

        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(creatEnemy());
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(image, player.getFirstLayer(),0, null);
        g.drawImage(image, player.getSecondLayer(),0, null);
        g.drawImage(player.image, player.getX(), player.getY(), null);

        g.setColor(Color.white);
        Font font = new Font("Arial", Font.BOLD,18);
        g.setFont(font);
        g.drawString("Скорость: " + player.getRealSpeed() + "км/ч", 100, 18);

        g.setColor(Color.white);
        Font newFont = new Font("Arial", Font.BOLD,18);
        g.setFont(newFont);
        g.drawString("Время: " + Math.round(timeCount.getResultTime()) + "c", 300, 18);

        Iterator<Enemy> i = enemies.iterator();
        List<Enemy> deleteEnemies = new ArrayList<>();

        for (Enemy enemy : enemies) {
            if ((enemy.getX() >= TWO_WIDTH_OF_FRAME) || (enemy.getX() <= -TWO_WIDTH_OF_FRAME)) {
                deleteEnemies.add(enemy);
            } else {
                g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
            }
        }

        for (Enemy enemy : deleteEnemies) {
            enemies.remove(enemy);
        }
        deleteEnemies.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        player.move();
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy enemy = i.next();
            enemy.move();
        }
        repaint();
        testCollisionEnemyWithEnemies();
    }

    public boolean testCollisionPlayerWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy enemy = i.next();
            if (player.getRect().intersects(enemy.getRect())) {
                mainTimer.stop();
                return true;
            }
        }
        return false;
    }

    private void testCollisionEnemyWithEnemies() {
        int curSpeed;
        int anotherSpeed;
        int minSpeed;
        for (Enemy curEnemy : enemies) {
            for (Enemy anotherEnemy : enemies) {
                if (!curEnemy.equals(anotherEnemy)) {
                    curSpeed = curEnemy.getV();
                    anotherSpeed = anotherEnemy.getV();
                    minSpeed = Math.min(curSpeed, anotherSpeed);
                    if (curEnemy.getRectForTest().intersects(anotherEnemy.getRectForTest())) {
                        curEnemy.setSpeed(minSpeed);
                        anotherEnemy.setSpeed(minSpeed);
                    }
                }
            }
        }
    }

    private Enemy creatEnemy() {
        Random rand = new Random();
        Enemy enemy = new Enemy(1100, rand.nextInt(40, 410), rand.nextInt(5,40), this);
        Iterator<Enemy> i = enemies.iterator();
        boolean flag;
        while (true) {
            flag = true;
            while (i.hasNext()) {
                Enemy curEnemy = i.next();
                if (enemy.getRectForTest().intersects(curEnemy.getRectForTest())) {
                    enemy = new Enemy(1100, rand.nextInt(40, 410), rand.nextInt(5, 40), this);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                break;
            } else {
                i = enemies.iterator();
            }

        }
        return enemy;

    }

    public Player getPlayer() {
        return player;
    }

    public TimeCount getTimeCount() {
        return timeCount;
    }


}
