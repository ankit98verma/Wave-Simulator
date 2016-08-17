package Wave_Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.*;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class PointSource extends JFrame{
    Graphics buffg;
    Image offscreen;
    
    int delay=16,n,controlledWave=-1;
    JPanel p;Thread runner;boolean ani=false;JButton b1;
    String TYPE_INFO="CIRCULAR";
    
    List<Point2D.Double> pos;
    List<SingCircWave> circWave;
    
    Statics st;ControlCircWave CCW;Point2D pressedPoint;
    
    PointSource(List<SingCircWave> ccww,List<Point2D.Double> ppp){
        setUndecorated(true);
        this.circWave=ccww;pos=ppp;n=circWave.size();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);setVisible(true);setSize(814,730);
        p=new JPanel();p.setSize(getWidth(),getHeight());
        
        st=new Statics(circWave,pos);CCW=new ControlCircWave(this);
        
        p=new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                offscreen=createImage(getWidth(), getHeight());
                buffg=offscreen.getGraphics();
                buffg.clearRect(0, 0, getWidth(), getHeight());
                draw((Graphics2D)buffg);
                g.drawImage(offscreen, 0, 0, this);
            }
        };
        Container cp=getContentPane();
        cp.add(p,BorderLayout.CENTER);
        
        runner=new Thread(){
            @Override
            public void run(){
                while(true){
                    if(ani){
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException ex) {}
                        p.repaint();
                        controllingWave();
                    }
                }
                
            }
        };
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me){
                pressedPoint=(Point2D)me.getPoint();
                SingCircWave cirwave;
                if(controlledWave!=-1){
                    cirwave=(SingCircWave)circWave.get(controlledWave);
                    cirwave.setisUpdateable(false);
                }    
            }
            @Override
            public void mouseReleased(MouseEvent me){
                SingCircWave cirwave;
                if(controlledWave!=-1){
                    cirwave=(SingCircWave)circWave.get(controlledWave);
                    cirwave.setisUpdateable(true);
                }    
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me){
                Point2D dragPoint=me.getPoint();
                double diffx=(-pressedPoint.getX()+dragPoint.getX()),diffy=(pressedPoint.getY()-dragPoint.getY());
                Point2D ppoint;
                if(controlledWave!=-1){
                    ppoint=(Point2D)pos.get(controlledWave);
                    ppoint.setLocation(diffx, diffy);st.update();
                    p.repaint();
                }
            }
        });
        
        JPanel p2=new JPanel();p2.setBackground(Color.black);
        b1=new JButton();b1.setBackground(Color.black);b1.setIcon(Pics.play);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(ani==false)
                {    ani=true;b1.setIcon(Pics.pause);}
                else if(ani)
                {   ani=false;b1.setIcon(Pics.play);}
                if(!runner.isAlive())
                {    runner.start();b1.setIcon(Pics.pause);}
                
            }
        });
        p2.add(b1);
        final JButton b3=new JButton();b3.setIcon(Pics.add);
        b3.setBackground(Color.black);b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Insert_Wave inW=new Insert_Wave();
                inW.Circular(PointSource.this, n);
            }
        });p2.add(b3);
        JButton b4=new JButton();b4.setIcon(Pics.remove);
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                removeWave();
            }
        });b4.setBackground(Color.black);p2.add(b4);
        JButton b5=new JButton();b5.setBackground(Color.black);b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!CCW.isVisible())
                CCW=new ControlCircWave(PointSource.this);
            }
        });p2.add(b5);b5.setIcon(Pics.control);
        cp.add(p2,BorderLayout.SOUTH);
    
    }
    public void draw(Graphics2D g){
        g.setColor(Color.black);
        g.fillRect(0,0,p.getWidth(),p.getHeight());g.setColor(Color.white);
        g.drawLine(p.getWidth()/2, 0, p.getWidth()/2, p.getHeight());
        g.drawLine(0, p.getHeight()/2, p.getWidth(), p.getHeight()/2);
       
        for(int i=0; i<n; i++){
            Point2D poos=(Point2D.Double)pos.get(i);
            SingCircWave singWave=(SingCircWave)circWave.get(i);
            singWave.setPos(poos,p);
            if(singWave.isVisible)
                singWave.drawWave((Graphics2D)buffg);
            singWave.update();
        }
    }
    public void controllingWave(){
        controlledWave=CCW.getControlledWave();
    }
    
    public void removeWave(){
        final JFrame f=new JFrame("Remove Wave");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel pp=new JPanel();pp.setLayout(new GridLayout(0,2,15,5));pp.setBorder(new EmptyBorder(10,10,10,10));
        JLabel l=new JLabel("Enter the Wave No.");pp.add(l);
        final JTextField txt=new JTextField();pp.add(txt);
        
        txt.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        
        Container cp=f.getContentPane();
        JPanel pp2=new JPanel();
        JButton bb=new JButton();bb.setIcon(Pics.remove);
        bb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            int wn=Integer.parseInt(txt.getText());
            if(wn!=0){
                circWave.remove(wn-1);
                pos.remove(wn-1);
            }
            else
                JOptionPane.showMessageDialog(f,"Enter Genuine Value" , "Error!", JOptionPane.ERROR_MESSAGE);
            n=circWave.size();st.f.dispose();CCW.dispose();
            st=new Statics(circWave,pos);CCW=new ControlCircWave(PointSource.this);
            f.dispose();
            }
        });
        pp2.add(bb);
        cp.add(pp,BorderLayout.NORTH);cp.add(pp2,BorderLayout.SOUTH);
        f.setVisible(true);f.setSize(300,115);f.setLocationRelativeTo(null);
        f.setFocusable(true);
        f.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    f.dispose();
            }
        });
        f.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evt){
                f.requestFocus();
            }
        });
    }
    public void disposeAll(){
        this.CCW.dispose();this.st.f.dispose();this.dispose();
    }
}
