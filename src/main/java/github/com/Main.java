package github.com;


import github.com.classes.frame.GameEndMenu;
import github.com.classes.frame.RacingGameMenu;

public class Main {
    private static final RacingGameMenu menu = new RacingGameMenu();

    public static void main(String[] args) {
        menu.init();
    }
}