package javamaze;

import java.awt.*;
import java.util.*;

public class DFSRender
{
    MazePanel pan;
    Backtracker bt;
    CellData cells[][];
    Stack<CellData> stack=new Stack<CellData>();
    int dx,dy,total;
    DFSRender(MazePanel pan)
    {
        this.pan=pan;
        bt=new Backtracker();
        cells=bt.getMaze();
        dx=7;
        dy=7;
        total=19*9*25;
        for(int i=0;i<9*5;i++)
        {
            for(int j=0;j<19*5;j++) cells[i][j].visited=false;
        }
        render();
    }
    void render()
    {
        new Thread(new
                Runnable()
        {
            public void run()
            {
                for(int i=0;i<9*5;i++)
                {
                    for(int j=0;j<19*5;j++)
                    {
                        CellData c=cells[i][j];
                        Rectangle r=getCell(i,j);
                        for(int k=0;k<4;k++)
                        {
                            if(c.walls[k]==0) renderWall(r,k);
                            try
                            {
                                Thread.sleep(1);
                            }
                            catch(Exception e) {}
                        }
                    }
                }
                pan.mouse=true;
            }
        }).start();
    }
    boolean allVisited()
    {
        int count=0;
        for(int m=0;m<9*5;m++)
                {
                    for(int n=0;n<19*5;n++)
                    {
                        if(cells[m][n].visited) count++;
                    }
                }
        if(count==total)return true;
        else return false;
    }
    int ran(int val)
    {
        return (int)(Math.random()*val);
    }
    int[] neighbours(int i,int j)
    {
        int cnt=0;
        int n[]=new int[4];
        if(i!=0)
        {
            if(!cells[i-1][j].visited) n[cnt++]=0;
        }
        if(j!=94)
        {
            if(!cells[i][j+1].visited) n[cnt++]=1;
        }
        if(i!=44)
        {
            if(!cells[i+1][j].visited) n[cnt++]=2;
        }
        if(j!=0)
        {
            if(!cells[i][j-1].visited) n[cnt++]=3;
        }
        int nb[]=new int[cnt];
        for(int k=0;k<cnt;k++) nb[k]=n[k];
        return nb;
    }
    void renderWall(Rectangle r,int wall)
    {
        if(wall==0) pan.drawMaze(r.x, r.y, r.x+dx, r.y);
        if(wall==1) pan.drawMaze(r.x+dx, r.y, r.x+dx, r.y+dy);
        if(wall==2) pan.drawMaze(r.x, r.y+dy, r.x+dx, r.y+dy);
        if(wall==3) pan.drawMaze(r.x, r.y, r.x, r.y+dy);
    }
    Rectangle getCell(int i,int j)
    {
        Rectangle rec=new Rectangle(j*dx,i*dy,dx,dy);
        return rec;
    }
}
