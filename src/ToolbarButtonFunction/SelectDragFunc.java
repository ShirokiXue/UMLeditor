package src.ToolbarButtonFunction;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import src.CanvasPanel;
import src.MainFrame;

import src.CanvasObject.BasicObject.*;

public class SelectDragFunc extends MouseInputAdapter {

    int cursorX, cursorY;
    int startX, startY, endX, endY;
    int DraggedOffsetX, DraggedOffsetY;

    // private MainFrame frame;
    private CanvasPanel canvas;

    public SelectDragFunc(MainFrame f) {
        // this.frame = f;
        this.canvas = f.canvas;
    }

    void setCursorCoor(MouseEvent e) {

        this.cursorX = e.getX();
        this.cursorY = e.getY();
    }

    public void mousePressed(MouseEvent e) {

        setCursorCoor(e);

        this.canvas.selectedNumber = 0;

        for (BasicObject bo : canvas.basicObjects) {

            if (bo.contain(this.cursorX, this.cursorY)) {

                if (canvas.selectedNumber != 1) {

                    bo.isSelected = true;
                    canvas.DraggedObject = bo;
                    canvas.selectedNumber = 1;
                } else {

                    if (bo.depth > canvas.DraggedObject.depth) {

                        canvas.DraggedObject.isSelected = false;
                        bo.isSelected = true;
                        canvas.DraggedObject = bo;
                    }
                }
            } else {
                bo.isSelected = false;
            }
        }

        if (canvas.selectedNumber != 1) {
            this.startX = this.cursorX;
            this.startY = this.cursorY;
            canvas.selectRectangle = new Rectangle();

        } else {
            DraggedOffsetX = cursorX - canvas.DraggedObject.r.x;
            DraggedOffsetY = cursorY - canvas.DraggedObject.r.y;
        }

        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {

        setCursorCoor(e);

        if (canvas.DraggedObject != null) {
            canvas.DraggedObject.setCoordinate(this.cursorX - DraggedOffsetX, this.cursorY - DraggedOffsetY);
        } else {
            canvas.setSelectRectangle(this.startX, this.startY, this.cursorX, this.cursorY);
        }

        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {

        setCursorCoor(e);

        if (canvas.DraggedObject != null) {

            canvas.DraggedObject = null;

        } else {

            canvas.selectRectangle = null;

            for (BasicObject bo : canvas.basicObjects) {

                if (bo.BeContained(this.startX, this.startY, this.cursorX, this.cursorY)) {

                    bo.isSelected = true;
                    canvas.selectedNumber += 1;

                } else {

                    bo.isSelected = false;
                }
            }
        }

        canvas.repaint();
    }
}
