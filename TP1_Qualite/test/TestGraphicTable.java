/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import junit.framework.TestCase;
import src.*;

/**
 *
 * @author portable
 */
public class TestGraphicTable extends TestCase {

    int indexChopsticksArray, indexPlatesArray, idColorYellow, compte;
    GraphicChopstick[] chopsticksArray;
    GraphicPlate[] platesArray;
    boolean[] boolChopsticksArray;
    GraphicTable table;

    public TestGraphicTable(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        
        indexChopsticksArray = 0;
        indexPlatesArray = 3;
        idColorYellow = 3;
        compte = 0;
        GraphicTable.setCompte(compte);
        
        table = new GraphicTable();
        chopsticksArray = table.getChopsticksArray();
        platesArray = table.getPlates();
        boolChopsticksArray = table.getBooleanChopsticksArray();
    }

    @Override
    protected void tearDown() throws Exception {
        indexChopsticksArray = 0;
        indexPlatesArray = 0;
        idColorYellow = 0;
        compte = 0;
        GraphicTable.setCompte(compte);
        
        table = null;
        chopsticksArray = null;
        platesArray = null;
        boolChopsticksArray = null;
    }
    
    public void testBecomesHungry(){
        table.becomesHungry(idColorYellow, null);
        assertEquals(Color.yellow, platesArray[indexPlatesArray].getColor());
        assertEquals(++compte, GraphicTable.getCompte());
    }
    
    public void testDoThinking(){
        table.doesThinking(indexPlatesArray);
        assertEquals(Color.black, platesArray[indexPlatesArray].getColor());
        assertEquals(--compte, GraphicTable.getCompte());
    }
    
    public void testTake(){
        table.take(indexChopsticksArray);
        assertEquals(false, boolChopsticksArray[indexChopsticksArray]);
    }
    
    public void testRelease() {
        table.release(indexChopsticksArray);
        assertEquals(true, boolChopsticksArray[indexChopsticksArray]);
    }

 
    
   

}
