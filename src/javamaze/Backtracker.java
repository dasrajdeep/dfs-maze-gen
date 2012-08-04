package javamaze;

import java.util.*;

public class Backtracker
{
    private CellData cells[][]=new CellData[9*5][19*5];
    private int totalcells,visitedcells;
    private Stack<CellData> cstack=new Stack<CellData>();
    Backtracker()
    {
        totalcells=19*9*25;
        visitedcells=0;
        for(int i=0;i<9*5;i++)
        {
            for(int j=0;j<19*5;j++) cells[i][j]=new CellData(i,j);
        }
        generate();
    }
    CellData[][] getMaze()
    {
        return cells;
    }
    private void generate()
    {
        int i,j;
        int num=ran(totalcells);
        i=num/95;
        j=num%95;
        CellData current=cells[i][j];
        visitedcells++;
        while(visitedcells<totalcells)
        {
            int nb[]=neighbours(i,j);
            if(nb.length>0)
            {
                int ch=ran(nb.length);
                cells[i][j].walls[nb[ch]]=1;
                CellData nextcell=getCell(i,j,nb[ch]);
                nextcell.walls[wallReflect(nb[ch])]=1;
                cstack.push(current);
                current=nextcell;
                visitedcells++;
            }
            else
            {
                current=cstack.pop();
            }
            i=current.i;
            j=current.j;
        }
        System.out.println("Generating complete. Total=" + totalcells + ", Visited=" + visitedcells);
    }
    int wallReflect(int dir)
    {
        int pos=0;
        if(dir==0) pos=2;
        if(dir==1) pos=3;
        if(dir==2) pos=0;
        if(dir==3) pos=1;
        return pos;
    }
    CellData getCell(int i,int j,int offset)
    {
        CellData c=cells[i][j];
        switch(offset)
        {
            case 0:
                c=cells[i-1][j];
                break;
            case 1:
                c=cells[i][j+1];
                break;
            case 2:
                c=cells[i+1][j];
                break;
            case 3:
                c=cells[i][j-1];
                break;
        }
        return c;
    }
    int[] neighbours(int i,int j)
    {
        int cnt=0;
        int n[]=new int[4];
        if(i!=0)
        {
            if(cells[i-1][j].allIntact()) n[cnt++]=0;
        }
        if(j!=94)
        {
            if(cells[i][j+1].allIntact()) n[cnt++]=1;
        }
        if(i!=44)
        {
            if(cells[i+1][j].allIntact()) n[cnt++]=2;
        }
        if(j!=0)
        {
            if(cells[i][j-1].allIntact()) n[cnt++]=3;
        }
        int nb[]=new int[cnt];
        for(int k=0;k<cnt;k++) nb[k]=n[k];
        return nb;
    }
    int ran(int val)
    {
        return (int)(Math.random()*val);
    }
}

class CellData
{
    int backtrack[]=new int[4];
    int solution[]=new int[4];
    int border[]=new int[4];
    int walls[]=new int[4];
    boolean visited=false;
    int i,j;
    CellData(int i,int j)
    {
        this.i=i;
        this.j=j;
        for(int k=0;k<4;k++) walls[k]=0;
    }
    boolean allIntact()
    {
        boolean intact=true;
        for(int k=0;k<4;k++)
        {
            if(walls[k]==1) intact=false;
        }
        return intact;
    }
}
