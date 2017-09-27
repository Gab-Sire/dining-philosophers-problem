/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Point;
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
    Point screenCenter;
    
    @Before
    public void setUp() {
        
        gui = new InterfaceWindow();
        width = 400;
        height = 600;
        gui.setSize(width, height);
        
        centerHorizontal = 200;
        centerVertical = 300;
        screenCenter = new Point(centerHorizontal, centerVertical);
    }
    
    @After
    public void tearDown() {
        
        gui = null;
        width = 0;
        height = 0;
        centerHorizontal = 0;
        centerVertical = 0;
        screenCenter = null;
    }
    
    @Test
    public void testConfigureWindow() {
        gui.configureWindow();
        assertTrue(InterfaceWindow.PROGRAM_TITLE == gui.getTitle());
        assertTrue(InterfaceWindow.WINDOW_BACKGROUND == gui.getBackground());
    }
    
    @Test
    public void testDisplayWindow() {
        gui.displayWindow();
        assertTrue(gui.isVisible());
    }
    
    @Test
    public void testCalculateCenterHorizontalScreen() {
        assertEquals(centerHorizontal, gui.calculateCenterHorizontalScreen());
    }
    
    @Test
    public void testCalculateCenterVerticalScreen() {
        assertEquals(centerVertical, gui.calculateCenterVerticalScreen());
    }
    
    @Test
    public void testInitializeScreenCenter() {
        assertEquals(screenCenter, gui.initializeScreenCenter());
    }
}
