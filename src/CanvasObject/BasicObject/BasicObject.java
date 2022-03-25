package src.CanvasObject.BasicObject;

import java.awt.*;

public class BasicObject{

    public Rectangle r;

    public int depth;
    public boolean isSelected = false;

    int type = 0;
    int SelectedBoxWidth = 10;

    public ConnectionPort portN;
    public ConnectionPort portS;
    public ConnectionPort portE;
    public ConnectionPort portW;

    public String text = "name";

    Font font = new Font(Font.DIALOG, Font.BOLD, 16);

    BasicObject(Rectangle rectangle, int depth){

        this.r = rectangle;
        this.depth = depth;

        this.portN = new ConnectionPort(this);
        this.portS = new ConnectionPort(this);
        this.portE = new ConnectionPort(this);
        this.portW = new ConnectionPort(this);
        updatePortCoordinates();
    }

    public void setCoordinate(int x, int y){

        this.r.x = x;
        this.r.y = y;
        this.updatePortCoordinates();
    }

    public void shiftCoordinate(int x, int y){

        this.r.x += x;
        this.r.y += y;
        this.updatePortCoordinates();
    }

    public ConnectionPort getPort(int X, int Y) {

        int min_distance = Integer.MAX_VALUE;
        int distance;
        ConnectionPort port = null;

        distance = CalculateDistanceQuadratic(X, Y, (r.x + (r.width / 2)), r.y);
        if (distance < min_distance) {

            min_distance = distance;
            port = portN;
        }
        distance = CalculateDistanceQuadratic(X, Y, (r.x + (r.width / 2)), (r.y + r.height));
        if (distance < min_distance) {

            min_distance = distance;
            port = portS;
        }
        distance = CalculateDistanceQuadratic(X, Y, (r.x + r.width), (r.y + (r.height / 2)));
        if (distance < min_distance) {

            min_distance = distance;
            port = portE;
        }
        distance = CalculateDistanceQuadratic(X, Y, (r.x), (r.y + (r.height / 2)));
        if (distance < min_distance) {

            min_distance = distance;
            port = portW;
        }

        return port;
    }

    int CalculateDistanceQuadratic(int x1, int y1, int x2, int y2) {

        return ((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public boolean contain(int x, int y){

        return ( (x > r.x) && (x < (r.x + r.width)) && (y > r.y) && (y < (r.y + r.height)) ) ? true:false;
    }

    public boolean BeContained( int x1, int y1, int x2, int y2 ){

        int min_x = Math.min(x1, x2);
        int min_y = Math.min(y1, y2);
        int max_x = Math.max(x1, x2);
        int max_y = Math.max(y1, y2);
        Rectangle r = this.getRectangle();

        return ( (min_x < r.x) && (max_x > (r.x + r.width)) && 
                 (min_y < r.y) && (max_y > (r.y + r.height)) ) ? true:false;
    }

    public Rectangle getRectangle(){
        return this.r;
    }

    public int getType(){
        return this.type;
    }

    void updatePortCoordinates(){

        this.portN.x = r.x + (r.width/2);
        this.portN.y = r.y;

        this.portS.x = r.x + (r.width/2);
        this.portS.y = r.y + r.height;
        
        this.portE.x = r.x + r.width;
        this.portE.y = r.y + r.height/2;

        this.portW.x = r.x;
        this.portW.y = r.y + r.height/2;
    }

    public void draw(Graphics g){

        g.setColor(Color.WHITE);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawRect(r.x, r.y, r.width, r.height);
        
        if (this.isSelected) {
            drawPort(g);
        }

        FontMetrics metrics = g.getFontMetrics(font);
        int x = this.r.x + (this.r.width - metrics.stringWidth(text)) / 2;
        int y = this.r.y + this.r.height/2 + metrics.getAscent()/4;
        g.setFont(font);
        g.drawString(this.text, x, y);
    }

    void drawPort(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect(this.portN.x - this.SelectedBoxWidth / 2,
                this.portN.y - SelectedBoxWidth / 2,
                SelectedBoxWidth,
                SelectedBoxWidth);
        g.fillRect(this.portS.x - SelectedBoxWidth / 2,
                this.portS.y - SelectedBoxWidth / 2,
                SelectedBoxWidth,
                SelectedBoxWidth);
        g.fillRect(this.portE.x - SelectedBoxWidth / 2,
                this.portE.y - SelectedBoxWidth / 2,
                SelectedBoxWidth,
                SelectedBoxWidth);
        g.fillRect(this.portW.x - SelectedBoxWidth / 2,
                this.portW.y - SelectedBoxWidth / 2,
                SelectedBoxWidth,
                SelectedBoxWidth);
    }

    public void setText(String text){

        this.text = text;
    }
}

