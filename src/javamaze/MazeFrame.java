package javamaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MazeFrame extends JFrame
{
    private MazePanel maze;
    private Mouse m;
    private Cheese c[];
    private DFSRender ren;
    private JButton runagain;
    MazeFrame()
    {
        setTitle("My Maze");
        setResizable(false);
        setLayout(new BorderLayout());
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit tools=Toolkit.getDefaultToolkit();
        Dimension dim=tools.getScreenSize();
        setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);
        maze=new MazePanel(dim.width,dim.height);
        add(maze);
        ren=new DFSRender(maze);
        m=new Mouse(maze,ren);
        m.runMouse();
        runagain=new JButton("Run Mouse!");
        JPanel bar=new JPanel();
        bar.add(runagain);
        add(bar,BorderLayout.SOUTH);
        runagain.addActionListener(new
                ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!m.completed) return;
                m.runMouse();
            }
        });
    }
}
