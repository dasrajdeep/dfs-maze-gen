package javamaze;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;

public class MazePanel extends JPanel
{
    BufferedImage img;
    Graphics2D g2;
    ArrayList<Ellipse2D> mice=new ArrayList<Ellipse2D>();
    int width,height;
    Ellipse2D e;
    boolean mouse=false;
    MazePanel(int w,int h)
    {
        height=h/2;
        width=w/2;
        setPreferredSize(new Dimension(width,height));
        img=new BufferedImage(665,324,BufferedImage.TYPE_INT_ARGB);
        e=new Ellipse2D.Double();
    }
    void drawMaze(int x1,int y1,int x2,int y2)
    {
        g2=img.createGraphics();
        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(1.0F));
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
        repaint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        if(mouse)
        {
            Graphics2D g2=(Graphics2D)g;
            g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
            g2.setPaint(Color.RED);
            g2.draw(e);
            g2.fill(e);
            g2.setPaint(Color.YELLOW);
            for(Ellipse2D m : mice)
            {
                g2.draw(m);
                g2.fill(m);
            }
        }
    }
}
