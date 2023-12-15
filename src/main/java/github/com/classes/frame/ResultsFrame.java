package github.com.classes.frame;

import github.com.classes.persistence.PlayerPersistence;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ResultsFrame extends JFrame {
    private PlayerPersistence playerPersistence;
    private ArrayList<Long> allCurTimes;
    JTable jTable;
    int length;

    DefaultTableModel model = new DefaultTableModel();

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
                Integer id = Integer.valueOf(i+1);
                Long time = Long.valueOf(allCurTimes.get(i));
                model.addRow(new Object[]{id,time});
            }
        }

        jTable = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        add(jScrollPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(200, 200);
        setLocation(30,30);
    }
}
