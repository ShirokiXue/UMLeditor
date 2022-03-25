package src.ToolbarButtonFunction;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import src.CanvasPanel;
import src.MainFrame;

public class CreateCaseFunc extends MouseInputAdapter {

    int cursorX, cursorY;
    int startX, startY, endX, endY;
    int DraggedOffsetX, DraggedOffsetY;

    // private MainFrame frame;
    private CanvasPanel canvas;

    public CreateCaseFunc(MainFrame f) {
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
        canvas.addCase(cursorX, cursorY);
        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {

        setCursorCoor(e);
        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {

        setCursorCoor(e);
        canvas.repaint();
    }
}
