package Wave_Simulator;

import javax.swing.ImageIcon;

public class Pics {
    
    public static ImageIcon next,open,check,play, pause,add,remove,camera,save,edit,control,switchs,on,off;
    Pics(){
        try{
            next=new ImageIcon(this.getClass().getResource("next.png"));
            open=new ImageIcon(this.getClass().getResource("download.png"));
            check=new ImageIcon(this.getClass().getResource("check.png"));
            play=new ImageIcon(this.getClass().getResource("play.png"));
            pause=new ImageIcon(this.getClass().getResource("pause.png"));
            add=new ImageIcon(this.getClass().getResource("add.png"));
            remove=new ImageIcon(this.getClass().getResource("minus.png"));
            camera=new ImageIcon(this.getClass().getResource("camera.png"));
            save=new ImageIcon(this.getClass().getResource("save.png"));
            edit=new ImageIcon(this.getClass().getResource("edit.png"));
            control=new ImageIcon(this.getClass().getResource("control.png"));
            switchs=new ImageIcon(this.getClass().getResource("switch.png"));
            on=new ImageIcon(this.getClass().getResource("switch-on.png"));
            off=new ImageIcon(this.getClass().getResource("switch-off.png"));
        }catch(Exception e){    System.out.println(e);  }
    }
    
}
