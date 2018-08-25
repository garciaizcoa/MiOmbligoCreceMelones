import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
 
public class AutoResize extends JPanel {
    BufferedImage image;
 
    public AutoResize(BufferedImage image) {
        this.image = image;
    }
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int iw = image.getWidth();
        int ih = image.getHeight();
        double xScale = (double)w/iw;
        double yScale = (double)h/ih;
        double scale = Math.min(xScale, yScale);    // scale to fit
                       //Math.max(xScale, yScale);  // scale to fill
        int width = (int)(scale*iw);
        int height = (int)(scale*ih);
        int x = (w - width)/2;
        int y = (h - height)/2;
        g2.drawImage(image, x, y, width, height, this);
    }
 
    public static void main(String[] args) throws IOException {
        String path = "images/bison.jpg";
        BufferedImage image = ImageIO.read(new File(path));
        AutoResize test = new AutoResize(image);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(test);
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}