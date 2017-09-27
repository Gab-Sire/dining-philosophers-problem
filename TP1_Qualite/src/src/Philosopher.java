package src;

public class Philosopher extends Thread {

    final int timeThink_max = 20;
    final int timeNextFork = 20;
    final int timeEat_max = 20;
    
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
            think();
            table.becomesHungry(ID, this);
            doSleep(timeNextFork);
            preparesToEat();
            eats();
        }
    }

    public boolean isAsleep() {
        return asleep;
    }

    public synchronized void think() {
        System.out.println(getName() + " thinks");
        this.table.colorPlate(-1, this.ID);
        long timeThinking = (long)Math.random() * timeThink_max;
        doSleep(timeThinking);
        System.out.println(getName() + " finished thinking");
    }

    public synchronized void preparesToEat() {
        this.table.colorPlate(this.ID, this.ID);
        takeChopsticks();
        
    }

    public synchronized void eats() {
        double timeToSleep = Math.random() * timeEat_max;
        System.out.println(getName() + " eats for " + timeToSleep);
        doSleep((long) timeToSleep);
        System.out.println(getName() + " finished eating");
        releaseChopsticks();
    }

    public synchronized void takeChopsticks() {
        takeLeftChopstick();
        takeRightChopstick();
    }
    
    public synchronized void releaseChopsticks(){
        releaseLeftChopstick();
        releaseRightChopstick();
    }

    public synchronized void releaseLeftChopstick() {
        table.colorChopstick(-1, this.leftChopstickID);
        table.release(this.leftChopstickID);
        logChopstickAction("release", this.leftChopstickID);
    }

    public synchronized void releaseRightChopstick() {
        table.colorChopstick(-1, this.rightChopstickID);
        table.release(this.rightChopstickID);
        logChopstickAction("release", this.rightChopstickID);
    }

    public synchronized void takeLeftChopstick() {
        logChopstickAction("wants", this.leftChopstickID);
        System.out.println(String.format("repainting ph %d - color %d ", this.leftChopstickID, this.ID));
        table.colorChopstick(this.ID, this.leftChopstickID);
        table.take(this.leftChopstickID);
        logChopstickAction("got", this.leftChopstickID);
    }

    public synchronized void takeRightChopstick() {
        logChopstickAction("wants", this.rightChopstickID);
        System.out.println(String.format("repainting ph %d - color %d ", this.rightChopstickID, this.ID));
        table.colorChopstick(this.ID, this.rightChopstickID);
        table.take(this.rightChopstickID);
        logChopstickAction("got", this.rightChopstickID);
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

    public void setTable(GraphicTable table) {
        this.table = table;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAsleep(boolean asleep) {
        this.asleep = asleep;
    }
}
