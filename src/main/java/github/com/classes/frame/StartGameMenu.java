package github.com.classes.frame;

import github.com.classes.persistence.PlayerPersistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static github.com.classes.constants.Consts.*;

public class StartGameMenu extends JFrame implements ActionListener {
    private JButton startButton;
    private JButton exitButton;
    private JButton baseConnButton;
    private JButton deleteButton;
    private PlayerPersistence playerPersistence = new PlayerPersistence();
    private ResultsFrame resultsFrame = new ResultsFrame(playerPersistence);
    private GameFrame gameFrame = new GameFrame(playerPersistence);

    public void init() {
        setTitle("Racing Game Menu");
        setSize(START_FRAME_WIDTH, START_FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(START_FRAME_X,START_FRAME_Y);

        startButton = new JButton("Начать игру");
        exitButton = new JButton("Выйти из игры");
        baseConnButton = new JButton("Посмотреть лучшие результаты");
        deleteButton = new JButton("Удалить все результаты");

        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        baseConnButton.addActionListener(this);
        deleteButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLUMNS));

        panel.add(startButton);
        panel.add(baseConnButton);
        panel.add(deleteButton);
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            gameFrame.initGame();
            dispose();
        } else if (e.getSource() == baseConnButton) {
            resultsFrame.init();
            dispose();
        } else if (e.getSource() == deleteButton) {
            playerPersistence.deleteAllTimes(TABLE);
            playerPersistence.restartId(TABLE);
        } else if (e.getSource() == exitButton) {
            dispose();
            System.exit(0);
        }
    }
}