package Wave_Simulator;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.EmptyBorder;


public class Progressive_Wave extends JFrame{   //used for simulation of progressive waves
    
    Graphics buffg;
    Image offscreen;
    
    boolean ani;int delay=16,n,controlledWave=-1;Statics st;double x, y;ControlProgWave CPW;
    
    Thread runner;
    JPanel p,p1;Point2D pressedPoint;
    static String TYPE_INFO="PROGRESSIVE";
    
    List<Wave> wave1=new ArrayList<Wave>(){};
    
    Progressive_Wave(List<Wave> wa){
        wave1=(wa);setUndecorated(true);
        setVisible(true);setSize(814,730);
        n=wave1.size();st=new Statics(wave1);CPW=new ControlProgWave(this);
        setTitle("Progressive Waves");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p=new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                offscreen=createImage(getWidth(),getHeight());
                buffg=offscreen.getGraphics();
                buffg.clearRect(0, 0, p.getWidth(), p.getHeight());
                draw((Graphics2D)buffg);
                g.drawImage(offscreen, 0, 0, this);
            }
        };
        
        runner=new Thread(){
            @Override
            public void run()
            {
                while(true)
                {
                    if(ani){
                        for(int i=0; i<n; i++){
                            Wave ww=(Wave)wave1.get(i);
                            ww.updatePhase();st.update();
                        }
                        try{Thread.sleep(delay);
                        }catch(Exception ex){}
                        p.repaint();
                        controllingWave();
                    }
                }
            }
        };
        Container cp=getContentPane();
        p1=new JPanel();p1.setBackground(Color.BLACK);
        final JButton b1=new JButton();p1.add(b1);b1.setBackground(Color.BLACK);b1.setIcon(Pics.play);
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
        final JButton b3=new JButton();b3.setIcon(Pics.add);b3.setBackground(Color.black);
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Insert_Wave inW=new Insert_Wave();
                inW.Progressive(Progressive_Wave.this, n);
            }
        });p1.add(b3);
        JButton b4=new JButton();b4.setIcon(Pics.remove);b4.setBackground(Color.black);
        b4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                removeWave();
                
            }
        });p1.add(b4);
        JButton b5=new JButton();b5.setIcon(Pics.control);b5.setBackground(Color.black);
        b5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!CPW.isVisible())
                CPW=new ControlProgWave(Progressive_Wave.this);
            }
        });p1.add(b5);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        cp.add(p,BorderLayout.CENTER);cp.add(p1,BorderLayout.SOUTH);
        setContentPane(cp);
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                Point2D dragPoint=e.getPoint();
                double diffx=(-pressedPoint.getX()+dragPoint.getX());
                Wave ww;
                if(controlledWave!=-1){
                    ww=(Wave)wave1.get(controlledWave);
                    ww.increasePhaseby(diffx);
                    p.repaint();
                }
                pressedPoint=dragPoint;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point pp=MouseInfo.getPointerInfo().getLocation();
                x=pp.getX();y=-suY(x);
                p.repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent meve){
                pressedPoint=meve.getPoint();
                Wave ww;
                if(controlledWave!=-1){
                    ww=(Wave)wave1.get(controlledWave);
                    ww.setIsUpdateable(false);
                }
            }
            @Override
            public void mouseReleased(MouseEvent meve){
                Wave ww;
                if(controlledWave!=-1){
                    ww=(Wave)wave1.get(controlledWave);
                    ww.setIsUpdateable(true);
                }
                
            }
        });
    }
    public double suY(double x){
        double y2[]=new double[n];double ss=0;
        for(int i=0; i<n;i++){
            Wave ww=(Wave)wave1.get(i);
            y2[i]=ww.getY(x);
            ss=ss+y2[i];
        }
        return ss;
    }
    public void draw(Graphics2D g2d){
        RenderingHints render=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);g2d.setRenderingHints(render);            
        g2d.setColor(Color.BLACK);g2d.fillRect(0,0,p.getWidth(),p.getHeight());
        g2d.setColor(Color.GREEN);
        g2d.drawLine(0, p.getHeight()/2,p.getWidth(), p.getHeight()/2);
        for(int i=0; i<n;i++){
            Wave ww=(Wave)wave1.get(i);
            Path2D path=ww.getProgressivePath(p);g2d.setColor(ww.getColor());
            if(ww.visible)
                g2d.draw(path);
        }
        Path2D path=Wave.superProgressivePath(p, wave1);
        g2d.setColor(Color.WHITE);
        g2d.draw(path);      
        
        Point pp=MouseInfo.getPointerInfo().getLocation();x=pp.getX();
        y=-suY(x);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2d.setFont(new Font("Lucida Handwriting",Font.PLAIN,14));
        g2d.drawString("x: "+x+" y: "+y, 5, 15);
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
        JButton bb=new JButton();bb.setIcon(Pics.remove);bb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            int wn=Integer.parseInt(txt.getText());
            if(wn!=0)
            wave1.remove(wn-1);
            else
                JOptionPane.showMessageDialog(f,"Enter Genuine Value" , "Error!", JOptionPane.ERROR_MESSAGE);
            n=wave1.size();st.f.dispose();CPW.dispose();
            st=new Statics(wave1);CPW=new ControlProgWave(Progressive_Wave.this);
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
    public void controllingWave(){
        controlledWave=CPW.getControlledWave();
    }
    public void disposeAll(){
        this.CPW.dispose();this.st.f.dispose();this.dispose();
    }
}
