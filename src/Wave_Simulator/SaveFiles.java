package Wave_Simulator;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.*;
import javax.swing.JFileChooser;

public class SaveFiles {
    
    public static void saveProgressiveFile(Progressive_Wave pw){
        
        JFileChooser fc=new JFileChooser();int status=fc.showSaveDialog(pw);
        if(status==JFileChooser.APPROVE_OPTION){
            try{
                String dir=fc.getSelectedFile().getAbsolutePath();
                Files.createDirectory(Paths.get(dir));
                Files.createFile(Paths.get(dir+"\\Info.info"));
                try (BufferedWriter bout = Files.newBufferedWriter(Paths.get(dir+"\\Info.info"),Charset.defaultCharset())) {
                    bout.write("PROGRESSIVE");bout.newLine();
                    bout.write(pw.n+"");
                }
                for(int i=0; i<pw.n; i++){
                    Files.createFile(Paths.get(dir+"\\"+i+".wave"));
                    Path paths=Paths.get(dir+"\\"+i+".wave");
                    try(ObjectOutputStream objOUT=new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(paths)))){
                        Wave current=(Wave)pw.wave1.get(i);
                        objOUT.writeObject(current);
                    }catch(Exception e){System.out.println("Object output error");}
                }
           }catch(Exception e){System.out.println(e);}
        }
    }
    public static void saveStandingFile(Standing_Wave sw){
        
        JFileChooser fc=new JFileChooser();int status=fc.showSaveDialog(sw);
        if(status==JFileChooser.APPROVE_OPTION){
            try{
                String dir=fc.getSelectedFile().getAbsolutePath();
                Files.createDirectory(Paths.get(dir));
                Files.createFile(Paths.get(dir+"\\Info.info"));
                try (BufferedWriter bout = Files.newBufferedWriter(Paths.get(dir+"\\Info.info"),Charset.defaultCharset())) {
                    bout.write("STANDING");bout.newLine();
                    bout.write(sw.n+"");bout.newLine();
                    bout.write(sw.close+"");
                }
                for(int i=0; i<sw.n; i++){
                    Files.createFile(Paths.get(dir+"\\"+i+".wave"));
                    Path paths=Paths.get(dir+"\\"+i+".wave");
                    try(ObjectOutputStream objOUT=new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(paths)))){
                        Wave current=(Wave)sw.wave1.get(i);
                        objOUT.writeObject(current);
                    }catch(Exception e){System.out.println("Object output error");}
                }
           }catch(Exception e){System.out.println(e);}
        }
    
    }
    public static void saveCircularFile(PointSource ps){
        
        JFileChooser fc=new JFileChooser();int status=fc.showSaveDialog(ps);
        if(status==JFileChooser.APPROVE_OPTION){
            try{
                String dir=fc.getSelectedFile().getAbsolutePath();
                Files.createDirectory(Paths.get(dir));
                Files.createFile(Paths.get(dir+"\\Info.info"));
                try (BufferedWriter bout = Files.newBufferedWriter(Paths.get(dir+"\\Info.info"),Charset.defaultCharset())) {
                    bout.write("CIRCULAR");bout.newLine();
                    bout.write(ps.n+"");
                }
                for(int i=0; i<ps.n; i++){
                    Files.createFile(Paths.get(dir+"\\"+i+".wave"));
                    try(BufferedWriter bWave=Files.newBufferedWriter(Paths.get(dir+"\\"+i+".wave"), Charset.defaultCharset())){
                        SingCircWave wave=(SingCircWave)ps.circWave.get(i);
                        bWave.write(wave.getWavelength()+"");bWave.newLine();
                        bWave.write(wave.getFrequency()+"");bWave.newLine();
                        Point2D pos=(Point2D.Double)ps.pos.get(i);
                        bWave.write(pos.getX()+"");bWave.newLine();
                        bWave.write(pos.getY()+"");bWave.newLine();
                        Color c=wave.getColor();
                        bWave.write(c.getRed()+"");bWave.newLine();
                        bWave.write(c.getGreen()+"");bWave.newLine();
                        bWave.write(c.getBlue()+"");bWave.newLine();
                        bWave.write(c.getAlpha()+"");bWave.newLine();
                     }
                    catch(Exception e){}
                }
           }catch(Exception e){System.out.println(e);}
        }
    
    }
}
