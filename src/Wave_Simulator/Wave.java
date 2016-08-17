
package Wave_Simulator;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.io.Serializable;
import javax.swing.JPanel;
import java.util.List; 

public class Wave implements Serializable
{
    boolean visible=true,isUpAllow=true;
    //according to Computer parameter
    double a,lembda,freq;int ratio=2;Color c;
    double Phase,t;
    
    private final static long serialVersionUID=1002L;
    
    Wave(){}
    Wave(double a,double lembda,double freq, Color c)
    {   this.a=a;this.lembda=lembda;this.freq=freq;this.c=c;}
    Wave(double a,double lembda,double freq, double init_Phase,Color c)
    {   this.a=a;this.lembda=lembda;this.freq=freq;this.c=c;this.Phase=init_Phase;}
    public double getY(double x)
    {
        double y=-a*Math.sin(2*Math.PI*x/lembda-Math.toRadians(Phase));   
        return y;
    }
    public double getY_Stand_Close(double x)
    {
        double y=-2*a*Math.sin(2*Math.PI*x/lembda)*Math.cos(2*Math.PI*t*freq)/ratio;
        return y;
    }
    public double getY_Stand_Open(double x)
    {
        double y=-2*a*Math.cos(2*Math.PI*x/lembda)*Math.sin(2*Math.PI*t*freq)/ratio;
        return y;
    }
    
    public Color getColor(){   return c;}
    public void setColor(Color c){this.c=c;}
    
    public double getAmplitude(){return a;}

    public void setAmplitude(double a){this.a=a;}
    
    public double getWavelength(){return lembda;}

    public void setWavelength(double lembda){this.lembda=lembda;}
    
    public double getFrequency(){return freq;}

    public void setFrequency(double freq){this.freq=freq;}
    
    public double getPhase(){return Phase;}

    public void setPhase(double init_Phase){this.Phase=init_Phase;}
    
    public double getTime(){return t;}

    public void setTime(double t){this.t=t;}
    
    public boolean getVisible(){return visible;}

    public void setVisible(boolean b){this.visible=b;}
    
    public void updatePhase(){if(isUpAllow)Phase+=freq*7.2;}
    public void updateTime(){if(isUpAllow)t+=16*.001;}
    public void increasePhaseby(double incPhase){Phase+=incPhase;}
    public void increaseTimeby(double incTime){t+=incTime;}
    
    public void setIsUpdateable(boolean isup){isUpAllow=isup;}
    public Path2D getProgressivePath(JPanel ps){
        Path2D p=new Path2D.Double();p.moveTo(0,0);
        for(int x=0; x<=ps.getWidth(); x+=1)
            p.lineTo(x,ps.getHeight()/2+getY(x));
        return p;
    }
    public Path2D getStandingPathOpen(JPanel sw){
        Path2D p=new Path2D.Double();p.moveTo(0,0);
        for(int x=0; x<=sw.getWidth(); x+=1)
            p.lineTo(x,sw.getHeight()/2+getY_Stand_Open(x));
        return p;
    }
    public Path2D getStandingPathClose(JPanel sw){
        Path2D p=new Path2D.Double();p.moveTo(0,0);
        for(int x=0; x<=sw.getWidth(); x+=1)
            p.lineTo(x,sw.getHeight()/2+getY_Stand_Close(x));
        return p;
    }
    public static Path2D superProgressivePath(JPanel ps,List<Wave> waves){
        Path2D path=new Path2D.Double();path.moveTo(0,0);
        for(int x=0; x<=ps.getWidth(); x++){   
            double y=0;
            for(int i=0; i<waves.size(); i++){
                Wave ww=(Wave)waves.get(i);
                y+=ww.getY(x);
            }
            path.lineTo(x,ps.getHeight()/2+y);
        }
        return path;
    }
    public static Path2D superStandingClosePath(JPanel ps,List<Wave> waves){
        Path2D path=new Path2D.Double();path.moveTo(0, 0);
        for(int x=0; x<=ps.getWidth(); x++){
            double y=0;
            for(int i=0; i<waves.size(); i++){
                Wave ww=(Wave)waves.get(i);
                y+=ww.getY_Stand_Close(x);
            }
            path.lineTo(x,ps.getHeight()/2+y);
        }
        return path;
    }
    public static Path2D superStandingOpenPath(JPanel ps,List<Wave> waves){
        Path2D path=new Path2D.Double();path.moveTo(0, 0);
        for(int x=0; x<=ps.getWidth(); x++){
            double y=0;
            for(int i=0; i<waves.size(); i++){
                Wave ww=(Wave)waves.get(i);
                y+=ww.getY_Stand_Open(x);
            }
            path.lineTo(x,ps.getHeight()/2+y);
        }
        return path;
    }
}

