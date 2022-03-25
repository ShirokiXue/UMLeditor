package src.CanvasObject.BasicObject;

import java.awt.*;

public class CaseObj extends BasicObject{

        public CaseObj(Rectangle rectangle, int depth){
    
            super(rectangle, depth);
            this.type = 2;
        }
    
        public void draw(Graphics g){
            g.setColor(Color.WHITE);
            g.fillOval(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
            g.drawOval(r.x, r.y, r.width, r.height);
            if (this.isSelected){
                drawPort(g);
            }

            FontMetrics metrics = g.getFontMetrics(font);
            int x = this.r.x + (this.r.width - metrics.stringWidth(text)) / 2;
            int y = this.r.y + this.r.height/2 + metrics.getAscent()/4;
            g.setFont(font);
            g.drawString(this.text, x, y);
        }
        
    }