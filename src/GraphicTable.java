import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicTable extends Frame implements WindowListener {
    public static final int MAX = 5;
    static private int compte = 0;
    static private int enAttente = 0;
    private Point center;
    private GraphicPlate plates[];
    private GraphicChopstick chops[];
    private boolean chopsticks[];

    public GraphicTable() {
        super();

        chopsticks = new boolean[5];
        for (int i = 0; i <= 4; i++)
            chopsticks[i] = true;
        addWindowListener(this);
        setTitle("Dining Philosophers");
        setSize(200, 200);
        setBackground(Color.darkGray);

        center = new Point(getSize().width / 2, getSize().height / 2);

        plates = new GraphicPlate[5];
        for (int i = 0; i < 5; i++) {
            plates[i] = new GraphicPlate(i, center, new Point(center.x, center.y - 70), 20);
        }

        chops = new GraphicChopstick[5];
        for (int i = 0; i < 5; i++) {
            chops[i] = new GraphicChopstick(i, center,
                    new Point(center.x, center.y - 70),
                    new Point(center.x, center.y - 40));
        }

        show();
        setResizable(false);

        Philosopher p0 = new Philosopher(0, this, 0, 4);
        Philosopher p1 = new Philosopher(1, this, 1, 0);
        Philosopher p2 = new Philosopher(2, this, 2, 1);
        Philosopher p3 = new Philosopher(3, this, 3, 2);
        Philosopher p4 = new Philosopher(4, this, 4, 3);

        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();


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

    public synchronized void becomesHungry(int phID) {
        while (compte == MAX || enAttente > 0) {
            attente();
        }
        compte++;
        plates[phID].setColor(phID);
        repaint();
    }

    private void attente() {
        try {
            enAttente++;
            wait();
            enAttente--;
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public synchronized void doThinking(int phID) {
        plates[phID].setColor(-1);
        repaint();
        compte--;
        notify();
    }

    public synchronized void take(int c) {
        while (!chopsticks[c]) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("boom !");
            }
        }
        chopsticks[c] = false;

    }

    public synchronized void release(int c) {
        chopsticks[c] = true;
        notifyAll();

    }

    public void takeChopstick(int phID, int chID) {
        System.out.println(String.format("repainting ph %d - color %d ", phID, chID ));
        chops[chID].setColor(phID);
        repaint();
    }

    public void releaseChopstick(int phID, int chID) {
        chops[chID].setColor(-1);
        repaint();
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 5; i++) {
            plates[i].draw(g);
            chops[i].draw(g);
        }
    }
}
