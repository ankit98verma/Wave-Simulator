package Wave_Simulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class ControlCircWave extends JFrame{
    
    int controlWave=-1,i;
    ButtonGroup bg=new ButtonGroup();
    JRadioButton[] rb;JRadioButton none;
    PointSource pss;
    
    ControlCircWave(PointSource ps){
        super("Contol Wave");
        pss=ps;
        rb=new JRadioButton[ps.n];none=new JRadioButton("None",true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p=new JPanel(new GridLayout(0,1,15,5));p.setBorder(new EmptyBorder(10,10,10,10));
        p.setBackground(Color.white);
        for(i=0; i<ps.n; i++){
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
        bg.add(none);p.add(none);
        JButton b6=new JButton();b6.setBackground(Color.white);
        b6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SaveAsImage.saveImage((BufferedImage)pss.offscreen);
            }
        });p.add(b6);b6.setIcon(Pics.camera);
        JButton b7=new JButton();b7.setBackground(Color.white);
        b7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SaveFiles.saveCircularFile(pss);
            }
        });p.add(b7);b7.setIcon(Pics.save);
        JButton b8=new JButton();b8.setBackground(Color.white);
        b8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ReadFiles read=new ReadFiles();
                    if(read.getStatus()==JFileChooser.APPROVE_OPTION){
                        pss.disposeAll();
                    }
                }catch(Exception excep){}
            }
        });p.add(b8);b8.setIcon(Pics.open);
        this.setContentPane(p);
        setSize(300,(int)(90*(ps.n+1)+25));this.setVisible(true);this.setLocation(814, 300);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    ControlCircWave.this.dispose();
            }
        });
        this.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent evt){
                ControlCircWave.this.requestFocus();
            }
        });
        
    }
    public int getControlledWave(){return controlWave;}
    
}
