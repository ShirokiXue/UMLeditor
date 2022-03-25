package src.CanvasObject.LineObject;

import src.CanvasObject.BasicObject.*;
import java.awt.*;

public class ConnectionLine{

    ConnectionPort portA, portB;

    ConnectionLine( ConnectionPort portA,  ConnectionPort portB ){

        this.portA = portA;
        this.portB = portB;
    }

    public void draw(Graphics g){

        int x1, y1, x2, y2;

        x1 = this.portA.x;
        y1 = this.portA.y;

        x2 = this.portB.x;
        y2 = this.portB.y;

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
}