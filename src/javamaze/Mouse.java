package javamaze;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Mouse
{
    MazePanel pan;
    DFSRender ren;
    Ellipse2D s;
    int r,x,y;
    CellData cells[][];
    Stack<CellData> cstack=new Stack<CellData>();
    boolean completed=true;
    Mouse(MazePanel pan,DFSRender ren)
    {
        this.pan=pan;
        this.ren=ren;
        cells=ren.bt.getMaze();
        s=new Ellipse2D.Double();
        r=5;
    }
    void runMouse()
    {
        if(!pan.mouse) return;
        for(int i=0;i<9*5;i++)
            for(int j=0;j<19*5;j++) cells[i][j].visited=false;
        new Thread(new PathSearch()).start();
    }
    void paintMouse()
    {
        pan.e=s;
        pan.repaint();
    }
    class PathSearch implements Runnable
    {
    public void run()
    {
        int total=9*19*25;
        int count=0;
        CellData current=cells[0][0];
        CellData destination=cells[44][94];
        count++;
        completed=false;
        long start=System.nanoTime();
        while(count<total)
        {
            x=current.j*7;
            y=current.i*7;
            s.setFrame(1+x, 1+y, r, r);
            paintMouse();
            current.visited=true;
            if(current==destination) break;
            int dir=getDirection(current);
            if(dir==-1) current=cstack.pop();
            else
            {
                cstack.push(current);
                current=getCell(current,dir);
                count++;
            }
            try
            {
                Thread.sleep(1);
            }
            catch(Exception e){}
        }
        long stop=System.nanoTime();
        double elapsed=(stop-start)/Math.pow(10, 9);
        completed=true;
        JOptionPane.showMessageDialog(pan, "Completed in " + elapsed + " seconds.", "Success!", JOptionPane.INFORMATION_MESSAGE);
    }
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
    int getDirection(CellData c)
    {
        int d=-1;
        for(int i=0;i<4;i++)
        {
            if(c.walls[i]==1)
            {
                if(getCell(c,i).visited) continue;
                d=i;
                break;
            }
        }
        return d;
    }
}
