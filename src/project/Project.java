package project;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
public class Project {
    public Project() {

    }
    
    public void ranImage() {
        String B = "D:\\work\\Plant Disease Project\\Dataset\\test_balance\\c_";
        for (int i = 0; i < 38; i++) {
            File b = new File(B + i);
            try {
                if (b.mkdir()) {
                    System.out.println("Directory Created");
                } else {
                    System.out.println("Directory is not created");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int numClass = 38;
        for (int i = 0; i < numClass; i++) {
            System.out.println(i);
            if(i !=22){
                String pathIn = "D:\\work\\Plant Disease Project\\Dataset\\train\\c_" + i;
                String pathOut = "D:\\work\\Plant Disease Project\\Dataset\\test_balance\\c_" + i;
                File file = new File(pathIn);
                String[] imageNames = file.list();
                Random rand = rand = new Random();
                int numImg = 100;
//                if(i == 22){System.out.println("22:");numImg = 64;}
                int numRan = imageNames.length;
                int check[] = new int[numImg];

                int index = rand.nextInt(numRan);
                //System.out.println(index);
                for (int j = 0; j < numImg; ++j) {
                    //System.out.println(j);
                    if (check[j] == index) {
                        //System.out.println(check[j]+" "+index);
                        index = rand.nextInt(numRan);
                        j = -1;
                    } else if (check[j] == 0) {
                        // System.out.println("c"+j+":"+check[j]+" "+index);
                        check[j] = index;
                        index = rand.nextInt(numRan);
                        j = -1;
                    }
                }
                Arrays.sort(check);
                for (int n = 0; n < numImg; n++) {
                    //System.out.print(check[n] + " ");
                    BufferedImage img = null;
                    try {
                        File imgFile = new File(pathIn + "\\" + imageNames[check[n]]);
                        img = ImageIO.read(imgFile);
                    } catch (IOException ex) {
                        System.err.println("Error loading image");
                        return;
                    }

                    try {
                        File fileOut = new File(pathOut + "\\" + imageNames[check[n]]);
                        //Image newImage = img.getScaledInstance(256, 256, Image.SCALE_DEFAULT);
                        ImageIO.write(img, "jpg", fileOut);
                    } catch (IOException ex) {
                        System.err.println("Error Saving image");
                        return;
                    }

                }
            }
        }

    }
    
    public void resizes() {
        String R = "D:\\work\\Plant Disease Project\\Dataset\\test_resize\\c_";
        for (int i = 0; i < 38; i++) {
            File r = new File(R + i);
            try {
                if (r.mkdir()) {
                    System.out.println("Directory Created");
                } else {
                    System.out.println("Directory is not created");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int numClass = 38;
        for (int i = 0; i < numClass; i++) {
            String pathIn = "D:\\work\\Plant Disease Project\\Dataset\\test_balance\\c_" + i;
            String pathOut = "D:\\work\\Plant Disease Project\\Dataset\\test_resize\\c_" + i;
            File file = new File(pathIn);
            String[] imageNames = file.list();

            for (int n = 0; n < imageNames.length; n++) {
                BufferedImage img = null;
                try {
                    File imgFile = new File(pathIn + "\\" + imageNames[n]);
                    img = ImageIO.read(imgFile);
                } catch (IOException ex) {
                    System.err.println("Error loading image");
                    return;
                }

                try {
                    File fileOut = new File(pathOut + "\\" + imageNames[n]);
                    ImageIO.write(resize(img, 256, 256), "jpg", fileOut);
                } catch (IOException ex) {
                    System.err.println("Error Saving image");
                    return;
                }

            }
        }
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_DEFAULT);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    
    public void augmentation() {
        String A = "D:\\work\\Plant Disease Project\\Dataset\\train_augmentation\\c_";
        for (int i = 0; i < 38; i++) {
            File a = new File(A + i);
            try {
                if (a.mkdir()) {
                    System.out.println("Directory Created");
                } else {
                    System.out.println("Directory is not created");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }      
    }

    public static void main(String[] args) {
        Project j = new Project();
        j.ranImage();
        j.resizes();
        //j.augmentation();

    }

}
