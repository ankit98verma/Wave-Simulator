package Wave_Simulator;


public class CircWaveInfo{
    double insR=0;double insT=0;int delay;
    double lembda,freq;double v,timePeriod;int limit;
    CircWaveInfo(double lembda,double freq,int delay){
        this.lembda=lembda;this.freq=freq;this.delay=delay;
        v=lembda*freq*delay/1000;timePeriod=(1/freq)*1000;
        int k=(int)timePeriod/(4*delay);
        limit=k*delay;
    }
    
    public int getLimit(){return limit;}
    public double getSpeed(){return v;}
    public double getTimePeriod(){return timePeriod;}
    
    public void updateR(){
        insR+=v;
    }
    public void updateT(){
        insT+=delay;
    }
    public double getR(){
        return insR;
    }
    public double getT(){
        return insT;
    }
    
}
