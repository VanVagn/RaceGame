package github.com.classes.music;


import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//public class MP  {
//        private  FileInputStream fileInputStream;
//    Player player;
//        public void startMusic() {
//            try {
//                FileInputStream fileInputStream = new FileInputStream("src/main/resources/audio/ryan.mp3");
//                 player = new Player(fileInputStream);
//                player.play();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        public void endMusic() {
//            player.close();
//    }
//    public static Clip clip;
//    public static void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        String path;
//        File file = new File("src/main/resources/audio/ryan.wav");
//        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//        clip = AudioSystem.getClip();
//        clip.open(audioStream);
//        clip.start();
//    }



//}