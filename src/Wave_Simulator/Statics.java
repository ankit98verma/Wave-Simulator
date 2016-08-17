package Wave_Simulator;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Statics {
    int i;static JPanel p;int k;int n;
    List<Wave> wave1=new ArrayList<Wave>(){};
    JFrame f;
    Statics(List<Wave> wa){
        wave1=wa;
        n=wave1.size();
        f=new JFrame("Statics");
        
        p=new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2d=(Graphics2D)g;g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                g2d.setFont(new Font("Agency FB",Font.PLAIN,20));
                for(k=0; k<n; k++){
                    Wave ww=(Wave)wave1.get(k);
                    g2d.setColor(ww.getColor());
                    g2d.drawString("Wave "+(k+1), 10, 20+k*19);
                    g2d.drawString("Amplitude: "+ww.getAmplitude(), 70, 20+k*19);
                    g2d.drawString("Wavelength: "+ww.getWavelength(), 180, 20+k*19);
                    g2d.drawString("Frequency: "+ww.getFrequency(), 300, 20+k*19);
                    g2d.drawString("Initial Phase: "+gettingPhase(ww.getPhase()), 405, 20+k*19);
                    
                }g2d.setColor(Color.black);
            }
        };
        List<JToggleButton> cl=new ArrayList<JToggleButton>(){};
        List<JButton> bl=new ArrayList<JButton>(){};
        JPanel p2=new JPanel();
        p2.setLayout(new GridLayout(n,0,10,10));p2.setBackground(Color.white);
        for(i=0; i<n; i++){
            bl.add(new JButton("Wave "+(i+1)));
            JButton bb=(JButton)bl.get(i);bb.setIcon(Pics.edit);
            bb.setCursor(new Cursor(Cursor.HAND_CURSOR));
            bb.addActionListener(new ActionListener() {
                int m=i;
                @Override
                public void actionPerformed(ActionEvent e) {
                    Wave ww=(Wave)wave1.get(m);
                    Edit_Wave edW=new Edit_Wave(ww,m+1,p);
                }
            });
            p2.add(bb);
            cl.add(new JToggleButton("Wave "+(i+1)));
            final JToggleButton cc=(JToggleButton)cl.get(i);
            cc.setSelected(true);cc.setIcon(Pics.on);cc.setFont(new Font("Lucida Handwriting",Font.PLAIN,11));
            cc.addItemListener(new ItemListener() {
                int m=i;
                @Override
                public void itemStateChanged(ItemEvent e) {
                    Wave ww=(Wave)wave1.get(m);
                    if(e.getStateChange()==ItemEvent.SELECTED)
                    {    ww.setVisible(true);cc.setIcon(Pics.on);}
                    else if(e.getStateChange()!=ItemEvent.SELECTED)
                    {   ww.setVisible(false);cc.setIcon(Pics.off);}
                }
            });p2.add(cc);
        }
        JButton switchingB=new JButton();switchingB.setIcon(Pics.switchs);
        switchingB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame[] arrF=Frame.getFrames();
                for(int i=0; i<arrF.length; i++)
                    arrF[i].dispose();
                Initializer init=new Initializer();
            }
        });
        Container cp=f.getContentPane();
        cp.add(p,BorderLayout.CENTER);cp.add(p2,BorderLayout.SOUTH);
        cp.add(switchingB, BorderLayout.PAGE_START);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);f.setSize(550,300);
        f.setLocation(814,0);
        f.setFocusable(true);
        f.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        });
        f.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evt){
                f.requestFocus();
            }
        });
    }
    List<SingCircWave> circWave;List<Point2D.Double> pos;
    Statics(List<SingCircWave> wa,List<Point2D.Double> pops){
        circWave=wa;pos=pops;
        f=new JFrame("Statics");n=wa.size();
        p=new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2d=(Graphics2D)g;g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                g2d.setFont(new Font("Lucida Handwriting",Font.PLAIN,12));
                for(k=0; k<n; k++){
                    SingCircWave ww=(SingCircWave)circWave.get(k);
                    g2d.setColor(ww.getColor());
                    g2d.drawString("Wave "+(k+1), 10, 20+k*15);
                    g2d.drawString("Wavelength: "+ww.getWavelength(), 80, 20+k*15);
                    g2d.drawString("Frequency: "+ww.getFrequency(), 200, 20+k*15);
                    g2d.drawString("Position X: "+(int)(((Point2D.Double)pos.get(k)).getX()), 315, 20+k*15);
                    g2d.drawString("Position Y: "+(int)(((Point2D.Double)pos.get(k)).getY()), 420, 20+k*15);
                    
                }g2d.setColor(Color.black);
            }
        };
        List<JToggleButton> cl=new ArrayList<JToggleButton>(){};
        List<JButton> bl=new ArrayList<JButton>(){};
        JPanel psouth=new JPanel(new GridLayout(n,0,15,5));psouth.setBackground(Color.white);
        for(i=0; i<n; i++){
            bl.add(new JButton("Wave "+(i+1)));
            JButton bb=(JButton)bl.get(i);bb.setIcon(Pics.edit);
            bb.setCursor(new Cursor(Cursor.HAND_CURSOR));
            bb.addActionListener(new ActionListener() {
                int m=i;
                @Override
                public void actionPerformed(ActionEvent e) {
                    SingCircWave ww=(SingCircWave)circWave.get(m);
                    Point2D.Double poos=(Point2D.Double)pos.get(m);
                    Edit_Wave edW=new Edit_Wave(ww,m+1,p,poos);
                }
            });psouth.add(bb);
            cl.add(new JToggleButton("Wave "+(i+1)));
            final JToggleButton cc=(JToggleButton)cl.get(i);
            cc.setSelected(true);cc.setIcon(Pics.on);cc.setFont(new Font("Lucida Handwriting",Font.PLAIN,11));
            cc.addItemListener(new ItemListener() {
                int m=i;
                @Override
                public void itemStateChanged(ItemEvent e) {
                    SingCircWave ww=(SingCircWave)circWave.get(m);
                    if(e.getStateChange()==ItemEvent.SELECTED)
                    {    ww.setVisible(true);cc.setIcon(Pics.on);}
                    else if(e.getStateChange()!=ItemEvent.SELECTED)
                    {    ww.setVisible(false);cc.setIcon(Pics.off);}
                }
            });psouth.add(cc);
        }
        JButton switchingB=new JButton();switchingB.setIcon(Pics.switchs);
        switchingB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame[] arrF=Frame.getFrames();
                for(int i=0; i<arrF.length; i++)
                    arrF[i].dispose();
                Initializer init=new Initializer();
            }
        });
        Container cp=f.getContentPane();
        cp.add(p,BorderLayout.CENTER);cp.add(psouth,BorderLayout.SOUTH);
        cp.add(switchingB, BorderLayout.PAGE_START);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);f.setSize(550,300);
        f.setLocation(814,0);
        f.setFocusable(true);
        f.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        });
        f.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evt){
                f.requestFocus();
            }
        });
    }
    public void update(){p.repaint();}
    public double gettingPhase(double pha){
        return pha%360;
    }
    
}

