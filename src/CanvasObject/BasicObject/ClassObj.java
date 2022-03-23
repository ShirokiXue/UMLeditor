package src.CanvasObject.BasicObject;

import java.awt.*;

public class ClassObj extends BasicObject{

    public ClassObj(Rectangle rectangle, int depth){

        super(rectangle, depth);
        this.type = 1;

    }

    public void draw(Graphics g){

        g.setColor(Color.WHITE);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawRect(r.x, r.y, r.width, r.height);
        g.drawLine(r.x, r.y + (r.height / 3), r.x + r.width, r.y + (r.height / 3));
        g.drawLine(r.x, r.y + (r.height / 3 * 2), r.x + r.width, r.y + (r.height / 3 * 2));
        if (this.isSelected) {
            drawPort(g);
        }

        FontMetrics metrics = g.getFontMetrics(font);
        int x = this.r.x + (this.r.width - metrics.stringWidth(text)) / 2;
        int y = this.r.y + this.r.height/6 + metrics.getAscent()/4;
        g.setFont(font);
        g.drawString(this.text, x, y);
    }
}
