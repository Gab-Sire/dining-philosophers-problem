package src;

import java.io.*;
import java.lang.*;
import java.awt.*;

/**
 * 
 * @author Gabriel Cyr
 */
public class GraphicPlate extends GraphicTableElement{
    
    public static final int CENTER_PLATE_TRANSLATION_X = 10;
    public static final int CENTER_PLATE_TRANSLATION_Y = 5;
    
    private Point centerPlate;
    private int size;

    public GraphicPlate(int ID, Point centerTable, Point coords, int size) {
        this.ID = ID;
        this.size = size;
        this.color = Color.black;
        this.angle = calculateAngle(ID);
        this.centerPlate = new Point(rotate(coords, centerTable, angle));
        this.centerPlate.x -= CENTER_PLATE_TRANSLATION_X;
        this.centerPlate.y += CENTER_PLATE_TRANSLATION_Y;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(centerPlate.x, centerPlate.y, size, size);
    }

    public Point getCenterPlate() {
        return centerPlate;
    }

    public int getSize() {
        return size;
    }

}
