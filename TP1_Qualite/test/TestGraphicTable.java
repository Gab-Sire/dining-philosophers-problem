/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;
import src.*;

/**
 *
 * @author Gabriel Cyr
 */
public class TestGraphicTable extends TestCase {
  
    int indexChopsticksArray, indexPlatesArray, idColorYellow, compte;
    GraphicChopstick[] chopsticksArray;
    GraphicPlate[] platesArray;
    boolean[] boolChopsticksArray;
    GraphicTable table;
    Philosopher[] philosophersArray;
    int idPhilosopher01, philosophersArraySize, idRightChopstick01;
    
    @Override
    protected void setUp() throws Exception {
        
        indexChopsticksArray = 0;
        indexPlatesArray = 3;
        idColorYellow = 3;
        compte = 0;
        
        table = new GraphicTable();
        table.initializeElements();
        chopsticksArray = table.getChopsticksArray();
        platesArray = table.getPlates();
        boolChopsticksArray = table.getBooleanChopsticksArray();
        
        philosophersArraySize = 2;
        philosophersArray = new Philosopher[philosophersArraySize];
        idPhilosopher01 = 0;
        idRightChopstick01 = 4;
        
    }
    
    @Override
    protected void tearDown() throws Exception{
        
        indexChopsticksArray = 0;
        indexPlatesArray = 0;
        idColorYellow = 0;
        compte = 0;
        
        table = null;
        chopsticksArray = null;
        platesArray = null;
        boolChopsticksArray = null;
        
        philosophersArraySize = 0;
        philosophersArray = null;
        idPhilosopher01 = 0;
        idRightChopstick01 = 0;
        
    }

    public void testInitializeBooleanArray(){
        table.initializeBooleanArray();
        boolean[] booleanChopsticksArray = table.getBooleanChopsticksArray();
        
        for(int i = 0; i < booleanChopsticksArray.length; i++){
            assertEquals(true, booleanChopsticksArray[i]);
        }
    }
    
    public void testInitializePlates(){
        table.initializePlates();
        GraphicPlate[] plates = table.getPlates();
        
        for(int i = 0; i < plates.length; i++){
            assertEquals(GraphicTable.SIZE_PLATE, plates[i].getSize());
            assertEquals(i, plates[i].getID());
        }
    }
    
    public void testInitializeChopsticks(){
        table.initializeChopsticks();
        GraphicChopstick[] chopsticks = table.getChopsticksArray();
        
        for(int i = 0; i < chopsticks.length; i++){
            assertEquals(i, chopsticks[i].getID());
        }
    }
    
    public void instanciatePhilosopher(){
        table.instanciatePhilosopher(idPhilosopher01, philosophersArray);
        assertEquals(idPhilosopher01, philosophersArray[idPhilosopher01].getId());
        assertEquals(idPhilosopher01, philosophersArray[idPhilosopher01].getLeftChopstickID());
        assertEquals(idRightChopstick01, philosophersArray[idPhilosopher01].getRightChopstickID());
    }
    
    public void testTake(){
        table.take(indexChopsticksArray);
        assertEquals(false, boolChopsticksArray[indexChopsticksArray]);
    }
    
    public void testRelease() {
        table.release(indexChopsticksArray);
        assertEquals(true, boolChopsticksArray[indexChopsticksArray]);
    }
    
    public void testColorChopstick(){
        table.colorChopstick(idColorYellow, indexChopsticksArray);
        GraphicChopstick[] chopsticksArray = table.getChopsticksArray();
        assertEquals(idColorYellow, chopsticksArray[indexChopsticksArray]);
    }
    
    public void testColorPlate(){
        table.colorPlate(idColorYellow, indexPlatesArray);
        GraphicPlate[] platesArray = table.getPlates();
        assertEquals(idColorYellow, platesArray[indexPlatesArray]);
    }
    
    
}
