import javax.swing.*;

/**
 * Created by Abhishek on 1/16/2017.
 */
public class Game {
    public static BackgroundPanel main_screen;

    public static void main(String args[]){
        JFrame frame = new JFrame("Mario");
        frame.setSize(645,365);
        main_screen = new BackgroundPanel();
        frame.add(main_screen);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
