package src;

/**
 * 
 * @author Gabriel Cyr
 */
public class Philosopher extends Thread {

    public static final int TIME_THINK_MAX = 1000;
    public static final int TIME_NEXT_FORK = 300;
    public static final int TIME_EAT_MAX = 300;
    public static final int COLOR_BLACK = -1;
    public static final String THINK = " thinks ";
    public static final String HUNGRY = " is hungry ";
    public static final String EAT = " eats for ";
    public static final String WANT = " wants ";
    public static final String GET = " got ";
    public static final String RELEASE = " releases ";
    public static final String FINISH_THINK = " finished thinking";
    public static final String FINISH_EAT = " finished eating ";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static final String CHOPSTICK = " chopstick ";

    private GraphicTable table;

    private int leftChopstickID;
    private int rightChopstickID;
    private int ID;
    private boolean asleep;

    public Philosopher(int ID, GraphicTable table, int leftChopstickID, int rightChopstickID) {
        this.ID = ID;
        this.table = table;
        this.leftChopstickID = leftChopstickID;
        this.rightChopstickID = rightChopstickID;
        this.asleep = false;
        setName("Philosopher " + ID);
    }

    public synchronized void run() {
        doSleep(500l);
        for (;;) {

            table.doesThinking(ID);
            thinks();
            becomesHungry(ID);
            doSleep(TIME_NEXT_FORK);
            preparesToEat();
            eats();
            finishesToEat();
        }
    }

    public synchronized void thinks() {
        System.out.println(getName() + THINK);

        long timeThinking = (long) Math.random() * TIME_THINK_MAX;
        doSleep(timeThinking);
        System.out.println(getName() + FINISH_THINK);
    }

    public synchronized void becomesHungry(int phID) {
        System.out.println(this.getName() + HUNGRY);
        while (this.table.compte == this.table.NUMBER_PEOPLE || this.table.enAttente > 0) {
            this.table.attente();
        }
        GraphicTable.compte++;
    }

    public synchronized void preparesToEat() {
        this.table.colorPlate(this.ID, this.ID);
        takeChopsticks();
    }

    public synchronized void eats() {
        double timeToSleep = Math.random() * TIME_EAT_MAX;
        System.out.println(getName() + EAT + timeToSleep);
        doSleep((long) timeToSleep);
        System.out.println(getName() + FINISH_EAT);
    }

    public synchronized void finishesToEat() {
        releaseChopsticks();
        this.table.colorPlate(COLOR_BLACK, this.ID);
    }

    public synchronized void takeChopsticks() {
        takeLeftChopstick();
        takeRightChopstick();
    }

    public synchronized void takeLeftChopstick() {
        takeChopstick(this.leftChopstickID);
    }

    public synchronized void takeRightChopstick() {
        takeChopstick(this.rightChopstickID);
    }

    public synchronized void takeChopstick(int chopstickID) {
        logChopstickAction(WANT, chopstickID);
        System.out.println(String.format("repainting ph %d - color %d ", chopstickID, this.ID));
        table.colorChopstick(this.ID, chopstickID);
        table.take(chopstickID);
        logChopstickAction(GET, chopstickID);
    }

    public synchronized void releaseChopsticks() {
        releaseLeftChopstick();
        releaseRightChopstick();
    }

    public synchronized void releaseLeftChopstick() {
        releaseChopstick(leftChopstickID);
    }

    public synchronized void releaseRightChopstick() {
        releaseChopstick(this.rightChopstickID);
    }

    public synchronized void releaseChopstick(int chopstickID) {
        table.colorChopstick(COLOR_BLACK, chopstickID);
        table.release(chopstickID);
        logChopstickAction(RELEASE, chopstickID);
    }

    public synchronized void logChopstickAction(String action, int chopstickID) {
        String whichChopstick = (chopstickID == this.leftChopstickID) ? LEFT : RIGHT;
        System.out.println(getName() + action + whichChopstick + CHOPSTICK);
    }

    public synchronized void doSleep(long millis) {
        try {
            asleep = true;
            sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        asleep = false;
    }

    public GraphicTable getTable() {
        return table;
    }

    public int getLeftChopstickID() {
        return leftChopstickID;
    }

    public int getRightChopstickID() {
        return rightChopstickID;
    }

    public int getID() {
        return ID;
    }

    public boolean isAsleep() {
        return asleep;
    }
    
    
}
