/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author Gabriel Cyr
 */
public class InterfaceWindow extends Frame implements WindowListener {

    public static final String PROGRAM_TITLE = "Dining Philosophers";
    public static final Color WINDOW_BACKGROUND = Color.darkGray;
    public static final int WINDOW_HEIGHT = 400;
    public static final int WINDOW_WIDTH = 400;
    protected Point screenCenter;

    public InterfaceWindow() {
    }

    public void configureWindow() {
        addWindowListener(this);
        setTitle(PROGRAM_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setBackground(WINDOW_BACKGROUND);
    }

    public void displayWindow() {
        setVisible(true);
        setResizable(false);
    }

    public int calculateCenterHorizontalScreen() {
        int centerHorizontal = getSize().width / 2;
        return centerHorizontal;
    }

    public int calculateCenterVerticalScreen() {
        int centerVertical = getSize().height / 2;
        return centerVertical;
    }

    public Point initializeScreenCenter() {
        int centerHorizontal = calculateCenterHorizontalScreen();
        int centerVertical = calculateCenterVerticalScreen();
        screenCenter = new Point(centerHorizontal, centerVertical);
        return screenCenter;
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}
