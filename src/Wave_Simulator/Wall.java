package Wave_Simulator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Wall {
    JFrame f;
    ImageIcon im=new ImageIcon("C:\\Users\\Ankit\\Documents\\NetBeansProjects\\Wave Simulator\\src\\Wave_Simulator\\theWall.jpg");
    
    Wall() {
        Pics pic=new Pics();
        f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setUndecorated(true);
        fade(f,true);
        JPanel p=new JPanel(){
        
            @Override
            public void paintComponent(Graphics g){
                g.drawImage(im.getImage(), 0, 0, this);
                //g.setColor(Color.red);g.fillRect(0, 0, 420, 300);
            }
        };
        f.setContentPane(p);
        f.setSize(700,393);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Initializer init=new Initializer();
                f.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
    }
    private static final int TIME = 1700;
    private static final int MILLIS_PER_FRAME = 16;
    private static final float DELTA = MILLIS_PER_FRAME / (float)TIME; //how much the opacity will change on each tick

    /**
     * @param frame the frame to fade in or out
     * @param in true if you are fading in, false if you're fading out
     */
    public static void fade(final JFrame frame, final boolean in)
    {
        frame.setOpacity(in ? 0f : 1f); //if we're fading in, make sure our opacity is 0, and 1 if we're fading out
        if (in) //set the state back to normal because we might have been minimized
            frame.setState(JFrame.NORMAL); 
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask()
        {
            float opacity = in ? 0f : 1f;
            float delta = in ? DELTA : -DELTA;

            @Override
            public void run()
            {
                opacity += delta; //tweak the opacity
                if (opacity < 0) //we're invisible now
                {
                   opacity=0;
                   timer.cancel();//stop the timer
                }
                else if (opacity > 1) //we're fully visible now
                {
                    opacity=1; //make the opacity an even 1.0f
                    timer.cancel(); //stop the timer
                }
                else
                    frame.setOpacity(opacity);
            }
        };
        timer.scheduleAtFixedRate(timerTask, MILLIS_PER_FRAME, MILLIS_PER_FRAME);
    }
}
