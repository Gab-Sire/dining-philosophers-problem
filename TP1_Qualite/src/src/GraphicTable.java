package src;

import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicTable extends Frame implements WindowListener {

    public static final int NUMBER_PEOPLE = 5;
    public static final int TOTAL_DEGREES_IN_CIRCLE = 360;
    public static final String PROGRAM_TITLE = "Dining Philosophers";
    public static final int TABLE_WIDTH = 200;
    public static final int TABLE_HEIGHT = 200;
    public static final Color TABLE_BACKGROUND = Color.darkGray;

    static public int compte = 0;
    static public int enAttente = 0;
    private Point screenCenter;
    private GraphicPlate plates[];
    private GraphicChopstick[] chopsticksArray;
    private boolean[] booleanChopsticksArray;

    public GraphicTable() {

        configureWindow();
        initializeElements();
        displayWindow();
    }

    public void configureWindow() {
        addWindowListener(this);
        setTitle(PROGRAM_TITLE);
        setSize(TABLE_WIDTH, TABLE_HEIGHT);
        setBackground(TABLE_BACKGROUND);
    }

    public void initializeElements() {
        initializeScreenCenter();
        initializeBooleanArray();
        initializePlates();
        intializeChopsticks();
        initializePhilosophers();
    }

    public void displayWindow() {
        setVisible(true);
        setResizable(false);
    }

    public void initializeScreenCenter() {
        screenCenter = new Point(getSize().width / 2, getSize().height / 2);
    }

    public void initializeBooleanArray() {
        booleanChopsticksArray = new boolean[NUMBER_PEOPLE];

        for (int i = 0; i <= 4; i++) {
            booleanChopsticksArray[i] = true;
        }
    }

    public void initializePlates() {
        plates = new GraphicPlate[NUMBER_PEOPLE];

        for (int i = 0; i < NUMBER_PEOPLE; i++) {
            plates[i] = new GraphicPlate(i, screenCenter, new Point(screenCenter.x, screenCenter.y - 70), 20);
        }
    }

    public void intializeChopsticks() {
        chopsticksArray = new GraphicChopstick[NUMBER_PEOPLE];

        for (int i = 0; i < 5; i++) {
            chopsticksArray[i] = new GraphicChopstick(i, screenCenter,
                    new Point(screenCenter.x, screenCenter.y - 70),
                    new Point(screenCenter.x, screenCenter.y - 40));
        }
    }

    public void initializePhilosophers() {
        Philosopher[] philosophersArray = new Philosopher[NUMBER_PEOPLE];

        for (int i = 0; i < philosophersArray.length; i++) {
            instanciatePhilosopher(i, philosophersArray);
        }

        startPhilosophers(philosophersArray);
    }

    public void startPhilosophers(Philosopher[] philosophersArray) {
        for (int i = 0; i < philosophersArray.length; i++) {
            philosophersArray[i].start();
        }
    }

    public void instanciatePhilosopher(int index, Philosopher[] philosophersArray) {
        int leftChopstickID = index;
        int rightChopstickID = (leftChopstickID == 0) ? 4 : leftChopstickID - 1;
        Philosopher philosopher = new Philosopher(index, this, leftChopstickID, rightChopstickID);
        philosophersArray[index] = philosopher;
    }

    public GraphicPlate[] getPlates() {
        return plates;
    }

    public static int getCompte() {
        return compte;
    }

    public static void setCompte(int compte) {
        GraphicTable.compte = compte;
    }

    private synchronized void attente() {
        try {
            enAttente++;
            wait();
            enAttente--;
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public synchronized void doesThinking(int phID) {
        plates[phID].setColorByID(-1);
        repaint();
        compte--;
        notify();
    }

    public synchronized void take(int c) {
        while (!booleanChopsticksArray[c]) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("boom !");
            }
        }
        booleanChopsticksArray[c] = false;
    }

    public synchronized void release(int c) {
        booleanChopsticksArray[c] = true;
        notifyAll();

    }

    public boolean[] getBooleanChopsticksArray() {
        return booleanChopsticksArray;
    }

    public GraphicChopstick[] getChopsticksArray() {
        return chopsticksArray;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 5; i++) {
            plates[i].draw(g);
            chopsticksArray[i].draw(g);
        }
    }

    public static int calculateNbDegreesPerPerson() {
        return TOTAL_DEGREES_IN_CIRCLE / NUMBER_PEOPLE;
    }

    public void colorChopstick(int colorID, int chopstickID) {
        this.chopsticksArray[chopstickID].setColorByID(colorID);
        repaint();
    }

    public void colorPlate(int colorID, int plateID) {
        this.plates[plateID].setColorByID(colorID);
        repaint();
    }

    public synchronized void becomesHungry(int phID, Philosopher philosopher) {
        System.out.println(philosopher.getName() + " is hungry");
        while (compte == NUMBER_PEOPLE || enAttente > 0) {
            attente();
        }
        compte++;
    }

    public void windowOpened(WindowEvent evt) {
    }

    public void windowClosing(WindowEvent evt) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent evt) {
    }

    public void windowIconified(WindowEvent evt) {
    }

    public void windowDeiconified(WindowEvent evt) {
    }

    public void windowActivated(WindowEvent evt) {
    }

    public void windowDeactivated(WindowEvent evt) {
    }

}
