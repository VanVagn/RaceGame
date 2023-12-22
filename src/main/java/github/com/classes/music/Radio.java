package github.com.classes.music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Radio implements Runnable {
    private FileInputStream input;
    private Player player;
    private Thread thread = new Thread(this);
    public void start() {
        thread.start();
    }
    public void stop() {
        thread.stop();
    }

    @Override
    public void run() {
        try {
            input = new FileInputStream("src/main/resources/audio/kavinskyNightcall.mp3");
            player = new Player(input);
            player.play();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }
}
