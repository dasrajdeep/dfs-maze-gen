package javamaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MazeApplet extends JApplet{
    private MazePanel maze;
    private Mouse m;
    private Cheese c[];
    private DFSRender ren;
    private JButton runagain;
    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {}
        setSize(675,370);
        maze=new MazePanel(1366,768);
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
