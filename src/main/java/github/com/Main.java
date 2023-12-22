package github.com;
import github.com.classes.frame.StartGameMenu;

public class Main {
    private static final StartGameMenu menu = new StartGameMenu();

    public static void main(String[] args) {
        menu.init();
    }
}