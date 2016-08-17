package Wave_Simulator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveAsImage {
    
    public static void saveImage(BufferedImage image) {
        String[] format_name=ImageIO.getReaderFileSuffixes();
        JFileChooser fileChooser = new JFileChooser();
        for(int i=0; i<format_name.length; i++){
            FileFilter filter=new FileNameExtensionFilter(format_name[i],format_name[i]);
            fileChooser.addChoosableFileFilter(filter);
        }
        int status = fileChooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String fileName = selectedFile.getCanonicalPath();
                String ext=fileChooser.getFileFilter().getDescription();
                if (!fileName.endsWith(ext)) {
                    selectedFile = new File(fileName +"."+ext);
                }
                ImageIO.write(image, "png", selectedFile);
            } catch (IOException e) {}
        }
    }
     
}
