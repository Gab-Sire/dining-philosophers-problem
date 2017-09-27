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
    int chopstickAngle, idColorBlack, idColorRed, idColorYellow;
    int id01, id02,  angle01, angle02;

    @Override
    protected void setUp() throws Exception {
        chopstickAngle = 200;
        idColorBlack = -1;
        idColorRed = 0;
        idColorYellow = 3;
        coordCenter = new Point(110, 140);
        coordStart = new Point(100, 130);
        coordEnd = new Point(120, 150);
        resultPoint = new Point(115, 152);
        chopStick = new GraphicChopstick(30, coordCenter, coordStart, coordEnd);
        chopStick.setColorByID(idColorRed);
        
        id01 = 0;
        id02 = 1;
        angle01 = 36;                   //0 + 36
        angle02 = 108;                  //72 + 36
    }

    @Override
    protected void tearDown() throws Exception {
        chopstickAngle = 0; 
        idColorBlack = 0; 
        idColorRed = 0;
        idColorYellow = 0;
        coordCenter = null;
        coordStart = null;
        coordEnd = null;
        resultPoint = null;
        chopStick = null;
        
        id01 = 0;
        id02 = 0;
        angle01 = 0;
        angle02 = 0;
    }
    
    public void testCalculateAngle(){
        //calculateAngle +  36
        assertEquals(angle01, chopStick.calculateAngle(id01));
        assertEquals(angle02, chopStick.calculateAngle(id02));
    }
    
    public void testRotate() {
        assertEquals(resultPoint, chopStick.rotate(coordStart, coordCenter, chopstickAngle));
    }
    
    public void testSetColorID(){
        chopStick.setColorByID(idColorBlack);
        assertEquals(Color.black, chopStick.getColor());
        chopStick.setColorByID(idColorYellow);
        assertEquals(Color.yellow, chopStick.getColor());
    }
}
