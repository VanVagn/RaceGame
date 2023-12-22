package github.com.classes.frame;

import github.com.classes.persistence.PlayerPersistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static github.com.classes.constants.Consts.*;


public class GameEndMenu extends JFrame implements ActionListener{
    private final ImageIcon imageIcon = new ImageIcon("src/main/resources/images/lose.png");
    private JButton button;
    private JButton secButton;
    private PlayerPersistence playerPersistence;

    public GameEndMenu(PlayerPersistence playerPersistence) {
        this.playerPersistence = playerPersistence;
    }

    public void init() {
        setSize(GAME_END_WIDTH, GAME_END_HEIGHT);
        setLocation(GAME_END_X, GAME_END_Y);

        JLabel label = new JLabel(imageIcon);
        JPanel panel = new JPanel();

        button = new JButton("Вернуться в начальное меню");
        secButton = new JButton("Начать новую игру");

        button.addActionListener(this);
        secButton.addActionListener(this);

        panel.add(label);
        panel.add(button, BorderLayout.SOUTH);
        panel.add(secButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            StartGameMenu racingGameMenu = new StartGameMenu();
            racingGameMenu.init();
        } else if (e.getSource() == secButton) {
            GameFrame gameFrame = new GameFrame(playerPersistence);
            gameFrame.initGame();
        }
        dispose();
    }
}
