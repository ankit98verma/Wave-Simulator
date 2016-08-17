/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wave_Simulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;
        

public class SingCircWave extends ArrayList{
    
    double lembda,freq;int delay=16;Point2D pos;JPanel p;Color c;boolean isVisible=true,isUpAllow=true;
    
    SingCircWave(){}
    
    SingCircWave(double l, double f,Point2D pos,Color c){
        super();this.lembda=l;this.freq=f;this.pos=pos;this.c=c;
        add(new CircWaveInfo(lembda,freq,delay));
        
    }
    public void setPos(Point2D poos,JPanel p){
        double xx=p.getWidth()/2+poos.getX(),yy=p.getHeight()/2-poos.getY();
        this.pos=new Point2D.Double(xx,yy);this.p=p;
    }
    public void drawWave(Graphics2D g2d){
        for(int i=0; i<size(); i++){
            CircWaveInfo CWI=(CircWaveInfo)get(i);
            drawThikWave(g2d,CWI);
        }
    }
    public void drawThikWave(Graphics2D g2d,CircWaveInfo CWI){
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
        g2d.setColor(c);double insR=CWI.getR();g2d.setStroke(new BasicStroke((float)lembda/8));
        g2d.draw(new Ellipse2D.Double(pos.getX()-insR+lembda/4,pos.getY()-insR+lembda/4,2*(insR-lembda/4),2*(insR-lembda/4)));
        }
    
    public void update(){
        if(isUpAllow){
            for(int i=0; i<size(); i++){
                CircWaveInfo CWI=(CircWaveInfo)get(i);
                if(CWI.getT()==CWI.getLimit())
                    addWave();
                if(size()>=20)
                    removeWave();
                CWI.updateR();CWI.updateT();
            }
        }
    }
    public void addWave(){add(new CircWaveInfo(lembda,freq,delay));}
    public void removeWave(){remove(0);}
    public double diffect(){
        double diff=lembda%delay;double diffectVar=diff/((CircWaveInfo)get(0)).getSpeed();
        return diffectVar/10;
    }
    
    public Color getColor(){return c;}
    
    public double getWavelength(){return lembda;}

    public void setWavelength(double wavele){lembda=wavele;}
    
    public double getFrequency(){return freq;}

    public void setFrequency(double fre){freq=fre;}
    
    public double getX(){return pos.getX();}
    public double getY(){return pos.getY();}
    
    public void setXY(Point2D pos){this.pos=pos;}
    public Point2D getXY(){return pos;}
    
    
    public void setVisible(boolean visi){isVisible=visi;}
    public boolean getVisible(){return isVisible;}
    
    public void setisUpdateable(boolean upAl){isUpAllow=upAl;}
    
    public boolean getisUpAllow(){return isUpAllow;}
    
    public void restart(){
    add(new CircWaveInfo(lembda,freq,delay));
    }
}