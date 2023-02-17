/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workerFrame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Filip
 */
public class PictureCreating {
    WorkerFrame wf;
    
    public PictureCreating(WorkerFrame wf) {
        Image image = null;
        try {
            image = ImageIO.read(new File("slika.jpg"));
            image = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        wf.addImage(image);
    }
}
