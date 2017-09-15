/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import junit.framework.TestCase;
import src.*;

/**
 *
 * @author portable
 */
public class TestGraphicChopstick extends TestCase {

    GraphicChopstick chopStick;
    Graphics graphics;
    Point coordCenter, coordStart, coordEnd, resultPoint;
    int angle, idColorBlack, idColorRed, idColorYellow;

    @Override
    protected void setUp() throws Exception {
        angle = 200;
        idColorBlack = -1;
        idColorRed = 0;
        idColorYellow = 3;
        coordCenter = new Point(110, 140);
        coordStart = new Point(100, 130);
        coordEnd = new Point(120, 150);
        resultPoint = new Point(115, 152);
        chopStick = new GraphicChopstick(30, coordCenter, coordStart, coordEnd);
        chopStick.setColorByID(idColorRed);
    }

    @Override
    protected void tearDown() throws Exception {
        angle = 0; 
        idColorBlack = 0; 
        idColorRed = 0;
        idColorYellow = 0;
        coordCenter = null;
        coordStart = null;
        coordEnd = null;
        resultPoint = null;
        chopStick = null;
    }
    
    public void testSetColor(){
        chopStick.setColorByID(idColorBlack);
        assertEquals(Color.black, chopStick.getColor());
        chopStick.setColorByID(idColorYellow);
        assertEquals(Color.yellow, chopStick.getColor());
    }
    
    public void testDraw(){
        //stick.draw(graphics);
        //assertEquals(Color.red, graphics.getColor());
    }
    
    public void testRotation() {
        assertEquals(resultPoint, chopStick.rotate(coordStart, coordCenter, angle));
    }
    
    
}
