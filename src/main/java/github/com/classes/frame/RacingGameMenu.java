package github.com.classes.frame;


import github.com.classes.persistence.PlayerPersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static github.com.classes.constants.Consts.TABLE;

public class RacingGameMenu extends JFrame implements ActionListener {
    private JButton startButton;
    private JButton exitButton;
    private JButton baseConnButton;
    private JButton deleteButton;
    private PlayerPersistence playerPersistence = new PlayerPersistence();
    private ResultsFrame resultsFrame = new ResultsFrame(playerPersistence);
    public void init() {
        setTitle("Racing Game Menu");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(600,150);

        startButton = new JButton("Start Racing");
        exitButton = new JButton("Exit");
        baseConnButton = new JButton("See best results");
        deleteButton = new JButton("Delete results");


        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        baseConnButton.addActionListener(this);
        deleteButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        panel.add(startButton);
        panel.add(baseConnButton);
        panel.add(deleteButton);
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            GameFrame gameFrame = new GameFrame(playerPersistence);
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
        }
    }
}