package src.CanvasObject.LineObject;

import src.CanvasObject.BasicObject.*;
import java.awt.*;

public class ConnectionLine{

    ConnectionPort portA, portB;

    ConnectionLine( ConnectionPort portA,  ConnectionPort portB ){

        this.portA = portA;
        this.portB = portB;
        // switch(boAPosition){
        //     case 'N':
        //         portA = boA.portN;
        //         break;
        //     case 'S':
        //         portA = boA.portS;
        //         break;
        //     case 'E':
        //         portA = boA.portE;
        //         break;
        //     case 'W':
        //         portA = boA.portW;
        //         break;
        // }
        // switch(boBPosition){
        //     case 'N':
        //         portB = boB.portN;
        //         break;
        //     case 'S':
        //         portB = boB.portS;
        //         break;
        //     case 'E':
        //         portB = boB.portE;
        //         break;
        //     case 'W':
        //         portB = boB.portW;
        //         break;
        // }
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