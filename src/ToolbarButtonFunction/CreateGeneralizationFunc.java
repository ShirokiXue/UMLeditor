package src.ToolbarButtonFunction;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import src.CanvasPanel;
import src.MainFrame;
import src.CanvasObject.BasicObject.BasicObject;
import src.CanvasObject.BasicObject.ConnectionPort;
import src.CanvasObject.LineObject.GeneralizationLine;

public class CreateGeneralizationFunc extends MouseInputAdapter {

    int cursorX, cursorY;
    int startX, startY, endX, endY;
    int DraggedOffsetX, DraggedOffsetY;

    // private MainFrame frame;
    private CanvasPanel canvas;

    public CreateGeneralizationFunc(MainFrame f) {
        // this.frame = f;
        this.canvas = f.canvas;
    }

    void setCursorCoor(MouseEvent e){

        this.cursorX = e.getX();
        this.cursorY = e.getY();
    }

    void setStartCoor(MouseEvent e){

        this.startX = this.cursorX;
        this.startY = this.cursorY;
    }

    void setEndCoor(MouseEvent e){

        this.endX = this.cursorX;
        this.endY = this.cursorY;
    }

    public void mousePressed(MouseEvent e) {

        setCursorCoor(e);
        setStartCoor(e);
        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {

        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {

        setCursorCoor(e);
        setEndCoor(e);

        ConnectionPort portA;
        ConnectionPort portB;
        portA = portB = new ConnectionPort(null);

        for (BasicObject bo : canvas.basicObjects) {

            if( bo.contain(this.startX, this.startY) ) portA = portB = bo.getPort(startX, startY); 
        }

        for (BasicObject bo : canvas.basicObjects) {

            if ( bo.contain(this.endX, this.endY) ) portB = bo.getPort(endX, endY);
        }

        if( portA != portB ) canvas.ConnectionLines.add(new GeneralizationLine( portA ,portB ));
    
        canvas.repaint();
    }
}
