package src;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame{

    public int mode;

    public ToolBarPanel toolbar;
    public CanvasPanel  canvas;
    public MenuBar      menubar;

    public MainFrame(){

        this.setTitle("The Title");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon("images/icon.png");
        this.setIconImage(image.getImage());

        this.toolbar = new ToolBarPanel(this);
        this.canvas  = new CanvasPanel(this);
        this.menubar = new MenuBar(this);

        this.setVisible(true);

        this.add(toolbar,BorderLayout.WEST);
        this.add(canvas,BorderLayout.CENTER);
        this.setJMenuBar(menubar);
    }
}