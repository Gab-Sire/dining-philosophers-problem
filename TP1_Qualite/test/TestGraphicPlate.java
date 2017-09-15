/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Point;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import src.*;

/**
 *
 * @author portable
 */
public class TestGraphicPlate extends TestCase {

    int angle, sizePlate, idColorBlack, idColorRed, idColorYellow;
    Point centerPlate, coordsPlate, resultPoint;
    GraphicPlate plate;

    public TestGraphicPlate(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        angle = 100;
        idColorBlack = -1;
        idColorRed = 0;
        idColorYellow = 3;

        sizePlate = 30;
        centerPlate = new Point(200, 100);
        coordsPlate = new Point(centerPlate.x, centerPlate.y - 70);
        resultPoint = new Point(268, 112);
        plate = new GraphicPlate(angle, centerPlate, coordsPlate, sizePlate);
    }

    @Override
    protected void tearDown() throws Exception {
        angle = 0;
        idColorBlack = 0;
        idColorRed = 0;
        idColorYellow = 0;

        sizePlate = 0;
        centerPlate = null;
        coordsPlate = null;
        resultPoint = null;
        plate = null;
    }

    public void testSetColor() {
        plate.setColorByID(idColorBlack);
        assertEquals(Color.black, plate.getColor());
        plate.setColorByID(idColorYellow);
        assertEquals(Color.yellow, plate.getColor());
    }

    public void testMove() {
        assertEquals(resultPoint, plate.rotate(coordsPlate, centerPlate, angle));
    }
    
    

}
