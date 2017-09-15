package src;

import java.io.*;
import java.lang.*;
import java.awt.*;

public class GraphicChopstick extends GraphicTableElement {

    private Point coordStart;
    private Point coordEnd;

    public GraphicChopstick(int ID, Point centerTable, Point coordStart, Point coordEnd) {
        this.ID = ID;
        this.color = Color.black;
        this.angle = calculateAngle(ID);
        this.coordStart = new Point(rotate(coordStart, centerTable, angle));
        this.coordStart.y += 15;
        this.coordEnd = new Point(rotate(coordEnd, centerTable, angle));
        this.coordEnd.y += 15;
    }

    public int calculateAngle(int ID) {
        return super.calculateAngle(ID) + 36;
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawLine(coordStart.x, coordStart.y, coordEnd.x, coordEnd.y);
    }
}
