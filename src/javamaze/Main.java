package javamaze;

import javax.swing.*;

public class Main
{
    public static void main(String[] args) throws Exception 
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MazeFrame m=new MazeFrame();
        m.setVisible(true);
    }
}
