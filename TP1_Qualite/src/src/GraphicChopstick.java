package src;

import java.io.*;
import java.lang.*;
import java.awt.*;

/**
 * 
 * @author Gabriel Cyr
 */
public class GraphicChopstick extends GraphicTableElement {
    
    public static final int CHOPSTICK_COORD_TRANSLATION_Y = 15;
    public static final int CHOPSTICK_ADDITIONAL_ANGLE = 36;
    
    private Point coordStart;
    private Point coordEnd;

    public GraphicChopstick(int ID, Point centerTable, Point coordStart, Point coordEnd) {
        this.ID = ID;
        this.color = Color.black;
        this.angle = calculateAngle(ID);
        this.coordStart = new Point(rotate(coordStart, centerTable, angle));
        this.coordStart.y += CHOPSTICK_COORD_TRANSLATION_Y;
        this.coordEnd = new Point(rotate(coordEnd, centerTable, angle));
        this.coordEnd.y += CHOPSTICK_COORD_TRANSLATION_Y;
    }

    public int calculateAngle(int ID) {
        return super.calculateAngle(ID) + CHOPSTICK_ADDITIONAL_ANGLE;
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawLine(coordStart.x, coordStart.y, coordEnd.x, coordEnd.y);
    }
}
