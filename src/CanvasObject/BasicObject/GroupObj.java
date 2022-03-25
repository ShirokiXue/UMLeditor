package src.CanvasObject.BasicObject;

import java.awt.*;
import java.util.ArrayList;

public class GroupObj extends BasicObject{

    public ArrayList<BasicObject> basicObjects = new ArrayList<BasicObject>();
    
    public GroupObj(ArrayList<BasicObject> bos){

        super(new Rectangle(), bos.size());
        this.type = 3;

        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
        BasicObject bo;
        
        for(int i=0; i<bos.size(); i++){

            bo = bos.get(i);

            if(bo.isSelected){
                
                bo.isSelected = false;
                bos.set(i, null);
                x1 = Math.min(x1, bo.r.x);
                y1 = Math.min(y1, bo.r.y);
                x2 = Math.max(x2, bo.r.x+bo.r.width);
                y2 = Math.max(y2, bo.r.y+bo.r.height);

                this.basicObjects.add(bo);
            }
            
        }
        this.r.setBounds(x1, y1, x2-x1, y2-y1);

        while(bos.remove(null)){}

    }

    public void draw(Graphics g){

        for (BasicObject bo : this.basicObjects) {

            bo.draw(g);
        }

        if (this.isSelected){

            g.setColor(Color.WHITE);
            g.drawRect(r.x, r.y, r.width, r.height);
            // drawPort(g);
        }
    }

    public void setCoordinate(int x, int y){

        int shiftX = x - this.r.x;
        int shiftY = y - this.r.y;

        this.r.x = x;
        this.r.y = y;

        for (BasicObject bo : this.basicObjects) {
            bo.shiftCoordinate(shiftX, shiftY);
        }

        this.updatePortCoordinates();
    }

    public void shiftCoordinate(int x, int y){

        this.r.x += x;
        this.r.y += y;

        for (BasicObject bo : this.basicObjects) {
            bo.shiftCoordinate(x, y);
        }

        this.updatePortCoordinates();
    }

    public boolean contain(int x, int y){

        for (BasicObject bo : this.basicObjects) {

            if( bo.contain(x, y) ){ return true;}
        }
        return false;
    }

    public ConnectionPort getPort(int x, int y) {

        // int min_distance = Integer.MAX_VALUE;
        // int distance;
        ConnectionPort port = null;

        for (BasicObject bo : this.basicObjects) {

            if( bo.contain(x, y) ){ port = bo.getPort(x, y);}
        }

        // distance = CalculateDistanceQuadratic(x, y, (r.x + (r.width / 2)), r.y);
        // if (distance < min_distance) {

        //     min_distance = distance;
        //     port = portN;
        // }
        // distance = CalculateDistanceQuadratic(x, y, (r.x + (r.width / 2)), (r.y + r.height));
        // if (distance < min_distance) {

        //     min_distance = distance;
        //     port = portS;
        // }
        // distance = CalculateDistanceQuadratic(x, y, (r.x + r.width), (r.y + (r.height / 2)));
        // if (distance < min_distance) {

        //     min_distance = distance;
        //     port = portE;
        // }
        // distance = CalculateDistanceQuadratic(x, y, (r.x), (r.y + (r.height / 2)));
        // if (distance < min_distance) {

        //     min_distance = distance;
        //     port = portW;
        // }

        return port;
    }
    
}