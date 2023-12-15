package github.com.classes.frame;

import github.com.classes.persistence.PlayerPersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEndMenu extends JFrame implements ActionListener{


    private final ImageIcon imageIc = new ImageIcon("src/main/resources/images/drive.png");
    private final ImageIcon imageIcon = new ImageIcon("src/main/resources/images/lose.png");
    private  final JButton button = new JButton("Вернуться в начальное меню");
    private  final JButton secButton = new JButton("Начать новую игру");
    private PlayerPersistence playerPersistence;

    public GameEndMenu(PlayerPersistence playerPersistence) {
        this.playerPersistence = playerPersistence;
    }

    public void init() {
        setSize(1280,820);
        setLocation(30,30);
//      JLabel label = new JLabel(image);
        JLabel labelTwo = new JLabel(imageIcon);

        JPanel panel = new JPanel();
//        panel.add(label);
        panel.add(labelTwo);
        panel.add(button, BorderLayout.SOUTH);
        panel.add(secButton, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button.addActionListener(this);
        secButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            RacingGameMenu racingGameMenu = new RacingGameMenu();
            racingGameMenu.init();
        } else if (e.getSource() == secButton) {
            GameFrame gameFrame = new GameFrame(playerPersistence);
            gameFrame.initGame();
        }
        dispose();
    }
}
