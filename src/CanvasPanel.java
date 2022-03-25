package src;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.BorderFactory;

import src.CanvasObject.BasicObject.*;
import src.CanvasObject.LineObject.*;

public class CanvasPanel extends JPanel {

    public ArrayList<BasicObject> basicObjects = new ArrayList<BasicObject>();
    public ArrayList<ConnectionLine> ConnectionLines = new ArrayList<ConnectionLine>();

    int ClassWidth = 90;
    int ClassHeight = 135;
    int CaseWidth = 135;
    int CaseHeight = 90;

    public BasicObject DraggedObject = null;
    public int selectedNumber;
    public Rectangle selectRectangle = new Rectangle();

    MouseInputAdapter mouseEvent;

    MainFrame frame;

    CanvasPanel(MainFrame f) {

        frame = f;

        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.gray);

        setEvent(null);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawString("I like cats!", 5, 15);

        for (BasicObject bo : basicObjects) {

            bo.draw(g);
        }
        for (ConnectionLine cl : ConnectionLines) {

            cl.draw(g);
        }
        if (selectRectangle != null) {
            g.setColor(Color.BLACK);
            g.drawRect(selectRectangle.x, selectRectangle.y,
                    selectRectangle.width, selectRectangle.height);
        }
    }

    public void setEvent(MouseInputAdapter mouseEvent){

        removeMouseListener(this.mouseEvent);
        removeMouseMotionListener(this.mouseEvent);
        this.mouseEvent = mouseEvent;
        addMouseListener(mouseEvent);
        addMouseMotionListener(mouseEvent);
    }

    public void setSelectRectangle(int x1, int y1, int x2, int y2){

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int x_min = Math.min(x1, x2);
        int y_min = Math.min(y1, y2);

        this.selectRectangle.setBounds(x_min, y_min, width, height);
    }

    public void addClass(int x, int y) {

        Rectangle r = new Rectangle();
        r.setBounds(x, y, this.ClassWidth, this.ClassHeight);
        BasicObject bo = new ClassObj(r, basicObjects.size());
        basicObjects.add(bo);
    }

    public void addCase(int x, int y) {

        Rectangle r = new Rectangle();
        r.setBounds(x, y, this.CaseWidth, this.CaseHeight);
        BasicObject bo = new CaseObj(r, basicObjects.size());
        basicObjects.add(bo);
    }

    void addGroup(){

        basicObjects.add( new GroupObj(basicObjects) );
        repaint();
        System.out.println("Meow");
    }

    void ungroup(){

        BasicObject bo;

        for( int i=this.basicObjects.size()-1; i>=0; i-- ){

            bo = this.basicObjects.get(i);

            if( bo.getType() == 3 ){

                if( bo.isSelected){

                    for(BasicObject sub_bo: ((GroupObj)bo).basicObjects){

                        this.basicObjects.add(sub_bo);
                    }
                    this.basicObjects.remove(bo);
                }
            }
        }
        repaint();

    }

    void rename(String text){

        for(BasicObject bo: this.basicObjects){

            if( bo.isSelected ){

                bo.setText(text);
            }
        }
        repaint();
    }
}