package src;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener{

    JMenuItem fileButton1, fileButton2, editButton1, editButton2, editButton3;
    MainFrame frame;

    MenuBar(MainFrame f){

        this.frame = f; 

        JMenu fileMenu = new JMenu("File");
        fileButton1 = new JMenuItem("Apple");
        fileButton2 = new JMenuItem("Banana");
        fileMenu.add(fileButton1);
        fileMenu.add(fileButton2);

        JMenu editMenu = new JMenu("Edit");
        editButton1 = new JMenuItem("Group");
        editButton2 = new JMenuItem("Ungroup");
        editButton3 = new JMenuItem("Rename");

        editButton1.addActionListener(this);
        editButton2.addActionListener(this);
        editButton3.addActionListener(this);

        editMenu.add(editButton1);
        editMenu.add(editButton2);
        editMenu.add(editButton3);

        this.add(fileMenu);
        this.add(editMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==editButton1){
            
            if( this.frame.canvas.selectedNumber > 1){
                
                this.frame.canvas.addGroup();
            }
        }

        if(e.getSource()==editButton2){
            
            this.frame.canvas.ungroup();
        }

        if(e.getSource()==editButton3){
            
            this.frame.canvas.rename();
        }
    }
}
