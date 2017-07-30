import javax.swing.*;
import java.util.Random;

/**
 * Created by Abhishek on 1/20/2017.
 */
public class Obstacle {
    final int w = 10;
    int x,y,h;

    public Obstacle(BackgroundPanel panel){
        Random rand = new Random();
        x = panel.getW();
        y = rand.nextInt(panel.getH() - 100);
        h = rand.nextInt(150) + 50;
    }

    int getW(){
        return w;
    }

    int getH(){
        return h;
    }

    int getY(){
        return y;
    }

    int getX(){
        return x;
    }

    void decX(int dec){
        x -= dec;
    }
}
