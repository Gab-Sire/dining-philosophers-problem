/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import junit.framework.TestCase;
import org.junit.Test;
import src.*;

/**
 *
 * @author portable
 */
public class TestPhilosopher extends TestCase {

    int philosopherID, leftChopstickID, rightChopstickID, idColor;
    GraphicTable table;
    Philosopher philosopher01;
    Color colorPhilosopher;

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
    }
    
    public void testBecomesHungry(){
        int compteInitial = table.getCompte();
        philosopher01.becomesHungry();
        assertEquals(compteInitial + 1, table.getCompte());
    }
    
    public void testPreparesToEat(){
        philosopher01.preparesToEat();
        GraphicPlate[] platesArray = this.table.getPlates();
        assertEquals(colorPhilosopher,  platesArray[philosopherID].getColor());
    }
    
    @Test(timeout = 2000)
    public void testDoSleep() throws InterruptedException {
        Thread thread = new Thread();
        thread.sleep(5000);
        philosopher01.doSleep(3000);

        assertTrue(thread.getState() == thread.getState().TIMED_WAITING);
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

    public void testReleaseLeftChopstick() {
        philosopher01.releaseLeftChopstick();
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(Color.black, chopsticksArray[leftChopstickID].getColor());
    }
    public void testReleaseRightChopstick() {
        philosopher01.releaseRightChopstick();
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(Color.black, chopsticksArray[rightChopstickID].getColor());
    }
    
    
    
}
