/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Gabriel Cyr
 */
public abstract class GraphicTableElement {

    protected int ID;
    protected Color color;
    protected int angle;
    private static final Color[] COLOR_ARRAY = {Color.black, Color.red, Color.blue, Color.green, Color.yellow, Color.white};

    public int calculateAngle(int ID) {
        return GraphicTable.calculateNbDegreesPerPerson() * ID;
    }

    public Point rotate(Point pointToRotate, Point center, int angle) {
        Point pointRotated = new Point();
        double theta = Math.PI / (180.0 / angle);
        rotateX(theta, pointRotated, pointToRotate, center);
        rotateY(theta, pointRotated, pointToRotate, center);
        
        return pointRotated;
    }

    public void rotateX(double theta, Point pointRotated, Point pointToRotate, Point center) {
        pointRotated.x = (int) (pointToRotate.x * Math.cos(theta) - pointToRotate.y * Math.sin(theta) - Math.cos(theta) * center.x + Math.sin(theta) * center.y + center.x);
    }

    public void rotateY(double theta, Point pointRotated, Point pointToRotate, Point center) {
        pointRotated.y = (int) (pointToRotate.x * Math.sin(theta) + pointToRotate.y * Math.cos(theta) - Math.sin(theta) * center.x - Math.cos(theta) * center.y + center.y);
    }

    public abstract void draw(Graphics g);
    
    public int getID() {
        return ID;
    }
    
    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setColorByID(int colorID) {
        this.color = COLOR_ARRAY[colorID + 1];
    }

    public Color getColor() {
        return color;
    }
}
