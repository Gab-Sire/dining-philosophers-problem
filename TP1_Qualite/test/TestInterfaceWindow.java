/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import src.InterfaceWindow;

/**
 *
 * @author portable
 */
public class TestInterfaceWindow extends TestCase {
    
    
    InterfaceWindow gui;
    int width;
    int height;
    int centerHorizontal;
    int centerVertical;
    
    @Before
    public void setUp() {
        
        width = 400;
        height = 400;
        gui = new InterfaceWindow();
        gui.setSize(width, height);
        
        centerHorizontal = 200;
        centerVertical = 200;
    }
    
    @After
    public void tearDown() {
        width = 0;
        height = 0;
        gui = null;
        
        centerHorizontal = 0;
        centerVertical = 0;
    }
    
    @Test
    public void testCalculateCenterHorizontalScreen() {
        assertEquals(centerHorizontal, gui.calculateCenterHorizontalScreen());
    }
    
    @Test
    public void testCalculateCenterVerticalScreen() {
        assertEquals(centerVertical, gui.calculateCenterVerticalScreen());
    }
}
