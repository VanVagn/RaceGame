package github.com.classes.music;


import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MP  {
    public static Clip clip;
    public static void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String path;
        File file = new File("src/main/resources/audio/ryan.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }



}