package Wave_Simulator;


import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.border.EmptyBorder;

public class Insert_Wave extends JFrame{
    
     Color c; JTextField t_A, t_W ,t_F,t_P; Wave w;
     Progressive_Wave pw;
     Standing_Wave sw;
     public void Progressive(Progressive_Wave pww,int i){
        pw=pww;
        final JFrame f=new JFrame("Insert Progressive Wave");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel p=new JPanel();p.setLayout(new GridLayout(0,6,15,5));p.setBorder(new EmptyBorder(10,10,10,10));
        p.add(new JLabel("Wave no."));p.add(new JLabel("Amplitude"));p.add(new JLabel("Wavelength "));
        p.add(new JLabel("Frequency"));p.add(new JLabel("Init Phase"));p.add(new JLabel("Color"));
        JLabel l=new JLabel("Wave "+(i+1));
        t_A=new JTextField();t_W=new JTextField();t_F=new JTextField();t_P=new JTextField();
        
        t_A.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t_W.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t_F.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t_P.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        
        JButton b=new JButton("Color");
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c=JColorChooser.showDialog(f, "Wave Color", Color.RED);
            }
        });
        p.add(l);p.add(t_A);p.add(t_W);p.add(t_F);p.add(t_P);p.add(b);b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel p2=new JPanel();
        JButton insB=new JButton();insB.setIcon(Pics.add);
        insB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        insB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double a=Double.parseDouble(t_A.getText());
                double lembda=Double.parseDouble(t_W.getText());
                double freq=Double.parseDouble(t_F.getText());
                double phase=Double.parseDouble(t_P.getText());
                w=new Wave(a,lembda,freq,phase,c);w.setPhase(phase);
                pw.wave1.add(w);
                pw.n=pw.wave1.size();
                pw.st.f.dispose();
                pw.st=new Statics(pw.wave1);
                pw.CPW.dispose();
                pw.CPW=new ControlProgWave(pw);
                f.setVisible(false);
            }
        });
        Container cp=f.getContentPane();
        cp.add(p,BorderLayout.NORTH);cp.add(p2,BorderLayout.SOUTH);
        p2.add(insB);f.setVisible(true);f.pack();f.setLocationRelativeTo(null);
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
     public void Standing(Standing_Wave sww,int i){
        sw=sww;
        final JFrame f=new JFrame("Insert Progressive Wave");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        JPanel p=new JPanel();p.setLayout(new GridLayout(0,5,15,5));p.setBorder(new EmptyBorder(10,10,10,10));
        p.add(new JLabel("Wave no."));p.add(new JLabel("Amplitude"));p.add(new JLabel("Wavelength "));
        p.add(new JLabel("Frequency"));p.add(new JLabel("Color"));
        JLabel l=new JLabel("Wave "+(i+1));
        t_A=new JTextField();t_W=new JTextField();t_F=new JTextField();
        
        t_A.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t_W.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t_F.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        
        JButton b=new JButton("Color");b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c=JColorChooser.showDialog(f, "Wave Color", Color.RED);
            }
        });
        p.add(l);p.add(t_A);p.add(t_W);p.add(t_F);p.add(b);
        JPanel p2=new JPanel();
        JButton insB=new JButton();insB.setIcon(Pics.add);insB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        insB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double a=Double.parseDouble(t_A.getText());
                double lembda=Double.parseDouble(t_W.getText());
                double freq=Double.parseDouble(t_F.getText());
                w=new Wave(a,lembda,freq,c);
                sw.wave1.add(w);
                sw.n=sw.wave1.size();
                sw.st.f.dispose();
                sw.st=new Statics(sw.wave1);
                sw.CSW.dispose();
                sw.CSW=new ControlStandWave(sw);
                f.setVisible(false);
            }
        });p2.add(insB);
        Container cp=f.getContentPane();
        cp.add(p,BorderLayout.NORTH);cp.add(p2,BorderLayout.SOUTH);
        f.setVisible(true);f.pack();f.setLocationRelativeTo(null);
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
    PointSource ps;
    JTextField t_X,t_Y;SingCircWave circw;
    public void Circular(PointSource pss,int i){
        ps=pss;
        final JFrame f=new JFrame("Insert Circular Wave");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel pnorth=new JPanel();pnorth.setLayout(new GridLayout(0,6,15,5));pnorth.setBorder(new EmptyBorder(10,10,10,10));
        pnorth.add(new JLabel("Wave no."));pnorth.add(new JLabel("Wavelength"));pnorth.add(new JLabel("Frequency "));
        pnorth.add(new JLabel("Position X"));pnorth.add(new JLabel("Position Y"));pnorth.add(new JLabel("Wave Color"));
        
        pnorth.add(new JLabel("Wave "+(i+1)));
        t_W=new JTextField();t_F=new JTextField();t_X=new JTextField();t_Y=new JTextField();
        
        t_W.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t_F.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t_X.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t_Y.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        
        JButton b=new JButton("Color");b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c=JColorChooser.showDialog(f, "Wave Color", Color.RED);
            }
        });pnorth.add(t_W);pnorth.add(t_F);pnorth.add(t_X);pnorth.add(t_Y);pnorth.add(b);
        JPanel psouth=new JPanel();
        JButton insB=new JButton();insB.setIcon(Pics.add);insB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        insB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double lembda=Double.parseDouble(t_W.getText());
                double freq=Double.parseDouble(t_F.getText());
                double pX=Double.parseDouble(t_X.getText());
                double pY=Double.parseDouble(t_Y.getText());
                Point2D.Double poos=new Point2D.Double(pX,pY);
                circw=new SingCircWave(lembda,freq,poos,c);
                ps.circWave.add(circw);
                ps.pos.add(poos);
                ps.n=ps.circWave.size();
                ps.st.f.dispose();
                ps.st=new Statics(ps.circWave,ps.pos);
                ps.CCW.dispose();
                ps.CCW=new ControlCircWave(ps);
                f.setVisible(false);
            }
        });psouth.add(insB);
        Container cp=f.getContentPane();
        cp.add(pnorth,BorderLayout.NORTH);cp.add(psouth,BorderLayout.SOUTH);
        f.setVisible(true);f.pack();f.setLocationRelativeTo(null);
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
}
