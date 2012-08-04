package javamaze;

import java.awt.*;
import java.awt.geom.*;

public class Cheese
{
    MazePanel pan;
    DFSRender ren;
    Ellipse2D s;
    int index;
    CellData cells[][];
    Cheese(MazePanel pan,DFSRender ren)
    {
        this.pan=pan;
        this.ren=ren;
        cells=ren.bt.getMaze();
        s=new Ellipse2D.Double();
        pan.mice.add(s);
        index=pan.mice.size()-1;
    }
    void paintCheese()
    {
        pan.mice.set(index, s);
        pan.repaint();
    }
    void runCheese()
    {
        new Thread(new
                Runnable()
        {
            public void run()
            {
                int x,y;
                CellData current=cells[0][0];
                while(true)
                {
                    x=current.j*35;
                    y=current.i*35;
                    s.setFrame(x+3, y+3, 29, 29);
                    paintCheese();
                    int d=getDirection(current);
                    current=getCell(current,d);
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch(Exception e){}
                }
            }
        }).start();
    }
    int getDirection(CellData c)
    {
        int d=(int)(Math.random()*4);
        if(d==4) d=0;
        while(c.walls[d]==0)
        {
            d=(int)(Math.random()*4);
            if(d==4) d=0;
        }
        return d;
    }
    CellData getCell(CellData c,int dir)
    {
        CellData nc=c;
        if(dir==0) nc=cells[c.i-1][c.j];
        if(dir==1) nc=cells[c.i][c.j+1];
        if(dir==2) nc=cells[c.i+1][c.j];
        if(dir==3) nc=cells[c.i][c.j-1];
        return nc;
    }
}
