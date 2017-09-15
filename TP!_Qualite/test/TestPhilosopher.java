/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import junit.framework.TestCase;
import org.junit.Test;
import src.*;

/**
 *
 * @author portable
 */
public class TestPhilosopher extends TestCase {
    
    int idPhilosopher, idLeftChopstick,  idRightChopstick;
    GraphicTable table;
    Philosopher philosopher;
    
    public TestPhilosopher(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        idPhilosopher = 0;
        idLeftChopstick = 1;
        idRightChopstick = 0;
        table = new GraphicTable();
        philosopher = new Philosopher(idPhilosopher, table, idLeftChopstick, idRightChopstick);
    }
    
    @Override
    protected void tearDown() throws Exception {
        idPhilosopher = 0;
        idLeftChopstick = 0;
        idRightChopstick = 0;
        table = null;
        philosopher = null;
    }
    
    @Test(timeout = 2000)
    public void testDoSleep() throws InterruptedException{
        philosopher.doSleep(3000);
        Thread.sleep(4000);
    }

    
}
