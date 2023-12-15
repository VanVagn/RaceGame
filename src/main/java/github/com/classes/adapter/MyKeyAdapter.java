package github.com.classes.adapter;

import github.com.classes.raceClasses.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {
    Player player;
    public MyKeyAdapter(Player player) {
        this.player = player;
    }

    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
}
