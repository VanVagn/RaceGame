package github.com.classes.frame;

import github.com.classes.persistence.PlayerPersistence;
import github.com.classes.raceClasses.Road;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static github.com.classes.constants.Consts.*;

public class GameFrame extends JFrame implements ActionListener{
    private JFrame frame;
    private GameEndMenu gameEndMenu;
    private final Road road = new Road();
    private Timer timer;

    private long gameTime;

    private final PlayerPersistence playerPersistence;

    public GameFrame(PlayerPersistence playerPersistence) {
        this.playerPersistence = playerPersistence;
    }

    public void initGame() {
        frame = new JFrame("Гонки");
        frame.setLocation(30,30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        frame.add(road);
        frame.setVisible(true);
        timer = new Timer(10, this);
        timer.start();

    }

    private void test() {
        if (road.testCollisionPlayerWithEnemies()) {
            GameEndMenu gameEndMenu = new GameEndMenu(playerPersistence);
            gameEndMenu.init();
            timer.stop();
            frame.dispose();
            gameTime = road.getTimeCount().getResultTime();
            workWithBd(gameTime);

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        test();
    }

    private void workWithBd(long gameTime) {
        long minim = gameTime;
        ArrayList<Long> allCurTimes = playerPersistence.getAll();
        int lastIndex = allCurTimes.size() - 1;
        if (allCurTimes.size() > 0) {
            for (int i = 0; i < allCurTimes.size(); i++) {
                if (allCurTimes.get(i) < minim) {
                    playerPersistence.updateTime(TABLE,i+1, minim);
                    minim = allCurTimes.get(i);
                }
            }
            if (minim != allCurTimes.get(lastIndex)) {
                playerPersistence.addTime(TABLE, minim);
            }
        } else {
            playerPersistence.addTime(TABLE, minim);
        }
    }
}
