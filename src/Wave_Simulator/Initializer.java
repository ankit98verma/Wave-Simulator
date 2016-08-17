package Wave_Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;

public class Initializer {
    
    JFrame f;int n;boolean close_b;String wave_type;
    
    List<Wave> wave=new ArrayList<Wave>(){};
    Initializer(){
        f=new JFrame("Input");
        JPanel pnorth=new JPanel();
        pnorth.setLayout(new GridLayout(0,2,5,5));pnorth.setBorder(new EmptyBorder(10,10,10,10));
        JLabel l1=new JLabel("Enter no. of Waves:");
        pnorth.add(l1);
        final JTextField t=new JTextField();
        t.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();
                if(!(Character.isDigit(c)||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
        pnorth.add(t);
        JRadioButton rbprog=new JRadioButton("Progressive Wave");
        rbprog.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                wave_type="PROGRESSIVE_WAVE";
            }
        });pnorth.add(rbprog);pnorth.add(new JLabel());
        JRadioButton rbstand=new JRadioButton("Standing Wave");
        rbstand.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                wave_type="STANDING_WAVE";
            }
        });pnorth.add(rbstand);pnorth.add(new JLabel());
        JRadioButton rbcirc=new JRadioButton("Circular Wave");
        rbcirc.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                wave_type="CIRCULAR_WAVE";
            }
        });pnorth.add(rbcirc);pnorth.add(new JLabel());
        ButtonGroup rbgroup=new ButtonGroup();rbgroup.add(rbprog);rbgroup.add(rbstand);rbgroup.add(rbcirc);
        JPanel psouth=new JPanel();psouth.setBorder(new EmptyBorder(5,5,5,5));
        JButton b=new JButton();b.setIcon(Pics.next);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                n=Integer.parseInt(t.getText());
                f.dispose();
                if(wave_type.equals("PROGRESSIVE_WAVE"))
                    Taker_Progressive();
                else if(wave_type.equals("STANDING_WAVE"))
                    Taker_Standing();
                else if(wave_type.equals("CIRCULAR_WAVE"))
                    Taker_Circular();
            }
        });
        psouth.add(b);b.setFont(new Font("Lucida Handwriting",Font.PLAIN,11));b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton b8=new JButton();b8.setIcon(Pics.open);
        b8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ReadFiles read=new ReadFiles();
                    f.dispose();
                }catch(Exception excep){}
            }
        });psouth.add(b8);b8.setFont(new Font("Lucida Handwriting",Font.PLAIN,11));b8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        Container cp=f.getContentPane();
        cp.add(pnorth,BorderLayout.NORTH);cp.add(psouth,BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
        f.pack();f.setVisible(true);f.setLocationRelativeTo(null);
    }
    
    int i;
    double[] a,lembda,freq, phase;Color c[];
    JTextField t_A[];JTextField t_W[];JTextField t_F[];JTextField t_P[];
    
    public void Taker_Standing() {
        final JFrame f2=new JFrame("Input");
        JPanel p2=new JPanel();
        JLabel l_t1=new JLabel("Wave no. "),l_t2=new JLabel("Amplitude"),l_t3=new JLabel("Wavelength"),l_t4=new JLabel("Frequency"),l_t5=new JLabel("Color");
        p2.add(l_t1);p2.add(l_t2);p2.add(l_t3);p2.add(l_t4);p2.add(l_t5);
        p2.setLayout(new GridLayout(0,5,15,5));
        
        JLabel l[]=new JLabel[n];
        JButton b[]=new JButton[n];
        
        t_A=new JTextField[n];t_W=new JTextField[n];t_F=new JTextField[n];c=new Color[n];
        a=new double[n];lembda=new double[n];freq=new double[n];
        
        for(i=0; i<n;i++){
            l[i]=new JLabel("Wave "+(i+1));
            t_A[i]=new JTextField();t_W[i]=new JTextField();t_F[i]=new JTextField();
            t_A[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            t_W[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            t_F[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            
            b[i]=new JButton("WAVE Color");b[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            b[i].addActionListener(new ActionListener() {
                int m=i;
                @Override
                public void actionPerformed(ActionEvent e) {
                    c[m]=JColorChooser.showDialog(f2, "Wave Color", Color.RED);
                }
            });
            p2.add(l[i]);p2.add(t_A[i]);p2.add(t_W[i]);p2.add(t_F[i]);p2.add(b[i]);
            
        }
        ButtonGroup bug=new ButtonGroup();
        JRadioButton close=new JRadioButton("Close Reflected Wave");
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                close_b=true;
            }
        });p2.add(close);
        JRadioButton open=new JRadioButton("Open Reflected Wave");
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                close_b=false;
            }
        });p2.add(open);bug.add(close);bug.add(open);
        JButton b_in=new JButton();b_in.setIcon(Pics.check);
        JPanel p3=new JPanel();b_in.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b_in.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for(int k=0; k<n; k++){
                    a[k]=Double.parseDouble(t_A[k].getText());
                    lembda[k]=Double.parseDouble(t_W[k].getText());
                    freq[k]=Double.parseDouble(t_F[k].getText());
                    wave.add(new Wave(a[k],lembda[k],freq[k],c[k]));
                    
                }
                Standing_Wave p_w=new Standing_Wave(wave,close_b);f2.dispose();
            }
        });
        Container cp=f2.getContentPane();
        p3.add(b_in);
        cp.add(p2,BorderLayout.NORTH);cp.add(p3,BorderLayout.SOUTH);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f2.setVisible(true);f2.pack();
        f2.setLocationRelativeTo(null);
        f2.setFocusable(true);
        f2.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        });
        f2.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evt){
                f2.requestFocus();
            }
        });
    }
    public void Taker_Progressive() {
        final JFrame f2=new JFrame("Input");
        JPanel p2=new JPanel();
        JLabel l_t1=new JLabel("Wave no. "),l_t2=new JLabel("Amplitude"),l_t3=new JLabel("Wavelength"),l_t4=new JLabel("Frequency"),l_t5=new JLabel("Init Phase"),l_t6=new JLabel("Color");
        p2.add(l_t1);p2.add(l_t2);p2.add(l_t3);p2.add(l_t4);p2.add(l_t5);p2.add(l_t6);
        p2.setLayout(new GridLayout(0,6,15,5));
        
        JLabel l[]=new JLabel[n];
        JButton b[]=new JButton[n];
        
        t_A=new JTextField[n];t_W=new JTextField[n];t_F=new JTextField[n];t_P=new JTextField[n];c=new Color[n];
        a=new double[n];lembda=new double[n];freq=new double[n];phase=new double[n];
        
        for(i=0; i<n;i++){
            l[i]=new JLabel("Wave "+(i+1));
            t_A[i]=new JTextField();t_W[i]=new JTextField();t_F[i]=new JTextField();t_P[i]=new JTextField();
            t_A[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            /*t_W[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    
                    if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });*/
            t_F[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            
            b[i]=new JButton("WAVE Color");b[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            b[i].addActionListener(new ActionListener() {
                int m=i;
                @Override
                public void actionPerformed(ActionEvent e) {
                    c[m]=JColorChooser.showDialog(f2, "Wave Color", Color.RED).darker();
                }
            });
            p2.add(l[i]);p2.add(t_A[i]);p2.add(t_W[i]);p2.add(t_F[i]);p2.add(t_P[i]);p2.add(b[i]);
            
        }
        JButton b_in=new JButton();b_in.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel p3=new JPanel();b_in.setIcon(Pics.check);
        b_in.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for(int k=0; k<n; k++){
                    a[k]=Double.parseDouble(t_A[k].getText());
                    lembda[k]=Double.parseDouble(t_W[k].getText());
                    freq[k]=Double.parseDouble(t_F[k].getText());
                    phase[k]=Double.parseDouble(t_P[k].getText());
                    wave.add(new Wave(a[k],lembda[k],freq[k],phase[k],c[k]));
                }
                Progressive_Wave p_w=new Progressive_Wave(wave);f2.dispose();
            }
        });
        p3.add(b_in);
        Container cp=f2.getContentPane();
        cp.add(p2,BorderLayout.NORTH);
        cp.add(p3,BorderLayout.SOUTH);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f2.setVisible(true);f2.pack();
        f2.setLocationRelativeTo(null);
        f2.setFocusable(true);
        f2.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        });
        f2.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evt){
                f2.requestFocus();
            }
        });
    }
    
    JTextField[] t_X,t_Y;double[] X,Y;
    List<SingCircWave> circWave=new ArrayList<SingCircWave>(){};List<Point2D.Double> point=new ArrayList<Point2D.Double>(){};
    
    public void Taker_Circular(){
        final JFrame f2=new JFrame("Input");
        JPanel pnorth=new JPanel(new GridLayout(0,6,15,5));
        JLabel l_t1=new JLabel("Wave no. "),l_t2=new JLabel("Wavelength"),l_t3=new JLabel("Frequency"),l_t4=new JLabel("Position X"),l_t5=new JLabel("Position Y"),l_t6=new JLabel("Color");
        pnorth.add(l_t1);pnorth.add(l_t2);pnorth.add(l_t3);pnorth.add(l_t4);pnorth.add(l_t5);pnorth.add(l_t6);
        
        JLabel[] l=new JLabel[n];
        JButton[] b=new JButton[n];
        
        t_W=new JTextField[n];t_F=new JTextField[n];t_X=new JTextField[n];t_Y=new JTextField[n];
        lembda=new double[n];freq=new double[n];X=new double[n];Y=new double[n];c=new Color[n];
        
        for(i=0; i<n; i++){
            l[i]=new JLabel("Wave "+(i+1));
            t_W[i]=new JTextField();t_F[i]=new JTextField();t_X[i]=new JTextField();t_Y[i]=new JTextField();
            t_W[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            t_F[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='.'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            t_X[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='-'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            t_Y[i].addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(!(Character.isDigit(c)||c=='-'||(c==' '||c==KeyEvent.VK_DELETE||c==KeyEvent.VK_BACK_SPACE))){
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });
            
            b[i]=new JButton("Wave Color");b[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            b[i].addActionListener(new ActionListener() {
                int m=i;
                @Override
                public void actionPerformed(ActionEvent e) {
                    c[m]=JColorChooser.showDialog(f2, "Wave Color", Color.RED);
                }
            });
            pnorth.add(l[i]);pnorth.add(t_W[i]);pnorth.add(t_F[i]);pnorth.add(t_X[i]);pnorth.add(t_Y[i]);pnorth.add(b[i]);
        }
        JPanel psouth=new JPanel();
        JButton b_in=new JButton();b_in.setIcon(Pics.check);psouth.add(b_in);
        b_in.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b_in.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for(int k=0; k<n; k++){
                    lembda[k]=Double.parseDouble(t_W[k].getText());
                    freq[k]=Double.parseDouble(t_F[k].getText());
                    X[k]=Double.parseDouble(t_X[k].getText());
                    Y[k]=Double.parseDouble(t_Y[k].getText());
                    point.add(new Point2D.Double(X[k],Y[k]));
                    circWave.add(new SingCircWave(lembda[k],freq[k],(Point2D.Double)point.get(k),c[k]));
                    
                }
                PointSource pS=new PointSource(circWave,point);f2.dispose();
            }
        });psouth.add(b_in);
        Container cp=f2.getContentPane();cp.add(pnorth,BorderLayout.NORTH);cp.add(psouth,BorderLayout.SOUTH);
        f2.setVisible(true);f2.pack();f2.setLocationRelativeTo(null);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setFocusable(true);
        f2.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        });
        f2.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evt){
                f2.requestFocus();
            }
        });
    }
}
