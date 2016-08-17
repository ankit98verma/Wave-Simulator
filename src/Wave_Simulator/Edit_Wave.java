package Wave_Simulator;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Edit_Wave {
    Wave w;
    JTextField t1,t2,t3,t4;
    JFrame f;
    Edit_Wave(Wave wa,int i,final JPanel pp){
        this.w=wa;f=new JFrame("EDIT Wave"+i);
        JPanel p=new JPanel();p.setBorder(new EmptyBorder(10,10,10,10));
        p.setLayout(new GridLayout(0,2,15,15));
        JLabel l1=new JLabel("Amplitude"),l2=new JLabel("Wavelength"),l3=new JLabel("Frequency");
        t1=new JTextField();t2=new JTextField();t3=new JTextField();
        t1.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t2.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t3.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        p.add(l1);p.add(t1);p.add(l2);p.add(t2);p.add(l3);p.add(t3);
        JPanel pp3=new JPanel();
        JButton b=new JButton("Change Parameters");b.setFont(new Font("Lucida Handwriting",Font.PLAIN,11));
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                w.setAmplitude(Double.parseDouble(t1.getText()));
                w.setWavelength(Double.parseDouble(t2.getText()));
                w.setFrequency(Double.parseDouble(t3.getText()));pp.repaint();
                f.dispose();
            }
        });pp3.add(b);b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Container cp=f.getContentPane();
        cp.add(p,BorderLayout.NORTH);cp.add(pp3,BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);f.setVisible(true);f.setSize(300,200);
        f.setLocationRelativeTo(null);
        f.setFocusable(true);
        f.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    f.dispose();
            }
        });
        f.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent evt){
                f.requestFocus();
            }
        });
        
    }
    
    SingCircWave circWave;Point2D.Double pos;
    Edit_Wave(SingCircWave circwave,int i, final JPanel pp,Point2D.Double poos){
        f=new JFrame("Edit Wave"+i);circWave=circwave;pos=poos;
        JPanel pnorth=new JPanel(new GridLayout(0,2,15,5));pnorth.setBorder(new EmptyBorder(10,10,10,10));
        JLabel l1=new JLabel("Wavelength"),l2=new JLabel("Frequency"),l3=new JLabel("Position X: "),l4=new JLabel("Position Y:");
        t1=new JTextField();t2=new JTextField();t3=new JTextField();t4=new JTextField();
        
        t1.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t2.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t3.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        t4.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        
        pnorth.add(l1);pnorth.add(t1);pnorth.add(l2);pnorth.add(t2);pnorth.add(l3);pnorth.add(t3);pnorth.add(l4);pnorth.add(t4);
        JPanel psouth=new JPanel();
        JButton b=new JButton("Change Parameters");b.setFont(new Font("Lucida Handwriting",Font.PLAIN,11));
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<circWave.size(); i++)
                    circWave.remove(i);
                circWave.setWavelength(Double.parseDouble(t1.getText()));
                circWave.setFrequency(Double.parseDouble(t2.getText()));
                pos.setLocation(Double.parseDouble(t3.getText()), Double.parseDouble(t4.getText()));
                circWave.restart();
                pp.repaint();
                f.dispose();
            }
        });psouth.add(b);
        Container cp=f.getContentPane();
        cp.add(pnorth,BorderLayout.NORTH);cp.add(psouth,BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);f.setVisible(true);f.setSize(300,190);
        f.setLocationRelativeTo(null);
        f.setFocusable(true);
        f.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    f.dispose();
            }
        });
        f.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent evt){
                f.requestFocus();
            }
        });
    }
}

