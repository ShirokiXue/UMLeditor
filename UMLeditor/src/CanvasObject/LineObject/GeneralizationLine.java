package src.CanvasObject.LineObject;

import src.CanvasObject.BasicObject.*;
import java.awt.*;

public class GeneralizationLine extends ConnectionLine{

    public GeneralizationLine( ConnectionPort portA,  ConnectionPort portB ){
        super( portA, portB );
    }

    public void draw(Graphics g){

        int x1, y1, x2, y2;

        x1 = this.portA.x;
        y1 = this.portA.y;

        x2 = this.portB.x;
        y2 = this.portB.y;

        int dx = x2 - x1, dy = y2 - y1;
        int d = 20, h = 10;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
        g.setColor(Color.WHITE);
        g.fillPolygon(xpoints, ypoints, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xpoints, ypoints, 3);

    }
}