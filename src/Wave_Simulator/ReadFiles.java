package Wave_Simulator;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

public class ReadFiles {
    
    int n,status;
    ReadFiles() throws IOException{
        JFileChooser fc=new JFileChooser();fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);status=fc.showOpenDialog(null);
        if(status==JFileChooser.APPROVE_OPTION){
            String dir=fc.getSelectedFile().getAbsolutePath();
            BufferedReader br=Files.newBufferedReader(Paths.get(dir+"\\Info.info"), Charset.defaultCharset());
            String type=br.readLine();n=Integer.parseInt(br.readLine());
            if(type.equals("PROGRESSIVE")){
                ReadProgressive(dir);
            }
            else if(type.equals("STANDING")){
                boolean close=Boolean.parseBoolean(br.readLine());
                ReadStanding(dir, close);
            }
            else if(type.equals("CIRCULAR")){
                ReadCircular(dir);
            }
            
        }
    }
    
    public final void ReadProgressive(String dir) throws IOException{
        List<Wave> wave1=new ArrayList<Wave>(){};
        for(int i=0; i<n; i++){
            Path paths=Paths.get(dir+"\\"+i+".wave");
            try(ObjectInputStream objIN=new ObjectInputStream(new BufferedInputStream(Files.newInputStream(paths)))){
                Wave current=(Wave)objIN.readObject();
                wave1.add(current);
            }catch(Exception e){System.out.println("Object output error");}
        }
        Progressive_Wave pw=new Progressive_Wave(wave1);
    }
    
    public final void ReadStanding(String dir,boolean close) throws IOException{
        List<Wave> wave1=new ArrayList<Wave>(){};
        for(int i=0; i<n; i++){
            Path paths=Paths.get(dir+"\\"+i+".wave");
            try(ObjectInputStream objIN=new ObjectInputStream(new BufferedInputStream(Files.newInputStream(paths)))){
                Wave current=(Wave)objIN.readObject();
                wave1.add(current);
            }catch(Exception e){System.out.println("Object output error");}
        }
        Standing_Wave sw=new Standing_Wave(wave1,close);
    }
    
    public final void ReadCircular(String dir) throws IOException{
        List<SingCircWave> circWave=new ArrayList<SingCircWave>(){};
        List<Point2D.Double> pos=new ArrayList<Point2D.Double>(){};
        for(int i=0; i<n; i++){
            BufferedReader br=Files.newBufferedReader(Paths.get(dir+"\\"+i+".wave"), Charset.defaultCharset());
            double w=Double.parseDouble(br.readLine()),f=Double.parseDouble(br.readLine());
            double posX=Double.parseDouble(br.readLine()),posY=Double.parseDouble(br.readLine());
            Point2D.Double pos1=new Point2D.Double(posX, posY);
            int red=Integer.parseInt(br.readLine()),green=Integer.parseInt(br.readLine()),blue=Integer.parseInt(br.readLine());
            int alpha=Integer.parseInt(br.readLine());Color c=new Color(red,green,blue,alpha);
            SingCircWave circWave1=new SingCircWave(w,f,pos1,c);circWave.add(circWave1);pos.add(pos1);
        }
        PointSource ps=new PointSource(circWave,pos);
    }
    
    public int getStatus(){
        return status;
    }
    
}
