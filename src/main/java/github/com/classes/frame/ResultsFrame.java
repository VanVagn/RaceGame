package github.com.classes.frame;

import github.com.classes.persistence.PlayerPersistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static github.com.classes.constants.Consts.*;

public class ResultsFrame extends JFrame implements ActionListener {
    private PlayerPersistence playerPersistence;
    private ArrayList<Long> allCurTimes;
    private JTable jTable;
    private int length;
    private Button back;
    private DefaultTableModel model = new DefaultTableModel();

    public ResultsFrame(PlayerPersistence playerPersistence) {
        this.playerPersistence = playerPersistence;
    }

    public void init() {
        model.addColumn("id");
        model.addColumn("gameTime");
        if (playerPersistence != null) {
            allCurTimes = playerPersistence.getAll();
            length = allCurTimes.size();
            for (int i = 0; i < length; i++) {
                Integer id = Integer.valueOf(i + INDEX_DIFF);
                Long time = Long.valueOf(allCurTimes.get(i));
                model.addRow(new Object[]{id,time});
            }
        }

        jTable = new JTable(model);
        JPanel jPanel = new JPanel();
        back = new Button("Вернуться в начальное меню");
        JScrollPane jScrollPane = new JScrollPane(jTable);

        back.addActionListener(this);

        jPanel.add(back);
        add(jScrollPane);
        add(jPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(RESULTS_FRAME_WIDTH, RESULTS_FRAME_HEIGHT);
        setLocation(RESULTS_FRAME_X, RESULTS_FRAME_Y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            dispose();
            StartGameMenu startGameMenu = new StartGameMenu();
            startGameMenu.init();
        }
    }
}
