package Wave_Simulator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class ControlStandWave extends JFrame{
    
    int controlWave=-1,i;
    ButtonGroup bg=new ButtonGroup();
    JRadioButton[] rb;JRadioButton none;
    Standing_Wave sww;
    
    ControlStandWave(Standing_Wave sw){
        super("Contol Wave");
        sww=sw;
        rb=new JRadioButton[sw.n];none=new JRadioButton("None",true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p=new JPanel(new GridLayout(0,1,15,5));p.setBorder(new EmptyBorder(10,10,10,10));
        for(i=0; i<sw.n; i++){
            rb[i]=new JRadioButton("Control Wave "+(i+1),false);rb[i].setFont(new Font("Lucida Handwriting",Font.PLAIN,11));
            rb[i].setBackground(Color.white);
            rb[i].addActionListener(new ActionListener() {
                int m=i;
                @Override
                public void actionPerformed(ActionEvent e) {
                    controlWave=m;
                }
            });
            p.add(rb[i]);
            bg.add(rb[i]);
        }
        none.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlWave=-1;
            }
        });none.setFont(new Font("Lucida Handwriting",Font.PLAIN,11));none.setBackground(Color.white);
        
        bg.add(none);p.add(none);p.setBackground(Color.white);
        JButton b6=new JButton();b6.setBackground(Color.white);b6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SaveAsImage.saveImage((BufferedImage)sww.offscreen);
            }
        });p.add(b6);b6.setIcon(Pics.camera);
        JButton b7=new JButton();b7.setBackground(Color.white);b7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SaveFiles.saveStandingFile(sww);
            }
        });p.add(b7);b7.setIcon(Pics.save);
        JButton b8=new JButton();b8.setBackground(Color.white);b8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ReadFiles read=new ReadFiles();
                    if(read.getStatus()==JFileChooser.APPROVE_OPTION){
                        sww.disposeAll();
                    }
                }catch(Exception excep){}
            }
        });p.add(b8);b8.setIcon(Pics.open);
        setContentPane(p);
        setSize(300,(int)(90*(sw.n+1)+25));setVisible(true);setLocation(814, 300);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    ControlStandWave.this.dispose();
            }
        });
        this.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent evt){
                ControlStandWave.this.requestFocus();
            }
        });
        
    }
    public int getControlledWave(){return controlWave;}
    
}
