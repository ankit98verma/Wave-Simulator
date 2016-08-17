package Wave_Simulator;

import javax.swing.UIManager;

public class Inference_Waves {

    public static void main(String[] args) {
        //Wave[] w=new Wave[2];
        //w[0]=new Wave(100,150,.25,50,Color.red);w[1]=new Wave(150,120,.15,12,Color.blue);
        //new Progressive_Wave(w);
        Wall w=new Wall();
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {}
    }
}
