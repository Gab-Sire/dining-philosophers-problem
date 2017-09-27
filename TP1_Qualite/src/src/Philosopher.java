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
    public static final String EAT = "eats";
    public static final String THINK = "thinks";
    

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
        System.out.println(getName() + " thinks");

        long timeThinking = (long) Math.random() * TIME_THINK_MAX;
        doSleep(timeThinking);
        System.out.println(getName() + " finished thinking");
    }

    public synchronized void becomesHungry(int phID) {
        System.out.println(this.getName() + " is hungry");
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
        System.out.println(getName() + " eats for " + timeToSleep);
        doSleep((long) timeToSleep);
        System.out.println(getName() + " finished eating");
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
        logChopstickAction("wants", chopstickID);
        System.out.println(String.format("repainting ph %d - color %d ", chopstickID, this.ID));
        table.colorChopstick(this.ID, chopstickID);
        table.take(chopstickID);
        logChopstickAction("got", chopstickID);
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
        logChopstickAction("release", chopstickID);
    }

    public synchronized void logChopstickAction(String action, int chopstickID) {
        String whichChopstick = (chopstickID == this.leftChopstickID) ? "left" : "right";
        System.out.println(getName() + " " + action + " " + whichChopstick + " chopstick");
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
}
