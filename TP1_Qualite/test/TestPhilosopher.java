/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.rules.Timeout;
import src.*;

/**
 *
 * @author Gabriel Cyr
 */
public class TestPhilosopher extends TestCase {

    int philosopherID, leftChopstickID, rightChopstickID, idColor;
    GraphicTable table;
    Philosopher philosopher01;
    Color colorPhilosopher;
    Color colorEmpty;

    public TestPhilosopher(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        philosopherID = 1;
        leftChopstickID = 1;
        rightChopstickID = 0;
        idColor = philosopherID;
        table = new GraphicTable();
        philosopher01 = new Philosopher(philosopherID, table, leftChopstickID, rightChopstickID);
        colorPhilosopher = Color.blue; //1
        colorEmpty = Color.black;
        philosopher01.start();
    }

    @Override
    protected void tearDown() throws Exception {
        philosopherID = 0;
        leftChopstickID = 0;
        rightChopstickID = 0;
        idColor = 0;
        table = null;
        philosopher01 = null;
        colorPhilosopher = null;
        colorEmpty = null;
    }

    public void testBecomesHungry() {
        int compteInitial = GraphicTable.compte;
        philosopher01.becomesHungry();
        assertEquals(compteInitial + 1, GraphicTable.compte);
    }

    public void testPreparesToEat() {
        philosopher01.preparesToEat();
        GraphicPlate[] platesArray = this.table.getPlates();
        assertEquals(colorPhilosopher, platesArray[philosopherID].getColor());
    }
    
    @Test(expected = InterruptedException.class)
    public void testEats() throws InterruptedException{
        philosopher01.eats();
        Thread.sleep(100);
        philosopher01.interrupt();
    }

    public void testFinishesToEat() {
        philosopher01.finishesToEat();
        GraphicPlate[] platesArray = this.table.getPlates();
        assertEquals(colorEmpty, platesArray[philosopherID].getColor());
    }

    public void testTakeLeftChopstick() {
        philosopher01.takeLeftChopstick();
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(colorPhilosopher, chopsticksArray[leftChopstickID].getColor());
    }

    public void testTakeRightChopstick() {
        philosopher01.takeRightChopstick();
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(colorPhilosopher, chopsticksArray[rightChopstickID].getColor());
    }

    public void testTakeChopstick() {
        philosopher01.takeChopstick(leftChopstickID);
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(colorPhilosopher, chopsticksArray[leftChopstickID].getColor());
        boolean[] booleanChopsticksArray = table.getBooleanChopsticksArray();
        assertEquals(false, booleanChopsticksArray[leftChopstickID]);
    }

    public void testReleaseLeftChopstick() {
        philosopher01.releaseLeftChopstick();
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(colorEmpty, chopsticksArray[leftChopstickID].getColor());
    }

    public void testReleaseRightChopstick() {
        philosopher01.releaseRightChopstick();
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(colorEmpty, chopsticksArray[rightChopstickID].getColor());
    }

    public void testReleaseChopstick() {
        philosopher01.releaseChopstick(rightChopstickID);
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(colorEmpty, chopsticksArray[rightChopstickID].getColor());
        boolean[] booleanChopsticksArray = table.getBooleanChopsticksArray();
        assertEquals(true, booleanChopsticksArray[rightChopstickID]);
    }

    @Test(expected = InterruptedException.class)
    public void testDoSleep() throws InterruptedException {
        Thread thread = new Thread();
        philosopher01.doSleep(3000);
        Thread.sleep(200);
        philosopher01.interrupt();
    }

}
