/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.GraphicChopstick;
import src.GraphicPlate;
import src.GraphicTable;

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
    
    @Before
    public void setUp() {
        
        indexChopsticksArray = 0;
        indexPlatesArray = 3;
        idColorYellow = 3;
        compte = 0;
        
        table = new GraphicTable();
        chopsticksArray = table.getChopsticksArray();
        platesArray = table.getPlates();
        boolChopsticksArray = table.getBooleanChopsticksArray();
    }
    
    @After
    public void tearDown() {
        
        indexChopsticksArray = 0;
        indexPlatesArray = 0;
        idColorYellow = 0;
        compte = 0;
        
        table = null;
        chopsticksArray = null;
        platesArray = null;
        boolChopsticksArray = null;
    }

 
    public void testInitializeBooleanArray(){
        table.initializeBooleanArray();
        boolean[] booleanChopsticksArray = table.getBooleanChopsticksArray();
        
        for(int i = 0; i < booleanChopsticksArray.length; i++){
            assertEquals(true, booleanChopsticksArray[i]);
        }
    }
    
    @Test
    public void testTake(){
        table.take(indexChopsticksArray);
        assertEquals(false, boolChopsticksArray[indexChopsticksArray]);
    }
    
    @Test
    public void testRelease() {
        table.release(indexChopsticksArray);
        assertEquals(true, boolChopsticksArray[indexChopsticksArray]);
    }
}
