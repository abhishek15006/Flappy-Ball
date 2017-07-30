import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Abhishek on 1/16/2017.
 */
public class BackgroundPanel extends JPanel implements Runnable,KeyListener {
    BufferedImage back_image;
    JLabel score;
    int x,y;
    public int width,height;
    final int rate = 2;
    LinkedList<Obstacle> obstacles;
    boolean touched = false;
    Thread t;
    public BackgroundPanel(){
        obstacles = new LinkedList<>();
        score = new JLabel("Score : 0");
        score.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));

        x = 0;
        y = 200;
        width = 0;
        height = 0;
        try{
            back_image = ImageIO.read(new File("./src/back2.jpg"));
            width = back_image.getWidth();
            height = back_image.getHeight();
        }catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        add(score,BorderLayout.PAGE_START);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        t = new Thread(this);
        t.start();
    }

    public void run(){

        int t = 0;


        while(!touched){
            x += rate;
            y += 1;

            score.setText("Score : " + String.valueOf(t));
            if(x>=width)
                x = 0;

            if(t%100==0)
                obstacles.add(new Obstacle(this));
            if(!obstacles.isEmpty())
                if(obstacles.get(0).getX()<=obstacles.get(0).getW())
                    obstacles.removeFirst();

            repaint();

            try{

                Thread.sleep(10);
                t++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        add(score,BorderLayout.CENTER);
        for(int i=0;i<80;++i){
            score.setFont(new Font(Font.SANS_SERIF,Font.BOLD,i));

            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(back_image,0,0,width-x,height,x,0,width,height,null);

        g.drawImage(back_image,width-x,0,width,height,0,0,x,height,null);

        g.setColor(Color.white);
        g.fillOval(10,y,20,20);

        g.setColor(Color.red);
        ListIterator<Obstacle> it = obstacles.listIterator();
        for(int i=0;i<obstacles.size();++i){

            if(obstacles.get(i).getY()<=y && (obstacles.get(i).getY() + obstacles.get(i).getH())>=(y + 20) && obstacles.get(i).getX()<=30)
                touched = true;
            if(y<0 || y>(height - 20))
                touched = true;

            g.fillRect(obstacles.get(i).getX(),obstacles.get(i).getY(),obstacles.get(i).getW(),obstacles.get(i).getH());

            if(!touched)
                obstacles.get(i).decX(rate);

        }
    }

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public int getW(){return width;}
    public int getH(){return height;}
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y -= 20;
            //repaint();
        }
    }


    public void keyReleased(KeyEvent e) {
    }
}
