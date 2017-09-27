package src;

import java.io.*;
import java.lang.*;
import java.awt.*;

/**
 * 
 * @author Gabriel Cyr
 */
public class GraphicPlate extends GraphicTableElement{

    private Point centerPlate;
    private int size;

    public GraphicPlate(int ID, Point centerTable, Point coords, int size) {
        this.ID = ID;
        this.size = size;
        this.color = Color.black;
        this.angle = calculateAngle(ID);
        this.centerPlate = new Point(rotate(coords, centerTable, angle));
        this.centerPlate.x -= 10;
        this.centerPlate.y += 5;
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
