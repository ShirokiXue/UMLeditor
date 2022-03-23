package src;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import src.CanvasObject.BasicObject.*;
import src.CanvasObject.LineObject.*;

class CanvasMouseListener extends MouseInputAdapter {

    int cursorX, cursorY;
    int startX, startY, endX, endY;
    int DraggedOffsetX, DraggedOffsetY;

    private MainFrame frame;
    private CanvasPanel canvas;

    CanvasMouseListener(MainFrame f, CanvasPanel c) {
        this.frame = f;
        this.canvas = c;
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

        switch (frame.mode) {

            case 0:

                this.canvas.selectedNumber = 0;
                
                for (BasicObject bo : canvas.basicObjects) {

                    if(bo.contain(this.cursorX, this.cursorY)){
                    

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
                }
                else{
                    DraggedOffsetX = cursorX - canvas.DraggedObject.r.x;
                    DraggedOffsetY = cursorY - canvas.DraggedObject.r.y;
                }

                break;

            case 1:
                this.setStartCoor(e);
                break;

            case 2:
                this.setStartCoor(e);
                break;

            case 3:
                this.setStartCoor(e);
                break;

            case 4:
                canvas.addClass();
                break;

            case 5:
                canvas.addCase();
                break;
        }

        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {

        setCursorCoor(e);

        switch (frame.mode) {

            case 0:
                if ( canvas.DraggedObject != null ) {
                    canvas.DraggedObject.setCoordinate(this.cursorX - DraggedOffsetX, this.cursorY - DraggedOffsetY);
                } else {
                    canvas.setSelectRectangle(this.startX, this.startY, this.cursorX, this.cursorY);
                }
                break;
        }

        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {

        BasicObject boA = null;
        BasicObject boB = null;

        setCursorCoor(e);

        switch (frame.mode) {

            case 0:
                if ( canvas.DraggedObject != null ) {

                    canvas.DraggedObject = null;

                } else {

                    canvas.selectRectangle = null;

                    for (BasicObject bo : canvas.basicObjects) {

                        if ( bo.BeContained(this.startX, this.startY, this.cursorX, this.cursorY) ) {

                            bo.isSelected = true;
                            canvas.selectedNumber += 1;

                        } else {

                            bo.isSelected = false;
                        }
                    }
                }

                break;

            case 1:
                this.setEndCoor(e);

                for (BasicObject bo : canvas.basicObjects) {

                    if( bo.contain(this.startX, this.startY) ){ boA = bo; }
                }

                for (BasicObject bo : canvas.basicObjects) {

                    if ( bo.contain(this.endX, this.endY)  && (boA != bo) ) { boB = bo; }
                }

                if ((boA != null) && (boB != null)) {

                    char boAPosition = boA.getPosition(this.startX, this.startY);
                    char boBPosition = boB.getPosition(this.endX, this.endY);
                    canvas.ConnectionLines.add(new AssociationLine(boA, boAPosition, boB, boBPosition));
                }

                break;
            
            case 2:
                this.setEndCoor(e);

                for (BasicObject bo : canvas.basicObjects) {

                    if( bo.contain(this.startX, this.startY) ){
                        boA = bo;
                    }
                }

                for (BasicObject bo : canvas.basicObjects) {

                    if ( bo.contain(this.endX, this.endY)  && (boA != bo) ) {
                        boB = bo;
                    }
                }

                if ((boA != null) && (boB != null) && (boA != boB)) {

                    char boAPosition = boA.getPosition(this.startX, this.startY);
                    char boBPosition = boB.getPosition(this.endX, this.endY);
                    canvas.ConnectionLines.add(new GeneralizationLine(boA, boAPosition, boB, boBPosition));
                }

                break;

            case 3:
                this.setEndCoor(e);

                for (BasicObject bo : canvas.basicObjects) {

                    if( bo.contain(this.startX, this.startY) ){
                        boA = bo;
                    }
                }

                for (BasicObject bo : canvas.basicObjects) {

                    if ( bo.contain(this.endX, this.endY)  && (boA != bo) ) {
                        boB = bo;
                    }
                }

                if ((boA != null) && (boB != null) && (boA != boB)) {

                    char boAPosition = boA.getPosition(this.startX, this.startY);
                    char boBPosition = boB.getPosition(this.endX, endY);
                    canvas.ConnectionLines.add(new CompositionLine(boA, boAPosition, boB, boBPosition));
                }

                break;
        }
        canvas.repaint();
    }
}
