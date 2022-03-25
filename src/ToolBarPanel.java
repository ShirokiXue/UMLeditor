package src;

import java.awt.*;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.ToolbarButtonFunction.*;

public class ToolBarPanel extends JPanel implements ActionListener{
    
    ButtonGroup group;
    JToggleButton[] buttons = new JToggleButton[6];
    JToggleButton buttonA, buttonB, buttonC, buttonD, buttonE, buttonF;

    MainFrame frame;

    ToolBarPanel(MainFrame f){

        frame = f;
        this.setBackground(Color.red);
        this.setPreferredSize(new Dimension(100,100));
        this.setLayout(new GridLayout(6, 1));
        this.CreateButtonGroup();
    }

    void CreateButtonGroup(){

        buttons[0] = CreateButtonA();
        buttons[1] = CreateButtonB();
        buttons[2] = CreateButtonC();
        buttons[3] = CreateButtonD();
        buttons[4] = CreateButtonE();
        buttons[5] = CreateButtonF();

        group = new ButtonGroup();
        for(int i=0; i<=5; i++){
            group.add(buttons[i]);
            this.add(buttons[i]);
        }

    }

    JToggleButton CreateButtonA(){
        
        ImageIcon image = new ImageIcon("images/select.png");
        buttonA = new JToggleButton(image);
        buttonA.setBackground( Color.WHITE );
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);

        return buttonA;
    }
    JToggleButton CreateButtonB(){

        ImageIcon image = new ImageIcon("images/association.png");
        buttonB = new JToggleButton(image);
        buttonB.setBackground( Color.WHITE );
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);

        return buttonB;
    }
    JToggleButton CreateButtonC(){

        ImageIcon image = new ImageIcon("images/generalization.png");
        buttonC = new JToggleButton(image);
        buttonC.setBackground( Color.WHITE );
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);

        return buttonC;
    }    
    JToggleButton CreateButtonD(){

        ImageIcon image = new ImageIcon("images/composition.png");
        buttonD = new JToggleButton(image);
        buttonD.setBackground( Color.WHITE );
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);

        return buttonD;
    }   
    JToggleButton CreateButtonE(){

        ImageIcon image = new ImageIcon("images/class.png");
        buttonE = new JToggleButton(image);
        buttonE.setBackground( Color.WHITE );
        buttonE.setFocusable(false);
        buttonE.addActionListener(this);

        return buttonE;
    }  
    JToggleButton CreateButtonF(){

        ImageIcon image = new ImageIcon("images/case.png");
        buttonF = new JToggleButton(image);
        buttonF.setBackground( Color.WHITE );
        buttonF.setFocusable(false);
        buttonF.addActionListener(this);

        return buttonF;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==buttonA){

            frame.canvas.setEvent(new SelectDragFunc(frame));
            System.out.println("Select");
        }
        if(e.getSource()==buttonB){
            
            frame.canvas.setEvent(new CreateAssociationFunc(frame));
            System.out.println("Association");
        }
        if(e.getSource()==buttonC){

            frame.canvas.setEvent(new CreateGeneralizationFunc(frame));
            System.out.println("Generalization");
        }
        if(e.getSource()==buttonD){

            frame.canvas.setEvent(new CreateCompositionFunc(frame));
            System.out.println("Composition");
        }
        if(e.getSource()==buttonE){

            frame.canvas.setEvent(new CreateClassFunc(frame));
            System.out.println("Class");
        }
        if(e.getSource()==buttonF){

            frame.canvas.setEvent(new CreateCaseFunc(frame));
            System.out.println("Case");
        }
    }

}
