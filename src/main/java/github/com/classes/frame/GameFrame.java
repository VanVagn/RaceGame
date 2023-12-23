package github.com.classes.frame;

import github.com.classes.music.Radio;
import github.com.classes.persistence.PlayerPersistence;
import github.com.classes.raceClasses.Road;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static github.com.classes.constants.Consts.*;

public class GameFrame extends JFrame implements ActionListener{
    private JFrame frame;
    private final Road road = new Road();
    private Timer timer;
    private long gameTime;
    private final PlayerPersistence playerPersistence;
    private GameEndMenu gameEndMenu;
    private Radio radio = new Radio();

    public GameFrame(PlayerPersistence playerPersistence) {
        this.playerPersistence = playerPersistence;
        gameEndMenu = new GameEndMenu(playerPersistence);
    }

    public void initGame() {
        frame = new JFrame("Гонки");
        frame.setLocation(GAME_FRAME_X,GAME_FRAME_Y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        frame.add(road);
        frame.setVisible(true);
        timer = new Timer(DELAY, this);
        timer.start();
        radio.start();

    }

    private void test() {
        if (road.testCollisionPlayerWithEnemies()) {
            gameEndMenu.init();
            timer.stop();
            radio.stop();
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
        int lastIndex = allCurTimes.size() - INDEX_DIFF;
        boolean flag = true;
        if (allCurTimes.size() > 0) {
            for (int i = 0; i < allCurTimes.size(); i++) {
                if (allCurTimes.get(i) == minim) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                for (int i = 0; i < allCurTimes.size(); i++) {
                    if (allCurTimes.get(i) < minim) {
                        playerPersistence.updateTime(TABLE,i+INDEX_DIFF, minim);
                        minim = allCurTimes.get(i);
                    }
                }
                playerPersistence.addTime(TABLE, minim);
            }
        } else {
            playerPersistence.addTime(TABLE, minim);
        }
    }
}
