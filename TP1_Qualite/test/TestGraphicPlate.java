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

    int plateAngle, sizePlate, idColorBlack, idColorRed, idColorYellow;
    Point centerPlate, coordsPlate, resultPoint;
    GraphicPlate plate;
    int id01, id02, angle01, angle02;

    public TestGraphicPlate(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        plateAngle = 100;
        idColorBlack = -1;
        idColorRed = 0;
        idColorYellow = 3;

        sizePlate = 30;
        centerPlate = new Point(200, 100);
        coordsPlate = new Point(centerPlate.x, centerPlate.y - 70);
        resultPoint = new Point(268, 112);
        plate = new GraphicPlate(plateAngle, centerPlate, coordsPlate, sizePlate);

        id01 = 0;
        id02 = 2;

        angle01 = 0;
        angle02 = 144;
    }

    @Override
    protected void tearDown() throws Exception {
        plateAngle = 0;
        idColorBlack = 0;
        idColorRed = 0;
        idColorYellow = 0;

        sizePlate = 0;
        centerPlate = null;
        coordsPlate = null;
        resultPoint = null;
        plate = null;

        id01 = 0;
        id02 = 0;

        angle01 = 0;
        angle02 = 0;
    }

    public void testCalculateAngle() {
        // 360 / 5 * ID
        assertEquals(angle01, plate.calculateAngle(id01));
        assertEquals(angle02, plate.calculateAngle(id02));
    }

    public void testRotate() {
        assertEquals(resultPoint, plate.rotate(coordsPlate, centerPlate, plateAngle));
    }

    public void testSetColorByID() {
        plate.setColorByID(idColorBlack);
        assertEquals(Color.black, plate.getColor());
        plate.setColorByID(idColorYellow);
        assertEquals(Color.yellow, plate.getColor());
    }
}
